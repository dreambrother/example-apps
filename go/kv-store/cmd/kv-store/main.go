package main

import (
	"context"
	"flag"
	"log/slog"
	"os"
	"os/signal"
	"syscall"
	"time"

	"local/kv-store/internal/server"
	"local/kv-store/internal/storage"
	"local/kv-store/internal/storage/rwmutex"
	"local/kv-store/internal/storage/sharded"
	"local/kv-store/internal/storage/singlethreaded"
)

func main() {
	addr := flag.String("addr", "0.0.0.0:9000", "server address (host:port)")
	backend := flag.String("backend", "single-threaded", "backend storage (single-threaded, sharded-mutex, rw-mutex)")
	flag.Parse()

	ctx, stop := signal.NotifyContext(context.Background(), syscall.SIGINT, syscall.SIGTERM)
	defer stop()

	srv := server.New(*addr, 10)
	storage := newStorage(ctx, *backend)
	srv.Register("get", func(args []string) (string, error) {
		key := args[0]
		slog.Info("get", "key", key)
		return storage.Get(key), nil
	})
	srv.Register("set", func(args []string) (string, error) {
		key, value := args[0], args[1]
		slog.Info("set", "key", key, "value", value)
		storage.Set(key, value)
		return "ok", nil
	})

	srv.Start(ctx, 10*time.Second)        // blocking until shutdown
	storage.WaitForStop(10 * time.Second) // wait for storage to finish
}

func newStorage(ctx context.Context, backend string) storage.Storage {
	switch backend {
	case "single-threaded":
		return singlethreaded.New(ctx)
	case "rw-mutex":
		return rwmutex.New()
	case "sharded-mutex":
		return sharded.New(16)
	default:
		slog.Error("unknown backend", "backend", backend)
		os.Exit(1)
		return nil
	}
}

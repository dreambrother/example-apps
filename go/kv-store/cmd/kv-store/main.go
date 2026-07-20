package main

import (
	"context"
	"flag"
	"log"
	"os/signal"
	"syscall"
	"time"

	"local/kv-store/internal/server"
	"local/kv-store/internal/storage"
	"local/kv-store/internal/storage/singlethreaded"
)

func main() {
	addr := flag.String("addr", "0.0.0.0:9000", "server address (host:port)")
	backend := flag.String("backend", "single-threaded", "backend storage")
	flag.Parse()

	ctx, stop := signal.NotifyContext(context.Background(), syscall.SIGINT, syscall.SIGTERM)
	defer stop()

	srv := server.New(*addr, 10)
	storage := newStorage(ctx, *backend)
	srv.Register("get", func(args []string) (string, error) {
		key := args[0]
		log.Println("get", key)
		return storage.Get(key), nil
	})
	srv.Register("set", func(args []string) (string, error) {
		key, value := args[0], args[1]
		log.Println("set", key, value)
		storage.Set(key, value)
		return "ok", nil
	})

	srv.Start(ctx, 10*time.Second) // blocking until shutdown
	storage.WaitForStop(10 * time.Second) // wait for storage to finish
}

func newStorage(ctx context.Context, backend string) storage.Storage {
	switch backend {
	case "single-threaded":
		return singlethreaded.NewSingleThreadedStorage(ctx)
	default:
		log.Fatal("Unknown backend " + backend)
		return nil
	}
}

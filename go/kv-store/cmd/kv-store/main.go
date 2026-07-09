package main

import (
	"context"
	"log"
	"os/signal"
	"syscall"
	"time"

	"local/kv-store/internal/server"
)

func main() {
	ctx, stop := signal.NotifyContext(context.Background(), syscall.SIGINT, syscall.SIGTERM)
	defer stop()

	srv := server.New(9000, 10)
	srv.Register("get", func(args []string) (string, error) {
		log.Println("get", args[0])
		return "example", nil
	})
	srv.Register("set", func(args []string) (string, error) {
		log.Println("set", args[0], args[1])
		return "ok", nil
	})

	go func() {
		<-ctx.Done()
		srv.Stop(10 * time.Second)
	}()

	srv.Start()
}

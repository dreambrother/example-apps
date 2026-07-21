package main

import (
	"bufio"
	"context"
	"flag"
	"fmt"
	"log/slog"
	"net"
	"os"
	"os/signal"
	"syscall"
)

func main() {
	addr := flag.String("addr", "localhost:9000", "server address (host:port)")
	flag.Parse()

	conn, err := net.Dial("tcp", *addr)
	if err != nil {
		slog.Error("connect to server", "err", err)
		os.Exit(1)
	}
	defer conn.Close()

	fmt.Printf("Connected to %s. Type commands (Ctrl+C to exit)\n", *addr)

	ctx, cancel := signal.NotifyContext(context.Background(), syscall.SIGINT, syscall.SIGTERM)
	defer cancel()

	// Close connection on Ctrl+C to unblock pending reads.
	go func() {
		<-ctx.Done()
		conn.Close()
	}()

	lines := make(chan string)
	go func() {
		defer close(lines)
		scanner := bufio.NewScanner(os.Stdin)
		for scanner.Scan() {
			lines <- scanner.Text()
		}
		if err := scanner.Err(); err != nil {
			slog.Error("read input", "err", err)
		}
	}()

	reader := bufio.NewReader(conn)
	for {
		fmt.Print("> ")
		select {
		case <-ctx.Done():
			fmt.Println("\nDisconnected")
			return
		case line, ok := <-lines:
			if !ok {
				return
			}
			if line == "" {
				continue
			}

			_, err := fmt.Fprintf(conn, "%s\n", line)
			if err != nil {
				slog.Error("send command", "err", err)
				return
			}

			response, err := reader.ReadString('\n')
			if err != nil {
				slog.Error("read response", "err", err)
				return
			}

			fmt.Print(response)
		}
	}
}

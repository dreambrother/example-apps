package main

import (
	"bufio"
	"context"
	"flag"
	"fmt"
	"log"
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
		log.Fatal("Error connecting to server:", err)
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
			log.Println("Error reading input:", err)
		}
	}()

	fmt.Print("> ")
	for {
		select {
		case <-ctx.Done():
			fmt.Println("\nDisconnected")
			return
		case line, ok := <-lines:
			if !ok {
				return
			}
			if line == "" {
				fmt.Print("> ")
				continue
			}

			_, err := fmt.Fprintf(conn, "%s\n", line)
			if err != nil {
				log.Println("Error sending command:", err)
				return
			}

			response, err := bufio.NewReader(conn).ReadString('\n')
			if err != nil {
				log.Println("Error reading response:", err)
				return
			}

			fmt.Print(response)
			fmt.Print("> ")
		}
	}
}

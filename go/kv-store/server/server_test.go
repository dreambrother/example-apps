package server

import (
	"context"
	"fmt"
	"net"
	"strings"
	"testing"
	"time"
)

func TestServerEcho(t *testing.T) {
	conn := startTestServer(t, map[string]handler{
		"ECHO": func(args []string) (string, error) {
			return args[0], nil
		},
	})

	got := sendRequest(t, conn, "ECHO hello")

	if want := "hello"; got != want {
		t.Fatalf("unexpected response: got %q, want %q", got, want)
	}
}

func TestSeverHandlerError(t *testing.T) {
	conn := startTestServer(t, map[string]handler{
		"OP": func(args []string) (string, error) {
			return "", fmt.Errorf("expected error")
		},
	})

	got := sendRequest(t, conn, "OP test")

	if want := "error 100: expected error"; got != want {
		t.Fatalf("unexpected response: got %q, want %q", got, want)
	}
}

func startTestServer(t *testing.T, handlers map[string]handler) net.Conn {
	t.Helper()

	// Reserve a free ephemeral port by listening on :0 and immediately
	// closing; this avoids hard-coded port collisions in parallel test runs.
	l, err := net.Listen("tcp", "127.0.0.1:0")
	if err != nil {
		t.Fatalf("reserve port: %v", err)
	}
	addr := l.Addr().String()
	l.Close()

	srv := New(addr, 2)
	for op, h := range handlers {
		srv.Register(op, h)
	}

	ctx, cancel := context.WithCancel(context.Background())
	started := make(chan error, 1)
	go func() {
		started <- srv.Start(ctx, 2*time.Second)
	}()

	conn, ready := waitForDial(t, addr, 2*time.Second)
	if !ready {
		select {
		case err := <-started:
			t.Fatalf("server exited before becoming ready: %v", err)
		default:
			t.Fatalf("server never became ready at %s", addr)
		}
	}

	t.Cleanup(func() {
		_ = conn.Close()
		cancel()
		select {
		case err := <-started:
			if err != nil {
				t.Errorf("server returned error on shutdown: %v", err)
			}
		case <-time.After(5 * time.Second):
			t.Errorf("server did not shut down within timeout")
		}
	})

	return conn
}

func waitForDial(t *testing.T, addr string, timeout time.Duration) (net.Conn, bool) {
	t.Helper()
	deadline := time.Now().Add(timeout)
	for time.Now().Before(deadline) {
		c, err := net.Dial("tcp", addr)
		if err == nil {
			return c, true
		}
		time.Sleep(20 * time.Millisecond)
	}
	return nil, false
}

func sendRequest(t *testing.T, conn net.Conn, req string) string {
	if _, err := conn.Write([]byte(req + "\n")); err != nil {
		t.Fatalf("write request: %v", err)
	}
	return readResponse(t, conn)
}

func readResponse(t *testing.T, conn net.Conn) string {
	t.Helper()
	if err := conn.SetReadDeadline(time.Now().Add(2 * time.Second)); err != nil {
		t.Fatalf("set read deadline: %v", err)
	}
	buf := make([]byte, 64)
	n, err := conn.Read(buf)
	if err != nil {
		t.Fatalf("read response: %v", err)
	}
	return strings.TrimSpace(string(buf[:n]))
}

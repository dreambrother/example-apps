package server

import (
	"bufio"
	"context"
	"errors"
	"fmt"
	"io"
	"log/slog"
	"net"
	"os"
	"strings"
	"sync"
	"time"
)

var errTooLarge = errors.New("stream exceeded maximum byte limit")

type Server struct {
	addr     string
	handlers map[string]handler
	listener net.Listener
	wg       *sync.WaitGroup
	workers  int
	queue    chan net.Conn
}

type handler func(args []string) (string, error)

func New(addr string, workers int) *Server {
	return &Server{
		addr:     addr,
		handlers: make(map[string]handler),
		wg:       &sync.WaitGroup{},
		workers:  workers,
		queue:    make(chan net.Conn, workers),
	}
}

func (s *Server) Register(op string, handle func(args []string) (string, error)) {
	s.handlers[op] = handle
}

func (s *Server) Start(ctx context.Context, stopTimeout time.Duration) error {
	slog.Info("starting server", "addr", s.addr)
	listener, err := net.Listen("tcp", s.addr)
	if err != nil {
		return fmt.Errorf("failed to listen: %w", err)
	}
	s.listener = listener

	for range s.workers {
		s.wg.Add(1)
		go s.worker(s.queue)
	}

	go func() {
		<-ctx.Done()
		slog.Info("server is shutting down")
		err := listener.Close()
		if err != nil {
			slog.Error("close listener", "err", err)
		}
	}()

	slog.Info("server started")
	for {
		clientConn, err := listener.Accept()
		if err != nil {
			if errors.Is(err, net.ErrClosed) {
				slog.Info("stop accepting connections")
				s.closeAndWait(stopTimeout)
				return nil
			}
			slog.Error("accept connection", "err", err)
			continue
		}
		s.queue <- clientConn
	}
}

func (s *Server) worker(q chan net.Conn) {
	defer s.wg.Done()

	for conn := range q {
		s.handle(conn)
	}
}

func (s *Server) closeAndWait(timeout time.Duration) {
	done := make(chan struct{})
	go func() {
		close(s.queue)
		s.wg.Wait()
		close(done)
	}()
	select {
	case <-done:
		return
	case <-time.After(timeout):
		slog.Warn("timeout waiting for workers to finish")
	}
}

func (s *Server) handle(clientConn net.Conn) {
	defer clientConn.Close()

	for {
		clientConn.SetReadDeadline(time.Now().Add(5 * time.Minute))
		line, err := readLine(clientConn, 10*1024)
		if err != nil {
			if errors.Is(err, errTooLarge) {
				slog.Warn("request too large", "err", err)
				clientConn.Write([]byte("error 1: request too large\n"))
			} else if errors.Is(err, io.EOF) {
				slog.Debug("client disconnected")
			} else if errors.Is(err, os.ErrDeadlineExceeded) {
				slog.Debug("connection timed out")
			} else {
				slog.Error("read command", "err", err)
			}
			return
		}

		slog.Info("request", "line", line)
		response, err := s.processCommand(line)
		if err != nil {
			slog.Error("process command", "err", err)
			clientConn.Write([]byte("error 100: " + err.Error() + "\n"))
			continue
		}
		slog.Info("response", "value", response)
		_, err = clientConn.Write([]byte(response + "\n"))
		if err != nil {
			slog.Error("write response", "err", err)
			return
		}
	}
}

func (s *Server) processCommand(line string) (string, error) {
	cmd := strings.Split(line, " ")
	if len(cmd) < 2 || len(cmd) > 3 {
		return "", fmt.Errorf("invalid command %v", line)
	}
	op := cmd[0]
	h, ok := s.handlers[op]
	if !ok {
		return "", fmt.Errorf("unknown command %v", line)
	}
	return h(cmd[1:])
}

func readLine(r io.Reader, maxBytes int64) (string, error) {
	lr := &io.LimitedReader{R: r, N: maxBytes + 1}
	line, err := bufio.NewReader(lr).ReadString('\n')
	if err == io.EOF && lr.N == 0 {
		return line, errTooLarge
	}
	if err != nil {
		return line, err
	}
	return line[:len(line)-1], nil
}

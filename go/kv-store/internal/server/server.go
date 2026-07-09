package server

import (
	"bufio"
	"context"
	"errors"
	"fmt"
	"io"
	"log"
	"net"
	"os"
	"strconv"
	"strings"
	"sync"
	"sync/atomic"
	"time"
)

var errTooLarge = errors.New("stream exceeded maximum byte limit")

type Server struct {
	port     int
	handlers map[string]handler
	stopped  *atomic.Bool
	listener net.Listener
	wg       sync.WaitGroup
	workers  int
	queue    chan net.Conn
}

type handler func(args []string) (string, error)

func New(port int, workers int) *Server {
	return &Server{
		port:     port,
		handlers: make(map[string]handler),
		workers:  workers,
		queue:    make(chan net.Conn, workers),
		stopped:  &atomic.Bool{},
	}
}

func (s *Server) Register(op string, handle func(args []string) (string, error)) {
	s.handlers[op] = handle
}

func (s *Server) Start() error {
	log.Println("Starting server on port", s.port)
	listener, err := net.Listen("tcp", ":"+strconv.Itoa(s.port))
	if err != nil {
		log.Fatal("Error when start listening", err)
	}
	s.listener = listener

	for range s.workers {
		go s.worker(s.queue)
	}

	log.Println("Server started")
	for !s.stopped.Load() {
		clientConn, err := listener.Accept()
		if err != nil {
			if s.stopped.Load() {
				// expected behavior
				break
			}
			log.Println("Error when accepting connection", err)
			continue
		}
		s.queue <- clientConn
	}
	close(s.queue)

	return nil
}

func (s *Server) worker(q chan net.Conn) {
	s.wg.Add(1)
	defer s.wg.Done()

	for conn := range q {
		s.handle(conn)
	}
}

func (s *Server) handle(clientConn net.Conn) {
	defer clientConn.Close()

	for {
		clientConn.SetReadDeadline(time.Now().Add(5 * time.Minute))
		line, err := readLine(clientConn, 10*1024)
		if err != nil {
			if errors.Is(err, errTooLarge) {
				log.Println("Request too large", err)
				clientConn.Write([]byte("error 1: request too large\n"))
			} else if errors.Is(err, io.EOF) {
				log.Println("Client disconnected")
			} else if errors.Is(err, os.ErrDeadlineExceeded) {
				log.Println("Connection timed out")
			} else {
				log.Println("Error when reading command", err)
			}
			return
		}

		log.Println("Request", line)
		response, err := s.processCommand(line)
		if err != nil {
			log.Println("Error when processing command", err)
			clientConn.Write([]byte("error 100: " + err.Error() + "\n"))
			continue
		}
		log.Println("Response", response)
		_, err = clientConn.Write([]byte(response + "\n"))
		if err != nil {
			log.Println("Error when writing response", err)
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

func (s *Server) Stop(timeout time.Duration) {
	log.Println("Stopping server...")
	s.stopped.Store(true)
	err := s.listener.Close()
	if err != nil {
		log.Println("Error when closing listener", err)
	}

	log.Println("Waiting for workers to stop...")
	ctx, cancel := context.WithTimeout(context.Background(), timeout)
	defer cancel()

	closeCh := make(chan struct{})
	go func() {
		s.wg.Wait()
		close(closeCh)
	}()

	select {
	case <-closeCh:
		log.Println("Workers stopped")
	case <-ctx.Done():
		log.Println("Timeout reached, forcing shutdown")
	}
}

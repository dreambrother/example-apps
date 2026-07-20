package singlethreaded

import (
	"context"
	"log"
	"time"
)

type cmdType int

const (
	opGet cmdType = iota
	opSet
)

type SingleThreadedStorage struct {
	requests chan request
	results  chan result
	done     chan struct{}
	storage  map[string]string
}

type request struct {
	op  cmdType
	key string
	val string
}

type result struct {
	val string
}

func NewSingleThreadedStorage(ctx context.Context) *SingleThreadedStorage {
	s := &SingleThreadedStorage{
		requests: make(chan request),
		results:  make(chan result),
		done:     make(chan struct{}),
		storage:  make(map[string]string),
	}
	s.start(ctx)
	return s
}

func (s *SingleThreadedStorage) start(ctx context.Context) {
	go func() {
		<-ctx.Done()
		close(s.requests)
	}()

	// worker
	go func() {
		defer close(s.results)
		defer close(s.done)
		for r := range s.requests {
			switch r.op {
			case opGet:
				s.results <- result{val: s.storage[r.key]}
			case opSet:
				s.storage[r.key] = r.val
				s.results <- result{}
			}
		}
	}()
}

func (s *SingleThreadedStorage) Get(key string) string {
	s.requests <- request{op: opGet, key: key}
	r := <-s.results
	return r.val
}

func (s *SingleThreadedStorage) Set(key, value string) {
	s.requests <- request{op: opSet, key: key, val: value}
	<-s.results
}

func (s *SingleThreadedStorage) WaitForStop(timeout time.Duration) {
	select {
	case <-s.done:
		log.Println("Storage stopped")
	case <-time.After(timeout):
		log.Println("Storage stop timeout reached")
	}
}

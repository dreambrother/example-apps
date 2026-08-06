package rwmutex

import (
	"sync"
	"time"
)

type RWMutexStorage struct {
	m       *sync.RWMutex
	storage map[string]string
}

func New() *RWMutexStorage {
	return &RWMutexStorage{
		m:       &sync.RWMutex{},
		storage: make(map[string]string),
	}
}

func (s *RWMutexStorage) Get(key string) string {
	s.m.RLock()
	defer s.m.RUnlock()
	return s.storage[key]
}

func (s *RWMutexStorage) Set(key, value string) {
	s.m.Lock()
	defer s.m.Unlock()
	s.storage[key] = value
}

func (s *RWMutexStorage) WaitForStop(timeout time.Duration) {
	// nothing to do
}

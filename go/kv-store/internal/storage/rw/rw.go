package rw

import (
	"sync"
	"time"
)

type RWStorage struct {
	m       *sync.RWMutex
	storage map[string]string
}

func New() *RWStorage {
	return &RWStorage{
		m:       &sync.RWMutex{},
		storage: make(map[string]string),
	}
}

func (rws *RWStorage) Get(key string) string {
	rws.m.RLock()
	defer rws.m.RUnlock()
	return rws.storage[key]
}

func (rws *RWStorage) Set(key, value string) {
	rws.m.Lock()
	defer rws.m.Unlock()
	rws.storage[key] = value
}

func (rws *RWStorage) WaitForStop(timeout time.Duration) {
	// nothing to do
}

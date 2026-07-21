package sharded

import (
	"hash"
	"hash/fnv"
	"sync"
	"time"
)

type ShardedMutexStorage struct {
	concurrency int
	mutexes     []*sync.Mutex
	hashers     *sync.Pool
	storage     map[string]string
}

func New(concurrency int) *ShardedMutexStorage {
	if concurrency <= 0 {
		concurrency = 1
	}
	mutexes := make([]*sync.Mutex, concurrency)
	for i := range mutexes {
		mutexes[i] = &sync.Mutex{}
	}
	return &ShardedMutexStorage{
		concurrency: concurrency,
		mutexes:     mutexes,
		hashers:     &sync.Pool{New: func() any { return fnv.New64a() }},
		storage:     make(map[string]string),
	}
}

func (s *ShardedMutexStorage) Get(key string) string {
	m := s.getMutex(key)
	defer m.Unlock()
	m.Lock()
	return s.storage[key]
}

func (s *ShardedMutexStorage) Set(key, value string) {
	m := s.getMutex(key)
	defer m.Unlock()
	m.Lock()
	s.storage[key] = value
}

func (s *ShardedMutexStorage) getMutex(key string) *sync.Mutex {
	hasher := s.hashers.Get().(hash.Hash64)
	defer s.hashers.Put(hasher)

	hasher.Reset()
	h, err := hasher.Write([]byte(key))
	index := 0
	if err == nil {
		index = h % s.concurrency
	}
	return s.mutexes[index]
}

func (s *ShardedMutexStorage) WaitForStop(timeout time.Duration) {
	// nothing to do
}

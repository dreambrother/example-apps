package singlethreaded

import (
	"context"
	"testing"
	"time"
)

func TestSingleThreadedStorage_SetAndGet(t *testing.T) {
	withStorage(func(s *SingleThreadedStorage) {
		s.Set("key1", "value1")
		s.Set("key2", "value2")

		if got := s.Get("key1"); got != "value1" {
			t.Errorf("Get(key1) = %q, want %q", got, "value1")
		}

		if got := s.Get("key2"); got != "value2" {
			t.Errorf("Get(key2) = %q, want %q", got, "value2")
		}

		if got := s.Get("missing"); got != "" {
			t.Errorf("Get(missing) = %q, want empty string", got)
		}
	})
}

func TestSingleThreadedStorage_Override(t *testing.T) {
	withStorage(func(s *SingleThreadedStorage) {
		s.Set("k1", "v1")
		s.Set("k1", "v2")

		if got := s.Get("k1"); got != "v2" {
			t.Errorf("Get(k1) = %q, want %q", got, "v2")
		}
	})
}

func withStorage(f func(s *SingleThreadedStorage)) {
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	s := NewSingleThreadedStorage(ctx)
	f(s)
	cancel()
	s.WaitForStop(1 * time.Second)
}

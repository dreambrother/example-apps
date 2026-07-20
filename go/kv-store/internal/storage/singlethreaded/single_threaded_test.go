package singlethreaded

import (
	"context"
	"testing"
	"time"
)

func TestSingleThreadedStorage_SetAndGet(t *testing.T) {
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()

	s := NewSingleThreadedStorage(ctx)

	s.Set("key1", "value1")
	s.Set("key2", "value2")

	if got := s.Get("key1"); got != "value1" {
		t.Errorf("Get(\"key1\") = %q, want %q", got, "value1")
	}

	if got := s.Get("key2"); got != "value2" {
		t.Errorf("Get(\"key2\") = %q, want %q", got, "value2")
	}

	if got := s.Get("missing"); got != "" {
		t.Errorf("Get(\"missing\") = %q, want empty string", got)
	}

	cancel()
	s.WaitForStop(2 * time.Second)
}

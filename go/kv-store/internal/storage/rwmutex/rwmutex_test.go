package rwmutex

import (
	"testing"
)

func TestRWMutexStorage_SetAndGet(t *testing.T) {
	s := New()

	s.Set("k1", "v1")
	s.Set("k2", "v2")

	got := s.Get("k1")
	if wanted := "v1"; got != wanted {
		t.Errorf("Get(k1) expected %s, got %s", wanted, got)
	}

	got = s.Get("k2")
	if wanted := "v2"; got != wanted {
		t.Errorf("Get(k2) expected %s, got %s", wanted, got)
	}
}

func TestRWMutexStorage_Override(t *testing.T) {
	s := New()

	s.Set("k1", "v1")
	s.Set("k1", "v2")

	got := s.Get("k1")
	if wanted := "v2"; got != wanted {
		t.Errorf("Get(k1) expected %s, got %s", wanted, got)
	}
}
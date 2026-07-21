package rw

import (
	"testing"
)

func TestRWStorage_SetAndGet(t *testing.T) {
	s := New()

	s.Set("k1", "v1")
	s.Set("k2", "v2")

	got := s.Get("k1")
	if waiting := "v1"; got != waiting {
		t.Errorf("Get(k1) expected %s, got %s", waiting, got)
	}

	got = s.Get("k2")
	if waiting := "v2"; got != waiting {
		t.Errorf("Get(k2) expected %s, got %s", waiting, got)
	}
}

func TestRWStorage_Override(t *testing.T) {
	s := New()

	s.Set("k1", "v1")
	s.Set("k1", "v2")

	got := s.Get("k1")
	if waiting := "v2"; got != waiting {
		t.Errorf("Get(k1) expected %s, got %s", waiting, got)
	}
}

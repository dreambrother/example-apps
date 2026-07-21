package sharded

import "testing"

func TestShardedMutexStorage_SetAndGet(t *testing.T) {
	s := New(4)

	s.Set("k1", "v1")
	s.Set("k2", "v2")

	got := s.Get("k1"); wanted := "v1"; if got != wanted {
		t.Errorf("Get(k1)=%s, wanted %s", got, wanted)
	}

	got = s.Get("k2"); wanted = "v2"; if got != wanted {
		t.Errorf("Get(k2)=%s, wanted %s", got, wanted)
	}
}
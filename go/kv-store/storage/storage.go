package storage

import (
	"time"
)

type Storage interface {
	Get(key string) string
	Set(key, value string)
	WaitForStop(timeout time.Duration)
}

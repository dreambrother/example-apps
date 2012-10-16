package com.blogspot.nikcode.spring.cache;

import org.springframework.cache.annotation.Cacheable;

public interface Calculator {

    int longOperation(int x1);
}

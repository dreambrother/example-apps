package com.blogspot.nikcode.spring.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("calculator")
public class CalculatorImpl implements Calculator {

    @Cacheable("default")
    @Override
    public int longOperation(int x1) {
        System.out.println("compute");
        return x1;
    }
}

package com.github.dreambrother.examples.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    public void foo(String foo, String bar) {
        logger.info("Executing foo {} {}", foo, bar);
    }

    public void bar(String foo, String bar) {
        logger.info("Executing bar {} {}", foo, bar);
    }

    public void exception() {
        throw new RuntimeException("OK");
    }
}

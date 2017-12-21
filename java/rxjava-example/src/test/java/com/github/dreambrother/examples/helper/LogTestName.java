package com.github.dreambrother.examples.helper;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

@Slf4j
public class LogTestName extends TestWatcher {

    @Override
    protected void starting( Description description ) {
        log.info("------- {} started --------", description.getMethodName());
    }
}

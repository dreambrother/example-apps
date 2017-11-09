package com.github.dreambrother.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import rx.Observable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FromTest {

    @Rule
    public LogTestName logTestName = new LogTestName();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Test
    public void testFromArray() {
        Observable.from(new String[]{"1", "2"})
                .subscribe(log::info);
    }

    @Test
    public void testFromFuture() {
        Observable.from(executorService.submit(() -> "3"))
                .subscribe(log::info);
    }

    @Test
    public void testFromCallable() {
        Observable<String> observable = Observable.fromCallable(() -> RandomStringUtils.randomNumeric(2));
        observable.subscribe(val -> log.info("First get {}", val));
        observable.subscribe(val -> log.info("Second get {}", val));
    }

    @Test
    public void testFromCachedCallable() {
        Observable<String> observable = Observable.fromCallable(() -> RandomStringUtils.randomNumeric(2))
                .cache();
        observable.subscribe(val -> log.info("First get {}", val));
        observable.subscribe(val -> log.info("Second get {}", val));
    }
}

package com.github.dreambrother.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import rx.*;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CreateTest {

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

    @Test
    public void testJust() {
        Observable.just("4")
                .subscribe(log::info);
    }

    @Test
    public void testCreate() {
        Observable.create((Emitter<String> emitter) -> {
            emitter.onNext("5");
            emitter.onNext("6");
            emitter.onCompleted();
        }, Emitter.BackpressureMode.BUFFER)
                .subscribe(log::info);
    }

    @Test
    public void testDoOnNext() {
        Observable.from(Arrays.asList(1, 2, 3))
                .doOnNext(val -> log.info("doOnNext {}", val))
                .map(String::valueOf)
                .subscribe(log::info);
    }

    @Test
    public void testSingle() {
        Single.create(subscriber -> subscriber.onSuccess(5))
                .map(String::valueOf)
                .subscribe(log::info);
    }

    @Test
    public void testCompletable() {
        Completable.create(CompletableSubscriber::onCompleted)
                .subscribe(() -> log.info("Completed"));
    }
}

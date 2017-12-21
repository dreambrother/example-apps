package com.github.dreambrother.examples;

import com.github.dreambrother.examples.helper.LogTestName;
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
    public void testFromCallableError() {
        Observable.fromCallable(() -> {
            throw new RuntimeException("test");
        }).map(String::valueOf)
                .subscribe(log::info, this::handleException);
    }

    @Test
    public void testUnsafeCreateError() {
        Observable.unsafeCreate(subscriber -> {
            throw new RuntimeException("ok");
        }).map(Object::toString)
                .subscribe(log::info, this::handleException);
    }

    @Test
    public void testCreateWithEmitterError() {
        Observable.create((Emitter<String> emitter) -> {
            throw new RuntimeException("ok");
        }, Emitter.BackpressureMode.BUFFER)
                .subscribe(log::info, this::handleException);
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

    @Test
    public void testInfinite() throws InterruptedException {
        Observable<String> observable = Observable.unsafeCreate(subscriber -> new Thread(
                () -> {
                    log.info("Create observable");

                    int count = 0;
                    while (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(count++);
                    }
                }).start())
                .map(String::valueOf);

        Subscription subscription1 = observable.subscribe(log::info);
        Subscription subscription2 = observable.subscribe(log::info);

        Thread.sleep(1);
        subscription1.unsubscribe();

        Thread.sleep(1);
        subscription2.unsubscribe();
    }

    @Test
    public void testUnhandledException() {
        Completable.complete()
                .subscribe(() -> { throw new RuntimeException("OK"); }); // will not be thrown by subscribe() method
    }

    private void handleException(Throwable ex) {
        log.error("Caught", ex);
    }
}

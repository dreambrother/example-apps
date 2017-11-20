package com.github.dreambrother.examples;

import com.github.dreambrother.examples.subject.EventsGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import rx.Emitter;
import rx.Observable;
import rx.subjects.PublishSubject;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SubjectTest {

    @Test
    public void testSubject() throws InterruptedException {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(val -> log.info("First subscriber got: {}", val));
        subject.subscribe(val -> log.info("Second subscriber got: {}", val));

        new EventsGenerator(subject).start();
        // 1 thread will be created

        Thread.sleep(5000L);
    }

    @Test
    public void testSubjectWithEmitter() throws InterruptedException {
        Observable<String> observable = PublishSubject.create((Emitter<String> emitter) ->
                Executors.newSingleThreadScheduledExecutor()
                        .scheduleAtFixedRate(() -> emitter.onNext(RandomStringUtils.randomAlphabetic(5)), 0, 1, TimeUnit.SECONDS), Emitter.BackpressureMode.DROP);

        observable.subscribe(log::info);
        observable.subscribe(log::info);
        // 2 threads will be created

        Thread.sleep(5000L);
    }
}

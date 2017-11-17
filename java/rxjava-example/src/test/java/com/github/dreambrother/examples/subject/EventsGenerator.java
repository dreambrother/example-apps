package com.github.dreambrother.examples.subject;

import org.apache.commons.lang3.RandomStringUtils;
import rx.Observer;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EventsGenerator {

    private final Observer<String> observer;

    public EventsGenerator(Observer<String> observer) {
        this.observer = observer;
    }

    public void start() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> observer.onNext(RandomStringUtils.randomAlphabetic(5)), 0, 1, TimeUnit.SECONDS);
    }
}

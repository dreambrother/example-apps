package com.github.dreambrother.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConnectedObservableTest {

    @Test
    public void testPublishWithRefCount() throws InterruptedException {
        Observable<String> observable = Observable.unsafeCreate(subscriber -> {
            log.info("Creating observable");
            ScheduledFuture<?> scheduledFuture = scheduleWith1SecPeriod(subscriber);
            subscriber.add(Subscriptions.create(() -> scheduledFuture.cancel(true)));
        }).publish()
                .refCount()
                .map(String::valueOf);

        Subscription subscription1 = observable.subscribe(log::info);
        Subscription subscription2 = observable.subscribe(log::info);
        // 1 thread will be created

        Thread.sleep(5000L);

        subscription1.unsubscribe();
        subscription2.unsubscribe();
        log.info("Unsubscribed");

        Thread.sleep(2000L);

        observable.subscribe(log::info);
        // new pool will be created

        Thread.sleep(2000L);
    }

    @Test
    public void testPublishWithConnect() throws InterruptedException {
        ConnectableObservable<String> observable = Observable.unsafeCreate(subscriber -> {
            log.info("Creating observable");
            ScheduledFuture<?> scheduledFuture = scheduleWith1SecPeriod(subscriber);
            subscriber.add(Subscriptions.create(() -> scheduledFuture.cancel(true)));
        }).map(String::valueOf).publish();

        Subscription subscription1 = observable.subscribe(log::info);
        Subscription subscription2 = observable.subscribe(log::info);
        // 0 events will be produced
        log.info("Subscribed");

        Thread.sleep(1000L);

        observable.connect();
        log.info("Connected");

        Thread.sleep(5000L);

        subscription1.unsubscribe();
        subscription2.unsubscribe();
        log.info("Unsubscribed");
        // Generation will be continued after unsubscription

        Thread.sleep(2000L);
    }

    @Test
    public void testUnsubscribeFromConnectAndReconnect() throws InterruptedException {
        ConnectableObservable<String> observable = Observable.unsafeCreate(subscriber -> {
            log.info("Creating observable");
            ScheduledFuture<?> scheduledFuture = scheduleWith1SecPeriod(subscriber);
            subscriber.add(Subscriptions.create(() -> scheduledFuture.cancel(true)));
        }).map(String::valueOf).publish();

        Subscription subscription = observable.connect();
        log.info("Subscribed");
        Thread.sleep(2000L);

        subscription.unsubscribe();
        log.info("Unsubscribed");
        Thread.sleep(2000L);

        observable.connect();
        log.info("Subscribed again");
        Thread.sleep(2000L);
    }

    private ScheduledFuture<?> scheduleWith1SecPeriod(Subscriber<? super Object> subscriber) {
        return Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    String rndString = RandomStringUtils.randomAlphanumeric(5);
                    log.info("Generated {}", rndString);
                    subscriber.onNext(rndString);
                }, 0, 1, TimeUnit.SECONDS);
    }

}

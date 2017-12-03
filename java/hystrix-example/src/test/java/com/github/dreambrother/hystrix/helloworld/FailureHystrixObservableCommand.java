package com.github.dreambrother.hystrix.helloworld;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

public class FailureHystrixObservableCommand extends HystrixObservableCommand<String> {

    public FailureHystrixObservableCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(subscriber -> {
            subscriber.onNext("First value");
            subscriber.onNext("Next value");
            subscriber.onError(new IllegalStateException("OK"));
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.just("Fallback");
    }
}

package com.github.dreambrother.hystrix.helloworld;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import rx.Observable;

public class NonRecoverableFailureHystrixObservableCommand extends HystrixObservableCommand<String> {

    public NonRecoverableFailureHystrixObservableCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(subscriber -> {
            subscriber.onNext("Before non recoverable");
            subscriber.onError(new HystrixBadRequestException("Non recoverable"));
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.just("Fallback");
    }
}

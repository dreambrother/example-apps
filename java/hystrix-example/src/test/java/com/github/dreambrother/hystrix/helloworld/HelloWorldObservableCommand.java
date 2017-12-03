package com.github.dreambrother.hystrix.helloworld;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

public class HelloWorldObservableCommand extends HystrixObservableCommand<String> {

    private String name;

    public HelloWorldObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.just("Hello " + name + "!");
    }
}

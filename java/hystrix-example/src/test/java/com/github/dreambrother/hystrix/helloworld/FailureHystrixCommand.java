package com.github.dreambrother.hystrix.helloworld;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FailureHystrixCommand extends HystrixCommand<String> {

    private String name;

    public FailureHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!";
    }
}

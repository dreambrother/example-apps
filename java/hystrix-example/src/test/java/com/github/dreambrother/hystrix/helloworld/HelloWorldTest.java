package com.github.dreambrother.hystrix.helloworld;

import lombok.SneakyThrows;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void testHelloWorldCommand() {
        System.out.println(new HelloWorldCommand("Hystrix").execute());
    }

    @Test
    public void testHelloWorldObservableCommand() {
        new HelloWorldObservableCommand("Observable Hystrix")
                .toObservable()
                .subscribe(System.out::println);
    }

    @Test
    @SneakyThrows
    public void testSyncHelloWorldObservableCommand() {
        System.out.println(new HelloWorldObservableCommand("Sync Observable Hystrix")
                .toObservable()
                .toBlocking()
                .toFuture()
                .get());
    }

    @Test
    @SneakyThrows
    public void testAsyncHelloWorldCommand() {
        System.out.println(new HelloWorldCommand("Sync Hystrix")
                .queue()
                .get());
    }

    @Test
    public void testFallback() {
        System.out.println(new FailureHystrixCommand("Hystrix").execute());
    }

    @Test
    public void testObservableFallback() {
        new FailureHystrixObservableCommand()
                .toObservable()
                .subscribe(System.out::println);
    }

    @Test
    public void testObservableNonRecoverableFallback() {
        new NonRecoverableFailureHystrixObservableCommand()
                .toObservable()
                .subscribe(System.out::println, System.err::println);
    }
}

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
                .construct()
                .subscribe(System.out::println);
    }

    @Test
    @SneakyThrows
    public void testSyncHelloWorldObservableCommand() {
        System.out.println(new HelloWorldObservableCommand("Sync Observable Hystrix")
                .construct()
                .toBlocking()
                .toFuture()
                .get());
    }
}

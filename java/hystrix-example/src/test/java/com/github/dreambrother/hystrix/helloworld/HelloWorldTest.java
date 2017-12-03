package com.github.dreambrother.hystrix.helloworld;

import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void testHelloWorldCommand() {
        System.out.println(new HelloWorldCommand("Hystrix").run());
    }

    @Test
    public void testHelloWorldObservableCommand() {
        new HelloWorldObservableCommand("Observable Hystrix")
                .construct()
                .subscribe(System.out::println);
    }
}

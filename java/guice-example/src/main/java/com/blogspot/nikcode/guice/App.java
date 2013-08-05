package com.blogspot.nikcode.guice;

import com.blogspot.nikcode.guice.service.AnotherService;
import com.blogspot.nikcode.guice.service.AnotherServiceImpl;
import com.blogspot.nikcode.guice.service.ExampleService;
import com.blogspot.nikcode.guice.service.ExampleServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * User: nik
 * Date: 2/19/13
 * Time: 1:11 AM
 */
public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ExampleService.class).to(ExampleServiceImpl.class);
                bind(AnotherService.class).to(AnotherServiceImpl.class);
            }
        });
        AnotherService anotherService = injector.getInstance(AnotherService.class);
        System.out.println(anotherService.getAnotherExample());
    }
}

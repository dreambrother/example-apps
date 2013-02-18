package com.blogspot.nikcode.guice.service;

import javax.inject.Inject;

/**
 * User: nik
 * Date: 2/19/13
 * Time: 1:15 AM
 */
public class AnotherServiceImpl implements AnotherService {

    @Inject
    private ExampleService exampleService;

    @Override
    public String getAnotherExample() {
        return "Another " + exampleService.getExample();
    }
}

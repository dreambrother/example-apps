package com.blogspot.nikcode.camel;

/**
 *
 */
public class TestEndpointImpl implements TestEndpoint {

    @Override
    public void process(String string) {
        System.out.println(string);
    }
}

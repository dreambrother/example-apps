package com.blogspot.nikcode.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *
 * @author nik
 */
public class FailProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        throw new IllegalStateException("Cannot process " + exchange);
    }
}

package com.blogspot.nikcode.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author nik
 */
public class App {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:endpoint1")
                        .to("stream:out")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                exchange.getIn().setBody("Changed message body");
                            }
                        })
                        .to("stream:out");
            }
        });
        camelContext.start();
        camelContext.createProducerTemplate().sendBody("direct:endpoint1", "Hello, Camel!");
        camelContext.stop();
    }
}

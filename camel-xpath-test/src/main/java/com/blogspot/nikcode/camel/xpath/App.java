package com.blogspot.nikcode.camel.xpath;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class App {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:endpoint")
                        .process(new Processor() {
                            
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                exchange.getIn().setBody(xpath("/body/*").evaluate(exchange, String.class));
                            }
                        })
                        .split().tokenizeXML("item")
                        .to("stream:out");
            }
        });
        
        camelContext.start();
        camelContext.createProducerTemplate().sendBody("direct:endpoint", "<body><items><item>1</item><item>2</item></items></body>");
    }
}

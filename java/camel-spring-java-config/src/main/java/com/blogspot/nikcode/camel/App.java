package com.blogspot.nikcode.camel;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CamelContext camelContext = applicationContext.getBean(CamelContext.class);
        camelContext.createProducerTemplate().sendBody("direct:endpoint", "Hello Spring Java config for Camel!");
    }
}

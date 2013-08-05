package com.blogspot.nikcode.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class AppConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public TestEndpoint testEndpoint() {
        return new TestEndpointImpl();
    }

    @Bean
    public CamelContext camelContext() throws Exception {
        SpringCamelContext springCamelContext = new SpringCamelContext(applicationContext);
        springCamelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:endpoint").beanRef("testEndpoint", "process");
            }
        });
        return springCamelContext;
    }
}

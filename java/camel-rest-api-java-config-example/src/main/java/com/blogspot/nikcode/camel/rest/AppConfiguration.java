package com.blogspot.nikcode.camel.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.restlet.RestletComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.restlet.Component;
import org.restlet.ext.spring.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class AppConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Component restletComponent() {
        return new SpringComponent();
    }

    @Bean
    public RestletComponent restletComponentService() {
        return new RestletComponent(restletComponent());
    }

    @Bean
    public ItemEndpoint itemEndpoint() {
        return new ItemEndpoint();
    }

    @Bean
    public CamelContext camelContext() throws Exception {
        SpringCamelContext springCamelContext = new SpringCamelContext(applicationContext);
        springCamelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                JacksonDataFormat itemMarshaller = new JacksonDataFormat(Item.class);
                from("restlet:/items?restletMethod=GET")
                        .bean(itemEndpoint(), "getAll")
                        .marshal(itemMarshaller);
                from("restlet:/items?restletMethod=POST")
                        .unmarshal(itemMarshaller)
                        .bean(itemEndpoint(), "save");
                from("restlet:/item/{itemId}?restletMethod=GET")
                        .bean(itemEndpoint(), "getById(${header.itemId})")
                        .marshal(itemMarshaller);
            }
        });
        return springCamelContext;
    }
}

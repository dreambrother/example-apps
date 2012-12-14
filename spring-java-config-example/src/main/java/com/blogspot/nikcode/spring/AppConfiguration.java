package com.blogspot.nikcode.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author nik
 */
@Configuration
@EnableWebMvc
public class AppConfiguration {

    @Bean
    public TextGenerator textGenerator() {
        return new SimpleTextGenerator();
    }
    
    @Bean
    public AppController appController() {
        return new AppController();
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Bean
    public RequestToViewNameTranslator requestToViewNameTranslator() {
        return new DefaultRequestToViewNameTranslator();
    }
}

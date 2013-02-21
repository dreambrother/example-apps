package com.blogspot.nikcode.groovy

import org.fusesource.scalate.spring.view.ScalateViewResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

/**
 *
 */
@Configuration
@EnableWebMvc
class AppConfig {

    @Bean
    ExampleController exampleController() { // Type specification is necessary for controllers
        return new ExampleController(service: exampleService())
    }

    @Bean
    def exampleService() {
        return new ExampleService()
    }

    @Bean
    def scalateViewResolver() {
        ScalateViewResolver viewResolver = new ScalateViewResolver()
        viewResolver.setPrefix("/WEB-INF/views/")
        viewResolver.setSuffix(".mustache")
        return viewResolver
    }
}

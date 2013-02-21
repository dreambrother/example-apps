package com.blogspot.nikcode.groovy

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.thymeleaf.spring3.SpringTemplateEngine
import org.thymeleaf.spring3.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ServletContextTemplateResolver

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
    def thymeleafTemplateResolver() {
        return new ServletContextTemplateResolver(
                prefix: "/WEB-INF/views/",
                suffix: ".html",
                templateMode: "HTML5"
        )
    }

    @Bean
    def thymeleafTemplateEngine() {
        return new SpringTemplateEngine(templateResolver: thymeleafTemplateResolver())
    }

    @Bean
    def thymeleafViewResolver() {
        return new ThymeleafViewResolver(templateEngine: thymeleafTemplateEngine())
    }
}

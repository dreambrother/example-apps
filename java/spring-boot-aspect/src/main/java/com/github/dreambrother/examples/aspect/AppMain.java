package com.github.dreambrother.examples.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class AppMain {

    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppMain.class);

        TestService testService = applicationContext.getBean(TestService.class);
        testService.foo("1", "2");
        testService.bar("3", "4");
        try {
            testService.exception();
        } catch (Exception ex) {
            logger.info("Exception is catched {}", ex.getMessage());
        }
    }

    @Bean
    public TestService testService() {
        return new TestService();
    }

    @Bean
    public TestAspect testAspect() {
        return new TestAspect();
    }
}

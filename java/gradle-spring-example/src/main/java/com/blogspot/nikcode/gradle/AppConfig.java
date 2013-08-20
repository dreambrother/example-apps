package com.blogspot.nikcode.gradle;

import com.blogspot.nikcode.gradle.service.SumService;
import com.blogspot.nikcode.gradle.service.SumServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SumService sumService() {
        return new SumServiceImpl();
    }
}

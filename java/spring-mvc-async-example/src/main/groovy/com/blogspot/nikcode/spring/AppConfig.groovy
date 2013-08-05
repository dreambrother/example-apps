package com.blogspot.nikcode.spring

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
    AsyncController asyncController() {
        new AsyncController()
    }

    @Bean
    SyncController syncController() {
        new SyncController()
    }
}

package com.blogspot.nikcode.configuration;

import com.blogspot.nikcode.controller.ItemController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: nik
 * Date: 3/19/13
 * Time: 12:42 AM
 */
@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ItemController itemController() {
        return new ItemController();
    }
}

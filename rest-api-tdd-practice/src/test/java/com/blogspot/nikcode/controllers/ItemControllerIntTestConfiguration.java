package com.blogspot.nikcode.controllers;

import com.blogspot.nikcode.configuration.WebConfiguration;
import com.blogspot.nikcode.services.ItemService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * User: nik
 * Date: 3/19/13
 * Time: 12:27 AM
 */
@Configuration
@Import(WebConfiguration.class)
public class ItemControllerIntTestConfiguration {

    @Bean
    public ItemService itemService() {
        return Mockito.mock(ItemService.class);
    }
}

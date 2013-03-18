package com.blogspot.nikcode.configuration;

import com.blogspot.nikcode.dao.ItemDao;
import com.blogspot.nikcode.dao.ItemDaoImpl;
import com.blogspot.nikcode.services.ItemService;
import com.blogspot.nikcode.services.ItemServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: nik
 * Date: 3/19/13
 * Time: 12:40 AM
 */
@Configuration
public class AppConfiguration {

    @Bean
    public ItemDao itemDao() {
        return new ItemDaoImpl();
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemDao());
    }
}

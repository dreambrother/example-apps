package com.blogspot.nikcode.spring.cache;

import com.blogspot.nikcode.spring.cache.dao.TransactionDao;
import com.blogspot.nikcode.spring.cache.dao.TransactionDaoImpl;
import java.util.Collections;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author nik
 */
@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    public TransactionDao transactionDao() {
        return new TransactionDaoImpl();
    }
    
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singleton(new ConcurrentMapCache("transactions")));
        return cacheManager;
    }
}

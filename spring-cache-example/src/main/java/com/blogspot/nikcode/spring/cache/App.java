package com.blogspot.nikcode.spring.cache;

import com.blogspot.nikcode.spring.cache.dao.TransactionDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        TransactionDao txDao = applicationContext.getBean(TransactionDao.class);
        
        for (int i = 0; i < 10000; i++) {
            txDao.save(new Transaction(i, i + 1000));
        }
        
        long start = System.nanoTime();
        txDao.getById(100000);
        long end = System.nanoTime();
        
        System.out.println("Search without cache: " + (end - start));
        
        start = System.nanoTime();
        txDao.getById(100000);
        end = System.nanoTime();
        
        System.out.println("Search from cache: " + (end - start));
        
        System.out.println("Amount of the tx: " + txDao.getById(1L).getAmount());
        txDao.update(new Transaction(1, 5000L));
        System.out.println("Amount of the tx: " + txDao.getById(1L).getAmount());
    }
}

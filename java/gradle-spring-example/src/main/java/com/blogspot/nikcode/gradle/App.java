package com.blogspot.nikcode.gradle;

import com.blogspot.nikcode.gradle.service.SumService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SumService sumService = context.getBean(SumService.class);
        System.out.println("Sum of 2 and 105 is " + sumService.sum(2, 105));
    }
}

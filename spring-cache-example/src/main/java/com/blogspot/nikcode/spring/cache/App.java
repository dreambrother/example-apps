package com.blogspot.nikcode.spring.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-context.xml");
        Calculator calculator = appContext.getBean(Calculator.class);
        calculator.longOperation(1);
        calculator.longOperation(1);
    }
}

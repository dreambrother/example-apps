package com.blogspot.nikcode.generics;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class App {

    private List<String> stringList = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException {
        App app = new App();
        ParameterizedType type = (ParameterizedType) app.getClass().getDeclaredField("stringList").getGenericType();
        Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];

        System.out.println(clazz);

        List<Integer> list = new ArrayList<Integer>() {}; // create anonym class
        System.out.println(((ParameterizedType) list.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}


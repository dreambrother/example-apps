package com.github.dreambrother;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // filter
        System.out.println(ints.stream().filter((i) -> i > 5).collect(Collectors.toList()));
        // sum
        System.out.println(ints.stream().reduce(Integer::sum));
        // map
        System.out.println(ints.stream().map(i -> i.toString()).collect(Collectors.toList()));
    }
}

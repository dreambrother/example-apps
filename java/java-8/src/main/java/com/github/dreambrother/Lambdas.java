package com.github.dreambrother;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {

    public static void main(String[] args) {
        Lambdas lambdas = new Lambdas();
        System.out.println(lambdas.executeCallback(() -> "Hello, lambdas"));
        System.out.println(lambdas.executeCallback(lambdas::callbackRef));
        System.out.println(lambdas.filter(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), elem -> elem % 2 == 0));
        System.out.println(lambdas.functionalInterface((a, b) -> a + b));
        System.out.println(CustomFunction.dummy());
    }

    private <T> T executeCallback(Supplier<T> callback) {
        return callback.get();
    }

    private String callbackRef() {
        return "Hello, method reference";
    }

    private <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T elem : list) {
            if (predicate.test(elem)) {
                result.add(elem);
            }
        }
        return result;
    }

    private Integer functionalInterface(CustomFunction<Integer, Integer, Integer> function) {
        function.print();
        return function.call(5, 10);
    }

    @FunctionalInterface
    private interface CustomFunction<A, B, R> {

        R call(A a, B b);

        default void print() {
            System.out.println(toString());
        }

        static <A, B, R> CustomFunction<A, B, R> dummy() {
            return (a, b) -> null;
        }
    }
}

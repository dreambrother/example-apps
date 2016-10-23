package com.github.dreambrother.examples.simple;

import rx.Observable;

import java.util.Arrays;

public class SimpleExamples {

    public static void main(String[] args) {
        SimpleExamples examples = new SimpleExamples();
        examples.helloWorld();
        examples.sum();
        examples.getAllElements();
    }

    private void helloWorld() {
        printExampleName();
        Observable.from(Arrays.asList("Hello", "World"))
                .subscribe(System.out::println);
    }

    private void sum() {
        printExampleName();
        Observable.from(Arrays.asList(1, 2, 3, 4))
                .reduce((acc, elem) -> acc + elem)
                .subscribe(System.out::println);
    }

    private void getAllElements() {
        printExampleName();
        Observable.from(Arrays.asList("All", "words", "should", "be", "aggregated"))
                .toList()
                .map(words -> String.join(" ", words))
                .subscribe(System.out::println);
    }

    private void printExampleName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName(); // get calling function name
        System.out.println("-------- " + methodName + " --------");
    }
}

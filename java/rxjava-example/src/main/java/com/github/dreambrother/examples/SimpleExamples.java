package com.github.dreambrother.examples;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import rx.Observable;

import java.util.Arrays;

public class SimpleExamples {

    public static void main(String[] args) {
        SimpleExamples examples = printingFunctionNamesExamples(new SimpleExamples());
        examples.helloWorld();
        examples.sum();
        examples.getAllElements();
    }

    public void helloWorld() {
        Observable.from(Arrays.asList("Hello", "World"))
                .subscribe(System.out::println);
    }

    public void sum() {
        Observable.from(Arrays.asList(1, 2, 3, 4))
                .reduce((acc, elem) -> acc + elem)
                .subscribe(System.out::println);
    }

    public void getAllElements() {
        Observable.from(Arrays.asList("All", "words", "should", "be", "aggregated"))
                .toList()
                .map(words -> String.join(" ", words))
                .subscribe(System.out::println);
    }

    private static SimpleExamples printingFunctionNamesExamples(Object object) {
        return (SimpleExamples) Enhancer.create(SimpleExamples.class, (InvocationHandler) (proxy, method, args) -> {
            System.out.println("-------- " + method.getName() + " --------");
            return method.invoke(object, args);
        });
    }
}

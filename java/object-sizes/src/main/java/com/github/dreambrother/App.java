package com.github.dreambrother;

import java.util.concurrent.locks.ReentrantLock;

public class App {

    /**
     * Run with command
     *   java -javaagent:target/objects-sizes-1.0-SNAPSHOT.jar com.github.dreambrother.App
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Size of Object: " + ObjectSizeGetter.getObjectSize(new Object()));
        System.out.println("Size of String: " + ObjectSizeGetter.getObjectSize(""));
        System.out.println("Size of Integer: " + ObjectSizeGetter.getObjectSize(Integer.MAX_VALUE));
        System.out.println("Size of ReentrantLock: " + ObjectSizeGetter.getObjectSize(new ReentrantLock()));
    }
}

package com.github.dreambrother;

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
        System.out.println("Size of Array: " + ObjectSizeGetter.getObjectSize(new int[0]));
        System.out.println("Size of Foo: " + ObjectSizeGetter.getObjectSize(new Foo()));
    }
}

class Foo {

    public int integer = 10;
    public String string = "asd";
    public boolean bool = true;
    public Object obj = new Object();
}

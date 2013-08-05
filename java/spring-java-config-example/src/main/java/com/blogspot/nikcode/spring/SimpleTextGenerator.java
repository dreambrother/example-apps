package com.blogspot.nikcode.spring;

/**
 *
 * @author nik
 */
public class SimpleTextGenerator implements TextGenerator {

    @Override
    public String generate() {
        return "Hello. I am the simple text generator.";
    }
}

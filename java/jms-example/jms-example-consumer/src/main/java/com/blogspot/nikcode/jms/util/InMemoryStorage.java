package com.blogspot.nikcode.jms.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author nik
 */
public final class InMemoryStorage {

    private static final List<String> storage = new CopyOnWriteArrayList<>();
    
    private InMemoryStorage() { }
    
    public static void add(String value) {
        storage.add(value);
    }
    
    public static List<String> getValues() {
        return new ArrayList<>(storage);
    }
}

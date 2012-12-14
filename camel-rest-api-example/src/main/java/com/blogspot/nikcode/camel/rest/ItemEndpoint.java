package com.blogspot.nikcode.camel.rest;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author nik
 */
public class ItemEndpoint {

    private List<Item> items = new CopyOnWriteArrayList<>();
    
    public List<Item> getAll() {
        return Collections.unmodifiableList(items);
    }
    
    public Item getById(final long id) {
        return Collections2.filter(items, new Predicate<Item>() {

            @Override
            public boolean apply(Item input) {
                return input.getId() == id;
            }
        }).iterator().next();
    }
    
    public void save(Item item) {
        items.add(item);
    }
}

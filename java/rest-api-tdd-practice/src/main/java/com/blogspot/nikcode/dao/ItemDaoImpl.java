package com.blogspot.nikcode.dao;

import com.blogspot.nikcode.domain.Item;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: nik
 * Date: 3/19/13
 * Time: 12:03 AM
 */
public class ItemDaoImpl implements ItemDao {

    private AtomicLong generator = new AtomicLong(0L);
    private List<Item> storage = new CopyOnWriteArrayList<>();

    @Override
    public long save(Item item) {
        storage.add(item);
        return generator.incrementAndGet();
    }

    @Override
    public Item getById(Long id) {
        for (Item item : storage) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}

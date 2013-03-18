package com.blogspot.nikcode.dao;

import com.blogspot.nikcode.domain.Item;

/**
 * User: nik
 * Date: 3/18/13
 * Time: 11:47 PM
 */
public interface ItemDao {

    long save(Item item);

    Item getById(Long id);
}

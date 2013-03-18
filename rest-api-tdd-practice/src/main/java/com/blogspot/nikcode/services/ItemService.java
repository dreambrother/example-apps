package com.blogspot.nikcode.services;

import com.blogspot.nikcode.domain.Item;

/**
 * User: nik
 * Date: 3/18/13
 * Time: 11:37 PM
 */
public interface ItemService {

    void save(Item item);

    Item get(Long id);
}

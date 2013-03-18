package com.blogspot.nikcode.services;

import com.blogspot.nikcode.dao.ItemDao;
import com.blogspot.nikcode.domain.Item;
import com.blogspot.nikcode.exceptions.ItemNotFoundException;

/**
 * User: nik
 * Date: 3/18/13
 * Time: 11:39 PM
 */
public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public void save(Item item) {
        long id = itemDao.save(item);
        item.setId(id);
    }

    @Override
    public Item get(Long id) {
        Item foundItem = itemDao.getById(id);
        if (foundItem == null) {
            throw new ItemNotFoundException();
        }
        return foundItem;
    }
}

package com.blogspot.nikcode.dao;

import com.blogspot.nikcode.domain.Item;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: nik
 * Date: 3/18/13
 * Time: 11:59 PM
 */
public class ItemDaoTest {

    @Test
    public void shouldReturnIdAfterSave() {
        // given
        ItemDao itemDao = new ItemDaoImpl();
        Item item = new Item("item");

        // when
        long actual = itemDao.save(item);

        // then
        assertNotSame(0L, actual);
    }

    @Test
    public void shouldFindSavedItem() {
        ItemDao itemDao = new ItemDaoImpl();
        Item expected = new Item("item");

        long id = itemDao.save(expected);
        expected.setId(id);
        Item actual = itemDao.getById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNullWhenItemNotFound() {
        ItemDao itemDao = new ItemDaoImpl();
        Item actual = itemDao.getById(50L);
        assertNull(actual);
    }
}

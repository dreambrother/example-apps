package com.blogspot.nikcode.services;

import com.blogspot.nikcode.dao.ItemDao;
import com.blogspot.nikcode.domain.Item;
import com.blogspot.nikcode.exceptions.ItemNotFoundException;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * User: nik
 * Date: 3/18/13
 * Time: 11:34 PM
 */
public class ItemServiceTest {

    @Test
    public void shouldSetIdOfItemAfterSave() {
        // given
        ItemService sut = new ItemServiceImpl(mock(ItemDao.class));
        Item item = new Item("item");

        // when
        sut.save(item);

        // then
        assertNotNull(item.getId());
    }

    @Test
    public void shouldSaveItem() {
        ItemDao itemDaoMock = mock(ItemDao.class);
        ItemService sut = new ItemServiceImpl(itemDaoMock);
        final Item expected = new Item("expected");

        when(itemDaoMock.save(expected)).thenAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocationOnMock) throws Throwable {
                long id = 1L;
                expected.setId(id);
                return id;
            }
        });
        sut.save(expected);

        when(itemDaoMock.getById(expected.getId())).thenReturn(expected);
        Item actual = sut.get(expected.getId());

        assertEquals(expected, actual);
    }

    @Test(expected = ItemNotFoundException.class)
    public void shouldThrowItemNotFoundExceptionWhenItemNotFound() {
        ItemDao itemDaoMock = mock(ItemDao.class);
        ItemService sut = new ItemServiceImpl(itemDaoMock);

        long id = 50L;
        when(itemDaoMock.getById(id)).thenReturn(null);

        sut.get(id);
    }
}

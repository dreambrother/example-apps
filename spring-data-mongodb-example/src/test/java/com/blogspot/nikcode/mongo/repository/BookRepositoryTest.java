package com.blogspot.nikcode.mongo.repository;

import com.blogspot.nikcode.mongo.domain.Book;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author nik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repositories-context.xml")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    
    @After
    public void cleanUp() {
        bookRepository.deleteAll();
    }
    
    @Test
    public void testFindByTitle() {
        Book book1 = new Book("Book1");
        Book book2 = new Book("Book2");
        
        bookRepository.save(book1);
        bookRepository.save(book2);
        // verify id was generated
        assertNotNull(book1.getId());
        assertNotNull(book2.getId());
        
        Book book1FromDB = bookRepository.findByTitle(book1.getTitle());
        Book book2FromDB = bookRepository.findByTitle(book2.getTitle());
        assertEquals(book1, book1FromDB);
        assertEquals(book2, book2FromDB);
    }
}

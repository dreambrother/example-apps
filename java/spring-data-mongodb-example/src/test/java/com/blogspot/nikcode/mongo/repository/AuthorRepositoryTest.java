package com.blogspot.nikcode.mongo.repository;

import com.blogspot.nikcode.mongo.domain.Author;
import com.blogspot.nikcode.mongo.domain.Book;
import java.util.Arrays;
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
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @After
    public void cleanUp() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }
    
    @Test
    public void testSaveAndRead() {
        Book book1 = new Book("Book1");
        Book book2 = new Book("Book2");
        bookRepository.save(book1);
        bookRepository.save(book2);
        
        Author author1 = new Author();
        author1.setName("Author1");
        author1.setBooks(Arrays.asList(book1, book2));
        
        Book book3 = new Book("Book3");
        Book book4 = new Book("Book4");
        bookRepository.save(book3);
        bookRepository.save(book4);
        
        Author author2 = new Author();
        author2.setName("Author2");
        author2.setBooks(Arrays.asList(book3, book4));
        
        authorRepository.save(author1);
        authorRepository.save(author2);
        assertNotNull(author1.getId());
        assertNotNull(author2.getId());
        
        Author author1FromDB = authorRepository.findOne(author1.getId());
        Author author2FromDB = authorRepository.findOne(author2.getId());
        assertEquals(author1, author1FromDB);
        assertEquals(author2, author2FromDB);
    }
    
    @Test
    public void testFindByName() {
        Author author1 = new Author();
        author1.setName("Author1");
        
        Author author2 = new Author();
        author2.setName("Author2");
        
        authorRepository.save(author1);
        authorRepository.save(author2);
        
        Author author1FromDB = authorRepository.findByName(author1.getName());
        Author author2FromDB = authorRepository.findByName(author2.getName());
        assertEquals(author1, author1FromDB);
        assertEquals(author2, author2FromDB);
    }
}

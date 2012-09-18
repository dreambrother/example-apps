package com.blogspot.nikcode.mongo.repository;

import com.blogspot.nikcode.mongo.domain.Book;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author nik
 */
public interface BookRepository extends CrudRepository<Book, String> {

    Book findByTitle(String title);
}

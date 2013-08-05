package com.blogspot.nikcode.mongo.repository;

import com.blogspot.nikcode.mongo.domain.Author;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author nik
 */
public interface AuthorRepository extends CrudRepository<Author, String> {

    Author findByName(String name);
}

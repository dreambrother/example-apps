package com.blogspot.nikcode.spring.cache.dao;

import com.blogspot.nikcode.spring.cache.Transaction;

/**
 *
 * @author nik
 */
public interface TransactionDao {

    Transaction getById(long id);
    void save(Transaction tx);
    void update(Transaction tx);
}

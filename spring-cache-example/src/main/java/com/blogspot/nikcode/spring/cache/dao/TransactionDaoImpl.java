package com.blogspot.nikcode.spring.cache.dao;

import com.blogspot.nikcode.spring.cache.Transaction;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 *
 * @author nik
 */
public class TransactionDaoImpl implements TransactionDao {

    private final List<Transaction> txs = new CopyOnWriteArrayList<>();
    
    @Override
    @Cacheable("transactions")
    public Transaction getById(long id) {
        // inefficient search imitation
        for (Transaction tx : txs) {
            if (tx.getId() == id) {
                return tx;
            }
        }
        return null;
    }

    @Override
    @CacheEvict(value = "transactions", allEntries = true)
    public void save(Transaction tx) {
        txs.add(tx);
    }
}

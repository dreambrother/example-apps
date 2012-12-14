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
    public void save(Transaction tx) {
        txs.add(tx);
    }
    
    @Override
    @CacheEvict(value = "transactions", key = "#tx.id")
    public void update(Transaction tx) {
        for (int i = 0; i < txs.size(); i++) {
            if (txs.get(i).getId() == tx.getId()) {
                txs.set(i, tx);
            }
        }
    }
}

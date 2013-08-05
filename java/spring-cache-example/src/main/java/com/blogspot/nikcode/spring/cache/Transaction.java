package com.blogspot.nikcode.spring.cache;

/**
 *
 * @author nik
 */
public class Transaction {

    private final long id;
    private final long amount;

    public Transaction(long id, long amount) {
        this.id = id;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }
}

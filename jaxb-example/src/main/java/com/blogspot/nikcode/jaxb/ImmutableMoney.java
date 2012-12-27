package com.blogspot.nikcode.jaxb;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 */
@XmlJavaTypeAdapter(ImmutableMoneyAdapter.class)
public class ImmutableMoney {

    private final long amount;
    private final Currency currency;

    public ImmutableMoney(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "ImmutableMoney{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}

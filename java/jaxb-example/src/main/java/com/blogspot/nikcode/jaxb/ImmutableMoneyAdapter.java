package com.blogspot.nikcode.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 */
public class ImmutableMoneyAdapter extends XmlAdapter<Money, ImmutableMoney> {

    @Override
    public ImmutableMoney unmarshal(Money v) throws Exception {
        return new ImmutableMoney(v.getAmount(), v.getCurrency());
    }

    @Override
    public Money marshal(ImmutableMoney v) throws Exception {
        Money money = new Money();
        money.setAmount(v.getAmount());
        money.setCurrency(v.getCurrency());
        return money;
    }
}

package com.blogspot.nikcode.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "container")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImmutableMoneyContainer {

    @XmlElement
    private Money money;

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ImmutableMoneyContainer{" +
                "money=" + money +
                '}';
    }
}

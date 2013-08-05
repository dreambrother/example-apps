package com.blogspot.nikcode.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

    @XmlElement
    private Money charge;

    @XmlElement
    private String notificationMessage;

    public Money getCharge() {
        return charge;
    }

    public void setCharge(Money charge) {
        this.charge = charge;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    @Override
    public String toString() {
        return "Event{" +
                "charge=" + charge +
                ", notificationMessage='" + notificationMessage + '\'' +
                '}';
    }
}

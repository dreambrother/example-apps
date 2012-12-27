package com.blogspot.nikcode.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Currency.class, Event.class, Money.class);

        Money charge = new Money();
        charge.setAmount(100L);
        charge.setCurrency(Currency.RUB);

        Event event = new Event();
        event.setCharge(charge);
        event.setNotificationMessage("message");

        jaxbContext.createMarshaller().marshal(event, System.out);
        System.out.println();

        Event unmarshalledEvent = (Event) jaxbContext.createUnmarshaller().unmarshal(new StringReader(
                "<event>" +
                        "<charge>" +
                        "<amount>1000</amount>" +
                        "<currency>RUB</currency>" +
                        "</charge>" +
                        "<notificationMessage>Test</notificationMessage>" +
                        "</event>"));
        System.out.println(unmarshalledEvent.toString());
    }
}

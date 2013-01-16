package com.blogspot.nikcode.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Currency.class, Event.class, Money.class, ImmutableMoneyContainer.class, ObjectWithNamespace.class);

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

        ImmutableMoneyContainer container = new ImmutableMoneyContainer();
        Money money = new Money();
        money.setCurrency(Currency.RUB);
        money.setAmount(3000L);
        container.setMoney(money);

        jaxbContext.createMarshaller().marshal(container, System.out);
        System.out.println();

        container = (ImmutableMoneyContainer) jaxbContext.createUnmarshaller().unmarshal(
                new StringReader("<container><money><amount>560</amount><currency>EUR</currency></money></container>"));
        System.out.println(container.toString());

        ObjectWithNamespace obj = new ObjectWithNamespace();
        obj.setVal("test val");
        jaxbContext.createMarshaller().marshal(obj, System.out);
    }
}

package com.blogspot.nikcode.camel;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.apache.camel.CamelContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author nik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
public class TransactionsIntTest {

    @Autowired
    private CamelContext camelContext;
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Test
    public void testJmsTransactions() {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session sn) throws JMSException {
                return sn.createTextMessage("test");
            }
        });
        Object body = camelContext.createConsumerTemplate().receiveBodyNoWait("activemq:queue");
        Assert.assertEquals("test", body);
    }
}

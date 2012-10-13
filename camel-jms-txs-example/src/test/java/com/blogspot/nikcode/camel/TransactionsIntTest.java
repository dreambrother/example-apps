package com.blogspot.nikcode.camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author nik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
@MockEndpoints("activemq:*")
public class TransactionsIntTest {

    @EndpointInject(uri = "mock:activemq:queue")
    private MockEndpoint mockEndpoint;
    
    @Produce(uri = "mock:activemq:queue")
    private ProducerTemplate producer;
    
    @Test
    public void testSendBody() throws InterruptedException {
        mockEndpoint.expectedBodiesReceived("test");
        producer.sendBody("test");
        mockEndpoint.assertIsSatisfied();
    }
}

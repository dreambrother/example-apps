/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.nikcode.spring.ws;

import com.blogspot.nikcode.spring.ws.oxm.ComputeSumRequest;
import com.blogspot.nikcode.spring.ws.oxm.ComputeSumResponse;
import java.io.IOException;
import java.math.BigInteger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.ResponseMatcher;

import static org.junit.Assert.assertEquals;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-ws-servlet.xml")
public class WSEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;
    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testExecute() {
        ComputeSumRequest request = new ComputeSumRequest();
        request.setX(BigInteger.valueOf(10L));
        request.setY(BigInteger.valueOf(120L));
        
        ComputeSumResponse expectedResponse = new ComputeSumResponse();
        expectedResponse.setResult(BigInteger.valueOf(130L));
        
        execute(request, expectedResponse);
    }
    
    @Test
    public void testCustomFault() throws JAXBException {
        mockClient.sendRequest(withPayload(new JAXBSource(JAXBContext.newInstance(ComputeSumRequest.class), 
                    new ComputeSumRequest()))) // empty request
                .andExpect(new SoapFaultResponseMatcherImpl("1", "Invalid request"));
    }
    
    private <TReq, TResp> void execute(TReq request, TResp expectedResponse) {
        try {
            mockClient.sendRequest(withPayload(new JAXBSource(JAXBContext.newInstance(request.getClass()), request)))
                    .andExpect(payload(new JAXBSource(JAXBContext.newInstance(expectedResponse.getClass()), expectedResponse)));
        } catch (JAXBException ex) {
            throw new AssertionError(ex);
        }
    }
    
    /**
     * SOAP fault matcher.
     */
    private static final class SoapFaultResponseMatcherImpl implements ResponseMatcher {

        private final String faultCode;
        private final String faultReason;

        public SoapFaultResponseMatcherImpl(String faultCode, String faultReason) {
            this.faultCode = faultCode;
            this.faultReason = faultReason;
        }

        @Override
        public void match(WebServiceMessage request, WebServiceMessage response) throws IOException, AssertionError {
            SoapMessage soapResponse = (SoapMessage) response;
            SoapBody responseBody = soapResponse.getSoapBody();
            SoapFault soapFault = responseBody.getFault();
            QName expectedFaultCode = getExpectedFaultCode(soapResponse.getVersion());
            assertEquals("Invalid SOAP Fault code", expectedFaultCode, soapFault.getFaultCode());
            if (faultCode != null) {
                assertEquals("Invalid SOAP Fault string/reason", faultReason, soapFault.getFaultStringOrReason());
            }
        }

        protected QName getExpectedFaultCode(SoapVersion version) {
            return new QName("http://nikcode.blogspot.com/", faultCode);
        }
    }
}

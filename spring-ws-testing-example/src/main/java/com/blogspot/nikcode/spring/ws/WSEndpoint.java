package com.blogspot.nikcode.spring.ws;

import com.blogspot.nikcode.spring.ws.oxm.ComputeSumRequest;
import com.blogspot.nikcode.spring.ws.oxm.ComputeSumResponse;
import java.math.BigInteger;
import org.springframework.util.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WSEndpoint {

    @PayloadRoot(namespace = "http://nikcode.blogspot.com/", localPart = "ComputeSumRequest")
    @ResponsePayload
    public ComputeSumResponse execute(@RequestPayload ComputeSumRequest request) {
        Assert.notNull(request.getX());
        Assert.notNull(request.getY());
        ComputeSumResponse response = new ComputeSumResponse();
        response.setResult(BigInteger.valueOf(request.getX().longValue() + request.getY().longValue()));
        return response;
    }
}

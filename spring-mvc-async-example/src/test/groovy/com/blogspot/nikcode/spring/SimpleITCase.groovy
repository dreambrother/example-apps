package com.blogspot.nikcode.spring

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.junit.Test

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import static org.junit.Assert.assertEquals

/**
 *
 */
class SimpleITCase {

    static final String URL = 'http://localhost:8080/spring-mvc-async-example'

    @Test
    void syncTest() {
        performGet('/sync-test')
    }

    @Test
    void asyncTest() {
        performGet('/async-test')
    }

    @Test
    void testFreeze() {
        int poolSize = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        poolSize.times { println("$it"); executorService.execute({ performGet('/async-freeze') } as Runnable) }
        performGet('/async-test')
        executorService.shutdownNow()
    }

    private void performGet(String relativeUri) {
        HttpGet httpGet = new HttpGet(URL + relativeUri)
        HttpResponse response = new DefaultHttpClient().execute(httpGet)
        httpGet.releaseConnection()
        assert response.getStatusLine().statusCode == 200
    }
}

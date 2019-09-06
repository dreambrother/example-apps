package com.github.dreambrother.examples.servlet;

import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NioExample extends HttpServlet {

    private final CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();

    {
        client.start();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        client.execute(new HttpGet("http://google.com"), new FutureCallback<>() {

            @SneakyThrows
            @Override
            public void completed(HttpResponse result) {
                resp.getWriter().println(EntityUtils.toString(result.getEntity()));
                asyncContext.complete();
            }

            @SneakyThrows
            @Override
            public void failed(Exception ex) {
                resp.getWriter().println(ex);
                asyncContext.complete();
            }

            @Override
            public void cancelled() {
                asyncContext.complete();
            }
        });
    }
}

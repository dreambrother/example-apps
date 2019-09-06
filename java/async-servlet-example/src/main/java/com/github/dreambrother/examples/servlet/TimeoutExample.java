package com.github.dreambrother.examples.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TimeoutExample extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                log.info("Completed {}", event);
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                log.info("Timeout {}", event);
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                log.info("Error {}", event);
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                log.info("StartAsync {}", event);
            }
        });
        asyncContext.setTimeout(1000L);
    }
}

package com.github.dreambrother.examples.servlet;

import lombok.SneakyThrows;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleExample extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        new Thread(() -> waitAndResponse(resp, asyncContext)).start();
    }

    @SneakyThrows
    private void waitAndResponse(HttpServletResponse response, AsyncContext context) {
        Thread.sleep(5000L);
        response.getWriter().println("OK!");
        context.complete();
    }
}

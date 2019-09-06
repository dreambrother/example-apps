package com.github.dreambrother.examples;

import com.github.dreambrother.examples.servlet.NioExample;
import com.github.dreambrother.examples.servlet.SimpleExample;
import com.github.dreambrother.examples.servlet.TimeoutExample;
import lombok.SneakyThrows;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Server server = new Server(9090);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(SimpleExample.class, "/simple");
        handler.addServlet(NioExample.class, "/nio");
        handler.addServlet(TimeoutExample.class, "/timeout");
        server.setHandler(handler);
        server.start();
    }
}

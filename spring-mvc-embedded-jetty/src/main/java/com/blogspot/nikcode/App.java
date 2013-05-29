package com.blogspot.nikcode;

import com.blogspot.nikcode.config.WebConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/");

        server.setHandler(handler);
        server.start();
    }
}

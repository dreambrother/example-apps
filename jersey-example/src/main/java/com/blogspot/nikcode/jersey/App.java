package com.blogspot.nikcode.jersey;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;

/**
 * User: nik
 * Date: 4/28/13
 * Time: 2:09 AM
 */
public class App {

    public static void main(String[] args) throws IOException {
        ResourceConfig resourceConfig = new PackagesResourceConfig("com.blogspot.nikcode.jersey");
        HttpServer httpServer = GrizzlyServerFactory.createHttpServer("http://localhost:8080/", resourceConfig);
        httpServer.start();
        System.in.read();
        httpServer.stop();
    }
}

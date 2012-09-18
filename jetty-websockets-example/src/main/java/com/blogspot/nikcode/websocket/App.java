package com.blogspot.nikcode.websocket;

import org.eclipse.jetty.server.Server;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        EventHandler handler = new EventHandler();
        server.setHandler(handler);
        server.start();
    }
}

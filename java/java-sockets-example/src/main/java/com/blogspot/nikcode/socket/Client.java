package com.blogspot.nikcode.socket;

import java.io.IOException;
import java.net.Socket;

/**
 *
 */
public class Client {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080)) {
            socket.getOutputStream().write("Test socket".getBytes());
        }
    }
}

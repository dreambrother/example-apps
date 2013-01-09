package com.blogspot.nikcode.socket;

import java.io.IOException;
import java.net.Socket;

/**
 *
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6666);
        socket.getOutputStream().write("Test socket".getBytes());
        socket.close();
    }
}

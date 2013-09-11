package com.blogspot.nikcode.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        boolean isLastIteration = false;
        for (Set<SelectionKey> selectionKeys; !isLastIteration;) {
            selector.select();
            selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
                    int bytesRead = socketChannel.read(buffer);
                    while (bytesRead > 0) {
                        buffer.flip();
                        CharBuffer charBuffer = decoder.decode(buffer);
                        while (charBuffer.hasRemaining()) {
                            System.out.print(charBuffer.get());
                        }
                        charBuffer.clear();
                        buffer.clear();
                        bytesRead = socketChannel.read(buffer);
                    }
                    isLastIteration = true;
                    socketChannel.write(ByteBuffer.wrap("HTTP/1.1 200 OK\n\nOK\n".getBytes("UTF-8")));
                }
                iterator.remove();
            }
        }
        selector.close();
    }
}

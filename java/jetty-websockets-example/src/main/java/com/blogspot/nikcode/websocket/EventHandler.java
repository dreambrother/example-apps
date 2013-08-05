package com.blogspot.nikcode.websocket;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

/**
 *
 * @author nik
 */
public class EventHandler extends WebSocketHandler {
        
    private final ConcurrentMap<String, Set<EventWebSocket>> eventsSubscribers = new ConcurrentHashMap<>();

    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
        return new EventWebSocket();
    }
   
    private void fireEvent(String eventName) {
        Set<EventWebSocket> subscribers = eventsSubscribers.get(eventName);
        System.out.println("Event " + eventName + " was occurred. Subscribers " + subscribers);
        if (subscribers != null) {
            for (EventWebSocket webSocket : subscribers) {
                webSocket.onEvent(eventName);
            }
        }
    }
    
    public final class EventWebSocket implements WebSocket.OnTextMessage {

        private Connection connection;
        private List<String> subscribedEvents = new LinkedList();
        
        @Override
        public void onOpen(Connection connection) {
            System.out.println("Open connection " + connection);
            this.connection = connection;
        }

        @Override
        public void onMessage(String data) {
            System.out.println("Process msg: " + data);
            if (data.isEmpty()) return;
            if (data.startsWith("Sub")) { // subscribe
                if (data.length() < 5) return;
                String eventName = data.split(":")[1]; // subscribing message format "Sub:EventName"
                Set<EventWebSocket> subscribers = eventsSubscribers.get(eventName);
                // double-check idiom for lazy initialization
                if (subscribers == null) {
                    synchronized (EventHandler.class) {
                        if (eventsSubscribers.get(eventName) == null) {
                            subscribers = new CopyOnWriteArraySet<>();
                            eventsSubscribers.putIfAbsent(eventName, subscribers);
                        }
                    }
                }
                subscribers.add(this);
                subscribedEvents.add(eventName);
            } else if (data.startsWith("Fire")) { // fire event
                if (data.length() < 6) return;
                String eventName = data.split(":")[1]; // Fire event message format "Fire:EventName"
                fireEvent(eventName);
            } else {
                try {
                    connection.sendMessage("Unknown command: " + data);
                } catch (IOException ex) {
                    System.err.println("Ooops: " + ex + ". Close connection " + connection);
                    connection.close();
                }
            }
        }
        
        public void onEvent(String eventName) {
            try {
                connection.sendMessage("Event " + eventName + " was occurred");
            } catch (IOException ex) {
                System.err.println("Ooops: " + ex + ". Close connection " + connection);
                connection.close();
            }
        }

        @Override
        public void onClose(int closeCode, String message) {
            System.out.println("Close connection " + connection);
            // remove from subscribers
            for (String eventName : subscribedEvents) {
                eventsSubscribers.get(eventName).remove(this);
            }
        }
    }
}

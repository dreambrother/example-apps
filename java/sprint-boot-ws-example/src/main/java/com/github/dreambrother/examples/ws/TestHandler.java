package com.github.dreambrother.examples.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);

    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Message {} from user {} is received",message.getPayload(), getUserId(session));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        logger.info("Connection {} for user {} is established", session.getId(), userId);
        userSessions.put(userId, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = getUserId(session);
        logger.info("Connection {} for user {} is closed with status {}", session.getId(), userId, status);
        userSessions.remove(userId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String userId = getUserId(session);
        logger.warn("Transport {} error for user", session.getId(), userId, exception);
        userSessions.remove(userId);
    }

    private String getUserId(WebSocketSession session) {
        return session.getUri().getQuery().split("=")[1];
    }

    @Scheduled(fixedDelay = 5000)
    public void broadcast() {
        userSessions.forEach((userId, session) -> {
            try {
                logger.info("Send message to {}", userId);
                session.sendMessage(new TextMessage("Message for " + userId));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}

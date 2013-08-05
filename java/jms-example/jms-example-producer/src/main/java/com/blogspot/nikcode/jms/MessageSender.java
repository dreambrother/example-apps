package com.blogspot.nikcode.jms;

import java.io.IOException;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nik
 */
@WebServlet("/send")
public class MessageSender extends HttpServlet {

    @Resource(name = "connectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(name = "queueDestination")
    private Destination queue;
    
    @Resource(name = "topicDestination")
    private Destination topic;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("msg");
        String type = req.getParameter("type");
        try {
            send(type, message);
        } catch (JMSException ex) {
            throw new ServletException(ex);
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
    
    private void send(String type, String text) throws JMSException {
        Connection connection = connectionFactory.createConnection();
        try {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer;
            switch (type) {
                case "Topic":
                    producer = session.createProducer(topic);
                    break;
                case "Queue":
                    producer = session.createProducer(queue);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown destination " + type);
            }
            producer.send(session.createTextMessage(text));
        } finally {
            connection.close();
        }
    }
}

package com.example.producent.service;

import com.example.producent.configuration.ArtemisProperties;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.*;

@Service
public class MessageService {

    @Autowired
    private ArtemisProperties artemisProperties;
    @Autowired
    private MessageConverter messageConverter;

    private ActiveMQConnectionFactory connectionFactory;

    @PostConstruct
    public void initConnectionFactory(){
        connectionFactory = new ActiveMQConnectionFactory(artemisProperties.getBrokerUrl());
        connectionFactory.setUser(artemisProperties.getUser());
        connectionFactory.setPassword(artemisProperties.getPassword());
    }

    public void sendQueue(String message) throws JMSException {
        sendMessageToQueue(artemisProperties.getQueue(),message);
    }

    public void sendDurableQueue(String message) throws JMSException {
        sendMessageToQueue(artemisProperties.getQueueDurable(),message);
    }

    public void sendTopic(String message) throws JMSException {
        sendMessageToTopic(artemisProperties.getTopic(), message);
    }

    private void sendMessageToQueue(String destinationName, String text) throws JMSException {
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID(artemisProperties.getClientId());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(destinationName);

            final MessageProducer producer = session.createProducer(queue);
            final TextMessage textMessage = (TextMessage)messageConverter.toMessage(text, session);

            session.createTextMessage(textMessage.getText());

            producer.send(textMessage);

            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void sendMessageToTopic(String destinationName, String text) throws JMSException {
        TopicConnection connection = null;
        try {
            connection = connectionFactory.createTopicConnection();
            connection.setClientID(artemisProperties.getClientId());

            connection.start();

            TopicSession session = connection.createTopicSession(
                    false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(destinationName);
            Message msg = session.createTextMessage(text);

            TopicPublisher publisher = session.createPublisher(topic);
            publisher.publish(msg);

            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}

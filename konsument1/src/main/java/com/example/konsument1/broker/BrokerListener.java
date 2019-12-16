package com.example.konsument1.broker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
@Log4j2
@RequiredArgsConstructor
public class BrokerListener {

    @JmsListener(destination = "${app.artemis.queue-nondurable}", containerFactory = "artemisQueueConnectionFactory")
    public void processNondurableQueueMessage(TextMessage message) {
        try {
            log.info("*********************** NONDURABLE QUEUE MESSAGE RECEIVED: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "${app.artemis.queue-durable}", containerFactory = "artemisQueueConnectionFactory")
    public void processDurableQueueMessage(TextMessage message) {
        try {
            log.info("*********************** DURABLE QUEUE MESSAGE RECEIVED: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    @JmsListener(destination = "${app.artemis.topic}", subscription = "KONSUMENT1_SUBSCRIPTION", containerFactory = "artemisTopicConnectionFactory")
    public void processTopicMessage(TextMessage message) {
        try {
            log.info("*********************** TOPIC MESSAGE RECEIVED: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "${app.artemis.topic-second}", subscription = "KONSUMENT1_SECOND_SUBSCRIPTION", containerFactory = "artemisTopicSecondConnectionFactory")
    public void processSecondTopicMessage(TextMessage message) {
        try {
            log.info("*********************** SECOND TOPIC MESSAGE RECEIVED: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}

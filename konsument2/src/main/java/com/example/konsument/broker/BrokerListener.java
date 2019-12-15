package com.example.konsument.broker;

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

    @JmsListener(destination = "${app.artemis.queue}", containerFactory = "artemisQueueConnectionFactory")
    public void processQueueMessage(TextMessage message) {
        try {
            log.info("*********************** QUEUE MESSAGE RECEIVED: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    @JmsListener(destination = "${app.artemis.topic}", containerFactory = "artemisTopicConnectionFactory")
    public void processTopicMessage(TextMessage message) {
        try {
            log.info("*********************** TOPIC MESSAGE RECEIVED: " + message.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}

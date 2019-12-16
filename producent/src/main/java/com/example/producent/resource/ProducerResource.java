package com.example.producent.resource;

import com.example.producent.model.MessageRequest;
import com.example.producent.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("command")
public class ProducerResource {

    private final MessageService messageService;

    @PostMapping(path = "queue")
    public void testQueue(@RequestBody MessageRequest request) throws JMSException {
        log.info("Sending to queue. Message: " + request);
        messageService.sendQueue(request.getMessage());
    }

    @PostMapping(path = "queue-durable")
    public void testQueueDurable(@RequestBody MessageRequest request) throws JMSException {
        log.info("Sending to durable queue. Message: " + request);
        messageService.sendDurableQueue(request.getMessage());
    }

    @PostMapping(path = "topic")
    public void testSubscribe(@RequestBody MessageRequest request) throws JMSException {
        log.info("Sending to topic. Message: " + request);
        messageService.sendTopic(request.getMessage());
    }

    @PostMapping(path = "topic-second")
    public void testSubscribeSecond(@RequestBody MessageRequest request) throws JMSException {
        log.info("Sending to topic. Message: " + request);
        messageService.sendTopicSecond(request.getMessage());
    }


}

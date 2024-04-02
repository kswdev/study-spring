package com.spring.study.chapter15.jms.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.Map;

@Slf4j
public class InboundHelloWorldJMSMessageProcessor {

    @ServiceActivator
    public void handleIncomingJmsMessage(Message<Map<String, Object>> inboundJmsMessage) {
        Map<String, Object> msg = inboundJmsMessage.getPayload();
        log.info("firstName: {}, lastName: {}, id: {}", msg.get("firstName"),
                                                       msg.get("lastName"),
                                                       msg.get("id"));
    }
}

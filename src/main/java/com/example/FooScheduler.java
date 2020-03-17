package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class FooScheduler {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public FooScheduler(final JmsTemplate jmsTemplate,
                        final ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    //@Scheduled(fixedRate = 10000)
    private void sendEvent() throws JsonProcessingException {
        final FooEvent event = createEvent();

        System.out.println("Send - event --> " + objectMapper.writeValueAsString(event));
        System.out.println();

        jmsTemplate.convertAndSend("foo-destination", event);
    }

    private FooEvent createEvent() {
        return FooEvent.builder()
                .uuid(UUID.randomUUID().toString())
                .date(new Date())
                .build();
    }
}

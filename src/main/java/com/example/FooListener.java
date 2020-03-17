package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class FooListener {

    private final ObjectMapper objectMapper;

    public FooListener(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "foo-destination", containerFactory = "jmsFactory")
    public void getEvents(FooEvent event) throws JsonProcessingException {
        System.out.println("Receive - event --> " + objectMapper.writeValueAsString(event));
        System.out.println();
    }

}

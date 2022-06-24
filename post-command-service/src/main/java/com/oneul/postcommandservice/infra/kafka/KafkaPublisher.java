package com.oneul.postcommandservice.infra.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.oneul.postcommandservice.infra.dto.PostMessage;

@Component
public class KafkaPublisher {
    private final Logger log = LoggerFactory.getLogger(KafkaPublisher.class);
    private final KafkaTemplate<String, PostMessage> kafkaTemplate;

    public KafkaPublisher(KafkaTemplate<String,PostMessage> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, PostMessage payload){
        log.info("publish message: topic: " + topic + ", payload: " + payload.toString());
        kafkaTemplate.send(topic, payload);
    }
}

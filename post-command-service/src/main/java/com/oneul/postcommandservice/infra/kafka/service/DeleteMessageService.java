package com.oneul.postcommandservice.infra.kafka.service;

import org.springframework.stereotype.Component;

import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.infra.dto.PostMessage;
import com.oneul.postcommandservice.infra.kafka.KafkaPublisher;
import com.oneul.postcommandservice.infra.kafka.MessageType;

@Component
public class DeleteMessageService implements MessageQueueService {
    private final KafkaPublisher kafkaPublisher;
    
    public DeleteMessageService(KafkaPublisher kafkaPublisher){
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    public MessageType getMessageType(){
        return MessageType.DELETE;
    }

    @Override
    public PostMessage transaction(Post post) {
        PostMessage postMessage = PostMessage.builder()
                                             .type(getMessageType().toString())
                                             .id(post.getId())
                                             .build();
        kafkaPublisher.sendMessage("post", postMessage);
        return postMessage;
    }
}

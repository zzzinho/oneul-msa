package com.oneul.postcommandservice.infra.kafka.service;

import org.springframework.stereotype.Component;

import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.infra.dto.PostMessage;
import com.oneul.postcommandservice.infra.kafka.KafkaPublisher;
import com.oneul.postcommandservice.infra.kafka.MessageType;

@Component
public class UpdateMessageService implements MessageQueueService {
    private final KafkaPublisher kafkaPublisher;

    public UpdateMessageService(KafkaPublisher kafkaPublisher){
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    public MessageType getMessageType(){
        return MessageType.UPDATE;
    }

    @Override
    public PostMessage transaction(Post post){
        PostMessage postMessage = new PostMessage(
            MessageType.UPDATE.toString(),
            post.getId(), 
            post.getCreatedAt(), 
            post.getContent(), 
            post.getUserId()
        );
        kafkaPublisher.sendMessage("post", postMessage);
        return postMessage;
    }
}

package com.oneul.postcommandservice.infra.kafka.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.infra.dto.PostMessage;
import com.oneul.postcommandservice.infra.kafka.MessageType;

@Component
public class MessageQueueFactory {
    private final Map<MessageType, Function<Post, PostMessage>> messageQueueServiceMap = new HashMap<>();    

    public MessageQueueFactory(List<MessageQueueService> messageQueueServices) {
        if(CollectionUtils.isEmpty(messageQueueServices)){
            throw new IllegalArgumentException("메시지 큐 구현체가 존재하지 않습니다.");
        }

        for(MessageQueueService service : messageQueueServices){
            Function<Post, PostMessage> transaction = service::transaction;
            this.messageQueueServiceMap.put(service.getMessageType(), transaction);
        }
    }

    public Function<Post, PostMessage> getMessageType(MessageType type){
        return messageQueueServiceMap.get(type);
    }
}

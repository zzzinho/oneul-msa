package com.oneul.postcommandservice.infra.kafka.service;

import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.infra.dto.PostMessage;
import com.oneul.postcommandservice.infra.kafka.MessageType;

public interface MessageQueueService {
    MessageType getMessageType();
    PostMessage transaction(Post post);
}

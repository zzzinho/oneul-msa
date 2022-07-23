package com.oneul.postcommandservice.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneul.postcommandservice.dao.PostCommandRepository;
import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.domain.UserEntity;
import com.oneul.postcommandservice.error.NotFoundException;
import com.oneul.postcommandservice.infra.kafka.MessageType;
import com.oneul.postcommandservice.infra.kafka.service.MessageQueueFactory;

@Service
@Transactional
public class PostCommnadServiceImpl implements PostCommandService{
    private final Logger log = LoggerFactory.getLogger(PostCommnadServiceImpl.class);

    private final PostCommandRepository postCommandRepository;
    private final MessageQueueFactory messageQueueFactory;

    public PostCommnadServiceImpl(PostCommandRepository postCommandRepository, MessageQueueFactory messageQueueFactory){
        this.postCommandRepository = postCommandRepository;
        this.messageQueueFactory = messageQueueFactory;
    }
    
    @Override
    public Post createPost(Post post, HttpSession httpSession){
        Object object = httpSession.getAttribute("user");
        log.info(object.toString());
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        LocalDateTime createdAt = LocalDateTime.now();

        Post postEntity =  postCommandRepository.save(
            Post.builder()
                .content(post.getContent())
                .createdAt(createdAt)
                .expiredAt(createdAt.plusHours(24))
                .userId(userEntity.getId())
                .build());

        MessageType messageType = MessageType.INSERT;
        messageQueueFactory.getMessageType(messageType).apply(postEntity);
    
        log.info("user: " + userEntity.getUsername() + " create " + postEntity.toString());
        return postEntity;
    }

    @Override
    public Post updatePost(Long id, Post post, HttpSession httpSession){ 
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        Post postEntity = postCommandRepository.findByIdAndUserId(id, userEntity.getId()).orElseThrow(() -> new NotFoundException(id + " post not found"));
        
        postEntity.setConent(post.getContent());
        postEntity = postCommandRepository.save(postEntity);

        MessageType messageType = MessageType.UPDATE;
        messageQueueFactory.getMessageType(messageType).apply(postEntity);

        log.info(postEntity.toString() + " is updated");

        return postEntity;
    }

    @Override
    public void deletePost(Long id, HttpSession httpSession){
        UserEntity userEntity = (UserEntity) httpSession.getAttribute("user");
        Post postEntity = postCommandRepository.findByIdAndUserId(id, userEntity.getId())
                                                .orElseThrow(() -> new NotFoundException(id + " post not found"));
        postCommandRepository.deleteByIdAndUserId(id, userEntity.getId());

        MessageType messageType = MessageType.DELETE;
        messageQueueFactory.getMessageType(messageType).apply(postEntity);
    
        log.info("post " + id + " is deleted");
    }
}
package com.oneul.postcommandservice.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneul.postcommandservice.dao.PostCommandRepository;
import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.dto.UserResponse;
import com.oneul.postcommandservice.error.ExpiredSessionException;
import com.oneul.postcommandservice.error.NotFoundException;
import com.oneul.postcommandservice.infra.client.UserWebClient;
import com.oneul.postcommandservice.infra.kafka.MessageType;
import com.oneul.postcommandservice.infra.kafka.service.MessageQueueFactory;

@Service
@Transactional
public class PostCommnadServiceImpl implements PostCommandService{
    private final Logger log = LoggerFactory.getLogger(PostCommnadServiceImpl.class);

    private final PostCommandRepository postCommandRepository;
    private final MessageQueueFactory messageQueueFactory;
    private final UserWebClient userWebClient;

    public PostCommnadServiceImpl(PostCommandRepository postCommandRepository, MessageQueueFactory messageQueueFactory, UserWebClient userWebClient){
        this.postCommandRepository = postCommandRepository;
        this.messageQueueFactory = messageQueueFactory;
        this.userWebClient = userWebClient;
    }
    
    @Override
    public Post createPost(Post post, HttpSession httpSession){
        Object object = httpSession.getAttribute("user");
        if(object == null){
            throw new ExpiredSessionException("session is expired");
        }
        log.info(object.toString());
        Long userId = (Long) httpSession.getAttribute("user");
        UserResponse userResponse = userWebClient.getUserById(userId);
        LocalDateTime createdAt = LocalDateTime.now();

        Post postEntity =  postCommandRepository.save(
            Post.builder()
                .content(post.getContent())
                .createdAt(createdAt)
                .expiredAt(createdAt.plusHours(24))
                .writerId(userResponse.getId())
                .build());

        MessageType messageType = MessageType.INSERT;
        messageQueueFactory.getMessageType(messageType).apply(postEntity);
    
        log.info("user: " + userResponse.getUsername() + " create " + postEntity.toString());
        return postEntity;
    }

    @Override
    public Post updatePost(Long id, Post post, HttpSession httpSession){ 
        Object object = httpSession.getAttribute("user");
        if(object == null){
            throw new ExpiredSessionException("session is expired");
        }
        log.info(object.toString());
        Long userId = (Long) httpSession.getAttribute("user");
        UserResponse userResponse = userWebClient.getUserById(userId);
        Post postEntity = postCommandRepository.findByIdAndWriterId(id, userResponse.getId()).orElseThrow(() -> new NotFoundException(id + " post not found"));
        
        postEntity.setConent(post.getContent());
        postEntity = postCommandRepository.save(postEntity);

        MessageType messageType = MessageType.UPDATE;
        messageQueueFactory.getMessageType(messageType).apply(postEntity);

        log.info(postEntity.toString() + " is updated");

        return postEntity;
    }

    @Override
    public void deletePost(Long id, HttpSession httpSession){
        Object object = httpSession.getAttribute("user");
        if(object == null){
            throw new ExpiredSessionException("session is expired");
        }
        log.info(object.toString());
        Long userId = (Long) httpSession.getAttribute("user");
        UserResponse userResponse = userWebClient.getUserById(userId);
        Post postEntity = postCommandRepository.findByIdAndWriterId(id, userResponse.getId())
                                                .orElseThrow(() -> new NotFoundException(id + " post not found"));
        postCommandRepository.deleteByIdAndWriterId(id, userResponse.getId());

        MessageType messageType = MessageType.DELETE;
        messageQueueFactory.getMessageType(messageType).apply(postEntity);
    
        log.info("post " + id + " is deleted");
    }
}
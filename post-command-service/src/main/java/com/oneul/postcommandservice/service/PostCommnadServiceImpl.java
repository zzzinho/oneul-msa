package com.oneul.postcommandservice.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneul.postcommandservice.dao.PostCommandRepository;
import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.error.NotFoundException;
import com.oneul.postcommandservice.infra.dto.PostMessage;
import com.oneul.postcommandservice.infra.kafka.KafkaPublisher;

@Service
@Transactional
public class PostCommnadServiceImpl implements PostCommandService{
    private final Logger log = LoggerFactory.getLogger(PostCommnadServiceImpl.class);

    private final PostCommandRepository postCommandRepository;
    private final KafkaPublisher kafkaPublisher;
    
    public PostCommnadServiceImpl(PostCommandRepository postCommandRepository, KafkaPublisher kafkaPublisher){
        this.postCommandRepository = postCommandRepository;
        this.kafkaPublisher = kafkaPublisher;
    }
    
    @Override
    public Post createPost(Post post, Long userId){
        LocalDateTime createdAt = LocalDateTime.now();

        Post postEntity =  postCommandRepository.save(
            Post.builder()
                .content(post.getContent())
                .createdAt(createdAt)
                .expiredAt(createdAt.plusHours(24))
                .userId(userId)
                .build());

        kafkaPublisher.sendMessage(
            "post", 
            new PostMessage(
                "INSERT",
                postEntity.getId(), 
                postEntity.getCreatedAt(), 
                postEntity.getContent(), 
                postEntity.getUserId()
            )
        );
    
        log.info("user: " + userId + " create " + postEntity.toString());
        return postEntity;
    }

    @Override
    public Post updatePost(Long id, Post post, Long userId){ 
        Post postEntity = postCommandRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException(id + " post not found"));
        
        postEntity.setConent(post.getContent());
        postEntity = postCommandRepository.save(postEntity);

        kafkaPublisher.sendMessage(
            "post", 
            new PostMessage(
                "UPDATE",
                postEntity.getId(), 
                postEntity.getCreatedAt(), 
                postEntity.getContent(), 
                postEntity.getUserId()
            )
        );

        log.info(postEntity.toString() + " is updated");

        return postEntity;
    }

    @Override
    public void deletePost(Long id, Long userId){
        postCommandRepository.deleteByIdAndUserId(id, userId);

        kafkaPublisher.sendMessage(
            "post", 
            PostMessage.builder()
                .type("DELETE")
                .id(id)
                .build());
    
        log.info("post " + id + " is deleted");
    }
}
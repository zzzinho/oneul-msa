package com.oneul.postqueryservice.infra.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oneul.postqueryservice.dao.PostQueryRepository;
import com.oneul.postqueryservice.domain.PostDocument;
import com.oneul.postqueryservice.error.NotFoundException;
import com.oneul.postqueryservice.infra.dto.PostMessage;

@Component
public class KafkaSubscriber {
    private final Logger log = LoggerFactory.getLogger(KafkaSubscriber.class);
    private final PostQueryRepository postQueryRepository;

    public KafkaSubscriber(PostQueryRepository postQueryRepository){
        this.postQueryRepository = postQueryRepository;
    }

    @KafkaListener(topics = "post", containerFactory = "postListener")
    public void listen(String test) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        PostMessage postMessage = objectMapper.readValue(test, PostMessage.class);
        
        log.info("message listen: " + postMessage.toString());
        
        if(postMessage.getType().equals("INSERT")){
            postQueryRepository.save(new PostDocument(
                postMessage.getId(), 
                postMessage.getCreatedAt(), 
                postMessage.getContent(), 
                postMessage.getUserId()));
        } else if(postMessage.getType().equals("UPDATE")){
            PostDocument postDocument = postQueryRepository.findById(postMessage.getId())
                                                            .orElseThrow(() ->new NotFoundException("query repository doesn't have " + postMessage.getId()));
            postDocument.setContent(postMessage.getContent());
            postQueryRepository.save(postDocument);
        } else if(postMessage.getType().equals("DELETE")){
            postQueryRepository.deleteById(postMessage.getId());
        }   
    }
}

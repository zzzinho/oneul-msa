package com.oneul.postqueryservice.infra.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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
    public void listen(PostMessage postMessage){
        log.info("message listen: " + postMessage.toString());
        
        if(postMessage.getType().equals("INSERT")){
            postQueryRepository.save(new PostDocument(
                postMessage.getId(), 
                postMessage.getCreatedAt(), 
                postMessage.getContent(), 
                postMessage.getWriter()));
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

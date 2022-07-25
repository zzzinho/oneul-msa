package com.oneul.postqueryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneul.postqueryservice.dao.PostQueryRepository;
import com.oneul.postqueryservice.domain.PostDocument;

@Service
@Transactional
public class PostQueryServiceImpl implements PostQueryService {
    private final PostQueryRepository postQueryRepository;

    public PostQueryServiceImpl(PostQueryRepository postQueryRepository){
        this.postQueryRepository = postQueryRepository;
    }

    @Override
    public PostDocument insertPost(PostDocument post){
        return postQueryRepository.insert(post);
    }
    
    @Override
    public Page<PostDocument> findAll(PageRequest pageRequest){
        return postQueryRepository.findAll(pageRequest);
    }

    @Override
    public Page<PostDocument> findByWriter(Long writerId, PageRequest pageRequest){
        return postQueryRepository.findAll(pageRequest);
    }
}

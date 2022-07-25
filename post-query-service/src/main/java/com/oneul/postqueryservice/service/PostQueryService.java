package com.oneul.postqueryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.oneul.postqueryservice.domain.PostDocument;

public interface PostQueryService {
    Page<PostDocument> findAll(PageRequest pageRequest);
    Page<PostDocument> findByWriter(Long writerId, PageRequest pageRequest);
    PostDocument insertPost(PostDocument postDocument);
}

package com.oneul.postqueryservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oneul.postqueryservice.domain.PostDocument;

@Repository
public interface PostQueryRepository extends MongoRepository<PostDocument, Long> {
    Page<PostDocument> findAll(Pageable pageable);
}

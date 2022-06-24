package com.oneul.postcommandservice.service;

import org.springframework.stereotype.Service;

import com.oneul.postcommandservice.domain.Post;

@Service
public interface PostCommandService {
    Post createPost(Post post, Long userId);
    Post updatePost(Long id, Post post, Long userId);
    void deletePost(Long id, Long userId);
}

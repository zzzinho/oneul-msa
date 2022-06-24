package com.oneul.postcommandservice.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.oneul.postcommandservice.domain.Post;

@Service
public interface PostCommandService {
    Post createPost(Post post, HttpSession httpSession);
    Post updatePost(Long id, Post post, HttpSession httpSession);
    void deletePost(Long id, HttpSession httpSession);
}

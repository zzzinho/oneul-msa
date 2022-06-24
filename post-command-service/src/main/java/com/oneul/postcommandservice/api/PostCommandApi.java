package com.oneul.postcommandservice.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oneul.postcommandservice.domain.Post;
import com.oneul.postcommandservice.dto.PostDTO;
import com.oneul.postcommandservice.service.PostCommandService;

@RestController
@RequestMapping(value = "/post")
public class PostCommandApi {
    private final PostCommandService postCommandService;

    public PostCommandApi(PostCommandService postCommandService){
        this.postCommandService = postCommandService;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Post createPost(HttpSession httpSession, @RequestBody PostDTO postDTO) {
        Post post = postCommandService.createPost(postDTO.toEntity(), httpSession);
        return post;
    }
    
    @RequestMapping(value="/{postId}/", method=RequestMethod.PUT)
    public Post updatePost(HttpSession httpSession, @RequestBody PostDTO postDTO, @PathVariable Long postId) {
        Post post = postCommandService.updatePost(postId, postDTO.toEntity(), httpSession);
        return post;
    }
    
    @RequestMapping(value="/{postId}/", method=RequestMethod.DELETE)
    public String deletePost(HttpSession httpSession, @PathVariable Long postId) {
        postCommandService.deletePost(postId, httpSession);
        return postId + " is deleted";
    }
    
}

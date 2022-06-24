package com.oneul.postcommandservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oneul.postcommandservice.domain.Post;

public class PostDTO {
    @NotBlank
    private String content; 
    @NotNull
    private Long userId;

    public PostDTO() {}

    public PostDTO(String content, Long userId){
        this.content = content;
        this.userId = userId;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return this.userId;
    }
    
    public Post toEntity() {
        return Post.builder()
            .content(content)
            .userId(userId)
            .build();
    }
}

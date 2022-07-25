package com.oneul.postcommandservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oneul.postcommandservice.domain.Post;

public class PostDTO {
    @NotBlank
    private String content; 
    @NotNull
    private Long writerId;

    public PostDTO() {}

    public PostDTO(String content, Long writerId){
        this.content = content;
        this.writerId = writerId;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public void setUserId(Long writerId){
        this.writerId = writerId;
    }

    public Long getUserId(){
        return this.writerId;
    }
    
    public Post toEntity() {
        return Post.builder()
            .content(content)
            .writerId(writerId)
            .build();
    }
}

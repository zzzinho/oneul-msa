package com.oneul.postcommandservice.infra.dto;

import java.time.LocalDateTime;

public class PostMessage {
    private String type;
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private Long userId;  

    public PostMessage() {}

    public PostMessage(String type, Long id, LocalDateTime createdAt, String content, Long userId){
        this.type = type;
        this.id = id; 
        this.createdAt = createdAt;
        this.content = content;
        this.userId = userId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public String getContent(){
        return this.content;
    }

    public void setConent(String content){
        this.content = content;
    }

    public Long getUserId(){
        return this.userId;
    }

    public void setWriter(Long userId){
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "PostMessage["
            + "type: " + this.type
            + ", id: " + this.id
            + ", createdAt: " + this.createdAt
            + ", content: " + this.content
            + ", userId: " + this.userId
            + "]";
    }

    public static Builder builder(){
        return new Builder();
    }
    
    public static class Builder {
        private String type;
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private Long userId;

        public PostMessage build(){
            return new PostMessage(
                type,
                id, 
                createdAt, 
                content, 
                userId);
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt){
            this.createdAt = createdAt;
            return this;
        }
        
        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder writer(Long userId){
            this.userId = userId;
            return this;
        }
    }
}

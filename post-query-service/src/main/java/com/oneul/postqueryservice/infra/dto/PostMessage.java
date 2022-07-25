package com.oneul.postqueryservice.infra.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class PostMessage {
    private String type;
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private String wirter;  

    public PostMessage() {}

    public PostMessage(String type, Long id, LocalDateTime createdAt, String content, String writer){
        this.type = type;
        this.id = id; 
        this.createdAt = createdAt;
        this.content = content;
        this.wirter = writer;
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

    public String getWriter(){
        return this.wirter;
    }

    public void setWriter(String writer){
        this.wirter = writer;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) {
            return true;
        }
        if(object == null || getClass() != object.getClass()){
            return false;
        }
        PostMessage that = (PostMessage)object;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
    
    @Override
    public String toString(){
        return "PostMessage["
            + "type: " + this.type
            + ", id: " + this.id
            + ", createdAt: " + this.createdAt
            + ", content: " + this.content
            + ", writer: " + this.wirter
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
        private String writer;

        public PostMessage build(){
            return new PostMessage(
                type,
                id, 
                createdAt, 
                content, 
                writer);
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

        public Builder writer(String writer){
            this.writer = writer;
            return this;
        }
    }
}

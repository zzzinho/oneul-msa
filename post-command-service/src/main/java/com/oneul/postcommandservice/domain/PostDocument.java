package com.oneul.postcommandservice.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PostDocument {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private String writer;

    public PostDocument() {}

    public PostDocument(Long id, LocalDateTime createdAt, String content, String writer){
        this.id = id;
        this.createdAt = createdAt;
        this.content = content;
        this.writer = writer;
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

    public void setContent(String content){
        this.content = content;
    }

    public String getWriter(){
        return this.writer;
    }

    public void setWriter(String writer){
        this.writer = writer;
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null || getClass() != object.getClass()){
            return false;
        }
        PostDocument that = (PostDocument)object;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString(){
        return "PostDocumnet["
            + "id: " + this.id
            + ", createdAt: " + this.createdAt
            + ", content: " + this.content
            + ", writer: " + this.writer
            + "]";
    }
}

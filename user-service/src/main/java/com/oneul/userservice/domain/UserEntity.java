package com.oneul.userservice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @JsonIgnore @Column(nullable = false)
    private String password;
    @JsonIgnore @CreatedDate
    private LocalDateTime createdAt;
    
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public UserEntity() {}
    
    public UserEntity(Long id, String username, String password, LocalDateTime createdAt){
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public UserEntity(UserEntity user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
    }
    
    @Override
    public boolean equals(Object object){
        if(this == object) {
            return true;
        }
        if(object == null || getClass() != object.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) object;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
    
    @Override
    public String toString(){
        return "userEntity["
            + "id: " + this.id
            + ", username: " + this.username
            + ", createdAt: " + this.createdAt
            + "]";
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private LocalDateTime createdAt;

        public UserEntity build() {
            return new UserEntity(
                id, 
                username, 
                password, 
                createdAt);
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt){
            this.createdAt = createdAt;
            return this;
        }
    }
}

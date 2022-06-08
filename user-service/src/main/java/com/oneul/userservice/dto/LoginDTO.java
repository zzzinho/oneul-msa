package com.oneul.userservice.dto;

import javax.validation.constraints.NotBlank;

import com.oneul.userservice.domain.UserEntity;

public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }
    
    public LoginDTO() {}

    public LoginDTO(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public UserEntity toEntity(){
        return UserEntity.builder()
            .username(username)
            .password(password)
            .build();
    }
}

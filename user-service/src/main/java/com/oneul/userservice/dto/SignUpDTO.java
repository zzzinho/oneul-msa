package com.oneul.userservice.dto;

import javax.validation.constraints.NotBlank;

import com.oneul.userservice.domain.UserEntity;

public class SignUpDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password1;
    @NotBlank
    private String password2;

    public void setUsername(String username ){
        this.username = username;
    } 

    public String getUsername(){
        return this.username;
    }

    public void setPassword1(String password1){
        this.password1 = password1;
    }

    public String getPassword1(){
        return this.password1;
    }

    public void setPassword2(String password2){
        this.password2 = password2;
    }

    public String getPassword2(){
        return this.password2;
    }
    
    public SignUpDTO() {}
    
    public SignUpDTO(String username, String password1, String password2){
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
            .username(this.username)
            .password(this.password1)
            .build();
    }
}

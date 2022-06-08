package com.oneul.userservice.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.oneul.userservice.domain.UserEntity;

@Service
public interface UserService {
    UserEntity signUp(UserEntity userEntity);
    UserEntity login(UserEntity userEntity, HttpSession httpSession);
    void logout(HttpSession httpSession);
}


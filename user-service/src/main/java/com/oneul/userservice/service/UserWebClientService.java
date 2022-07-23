package com.oneul.userservice.service;

import com.oneul.userservice.domain.UserEntity;

public interface UserWebClientService {
    UserEntity findByUserId(Long userId);
}

package com.oneul.userservice.service;

import com.oneul.userservice.dto.UserResponse;

public interface UserWebClientService {
    UserResponse findByUserId(Long userId);
}

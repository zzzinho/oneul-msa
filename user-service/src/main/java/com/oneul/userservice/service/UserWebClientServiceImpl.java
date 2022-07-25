package com.oneul.userservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oneul.userservice.dao.UserRepository;
import com.oneul.userservice.domain.UserEntity;
import com.oneul.userservice.dto.UserResponse;
import com.oneul.userservice.exception.NotFoundException;

@Service
@Transactional
public class UserWebClientServiceImpl implements UserWebClientService {
    private UserRepository userRepository;

    public UserWebClientServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse findByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("wrong user Id"));
        UserResponse userResponse = UserResponse.builder()
                                                .id(userEntity.getId())
                                                .username(userEntity.getUsername())
                                                .build();
        return userResponse;
    }
}

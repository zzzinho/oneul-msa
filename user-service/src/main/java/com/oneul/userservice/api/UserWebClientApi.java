package com.oneul.userservice.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oneul.userservice.domain.UserEntity;
import com.oneul.userservice.service.UserWebClientService;

@RestController
@RequestMapping(value = "/user/wc")
public class UserWebClientApi {
    private UserWebClientService userWebClientService;

    public UserWebClientApi(UserWebClientService userWebClientService){
        this.userWebClientService = userWebClientService;
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public UserEntity getUserByUserId(Long userId){
        UserEntity user = userWebClientService.findByUserId(userId);
        return user;
    }
}

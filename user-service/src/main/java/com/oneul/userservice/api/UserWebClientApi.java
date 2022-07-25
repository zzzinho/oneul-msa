package com.oneul.userservice.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oneul.userservice.dto.UserResponse;
import com.oneul.userservice.service.UserWebClientService;

@RestController
@RequestMapping(value = "/user/wc")
public class UserWebClientApi {
    private UserWebClientService userWebClientService;

    public UserWebClientApi(UserWebClientService userWebClientService){
        this.userWebClientService = userWebClientService;
    }
    
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponse getUserByUserId(@PathVariable Long userId){
        UserResponse user = userWebClientService.findByUserId(userId);
        return user;
    }
}

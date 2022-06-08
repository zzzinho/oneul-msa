package com.oneul.userservice.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oneul.userservice.domain.UserEntity;
import com.oneul.userservice.dto.LoginDTO;
import com.oneul.userservice.dto.SignUpDTO;
import com.oneul.userservice.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserApi {
    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserApi.class);

    public UserApi(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/signup/", method=RequestMethod.POST)
    public UserEntity signUp(@RequestBody SignUpDTO signUpDTO) {
        // TODO: password1, password2 같은지 validator로 검사
        UserEntity user = userService.signUp(signUpDTO.toEntity());
        log.info("signUp: " + user.toString());
        return user;
    }

    @RequestMapping(value="/login/", method=RequestMethod.POST)
    public UserEntity login(HttpSession httpSession, @RequestBody LoginDTO loginDTO) {
        UserEntity user = userService.login(loginDTO.toEntity(), httpSession);
        log.info("login: " + user.toString());
        return user;
    }
    
    @RequestMapping(value="/logout/", method=RequestMethod.POST)
    public String logout(HttpSession httpSession) {
        log.info("logout: " + httpSession.toString());
        userService.logout(httpSession);
        return "logout";
    }
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String hello() {
        return "hello";
    }
    
}

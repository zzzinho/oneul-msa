package com.oneul.userservice.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.oneul.userservice.dao.UserRepository;
import com.oneul.userservice.domain.UserEntity;
import com.oneul.userservice.exception.UserAlreadyExistException;
import com.oneul.userservice.exception.WrongUsernameAndPasswordException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserEntity signUp(UserEntity userEntity){
        if(userRepository.findByUsername(userEntity.getUsername()).isPresent()){
            throw new UserAlreadyExistException(userEntity.getUsername() + " is already exists");
        }
        
        UserEntity user = UserEntity.builder().username(userEntity.getUsername())
                                              .password(
                                                  passwordEncoder.encode(userEntity.getPassword()))
                                              .build();
        userRepository.save(user);
        log.info("user is created: " + user.toString());
        return user;
    }

    @Override
    public UserEntity login(UserEntity userEntity, HttpSession httpSession){
        UserEntity user =  userRepository.findByUsername(userEntity.getUsername())
                                         .orElseThrow(() -> new WrongUsernameAndPasswordException("wrong username"));

        if(!passwordEncoder.matches(userEntity.getPassword(), user.getPassword())){
            throw new WrongUsernameAndPasswordException("wrong passowrd");
        }

        log.info("login user: " + userEntity.toString());
        httpSession.setAttribute("user",user);

        log.info("session id: " + httpSession.getId());
        log.info("session value: " + httpSession.getAttribute("user"));
        log.info("get session: " + httpSession.getAttribute("user").toString());
        return user;
    }

    @Override
    public void logout(HttpSession httpSession){
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        if(user == null) return ;
        log.info("session id: " + httpSession.getId());
        log.info("session value: " + httpSession.getAttribute("user"));
        httpSession.removeAttribute("user");
    }
}


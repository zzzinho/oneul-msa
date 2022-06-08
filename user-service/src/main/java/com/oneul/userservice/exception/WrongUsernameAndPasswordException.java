package com.oneul.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "wrong username and password")
public class WrongUsernameAndPasswordException extends RuntimeException {
    public WrongUsernameAndPasswordException(String message){
        super(message);
    }
}

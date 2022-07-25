package com.oneul.postcommandservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "session is expired")
public class ExpiredSessionException extends RuntimeException {
    public ExpiredSessionException(String message){
        super(message);
    }
}

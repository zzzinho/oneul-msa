package com.oneul.postcommandservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "entity not found")
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}

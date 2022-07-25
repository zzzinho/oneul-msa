package com.oneul.postcommandservice.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ExpiredSessionException.class)
    protected ResponseEntity<String> handleExpiredSessionException(ExpiredSessionException e){
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<String> handleNotFoundException(NotFoundException e){
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

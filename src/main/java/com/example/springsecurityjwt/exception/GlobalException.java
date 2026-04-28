package com.example.springsecurityjwt.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex){
        return ex.getMessage();
    }

}

package com.audgnssweet.aop.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//프레임워크에서 exception을 낚아챌 수 있음.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String HandleAllException(Exception e) {
        return e.getMessage();
    }
}

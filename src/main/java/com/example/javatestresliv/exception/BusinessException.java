package com.example.javatestresliv.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private HttpStatus code;

    public BusinessException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}

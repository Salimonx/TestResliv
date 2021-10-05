package com.example.javatestresliv.exception;

import com.example.javatestresliv.dto.City;
import com.example.javatestresliv.dto.ExceptionResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ExceptionResp> BusinessException(BusinessException exeption) {
        return ExceptionResp.of(exeption.getMessage(), exeption.getCode()).getResponseEntity();
    }

}

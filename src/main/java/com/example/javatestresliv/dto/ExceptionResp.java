package com.example.javatestresliv.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

public class ExceptionResp {
    private String message;
    @JsonProperty("code")
    private int statusCode;

    public ExceptionResp(String message, @NotNull HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode.value();
    }

    public static ExceptionResp of(String message, HttpStatus statusCode) {
        return new ExceptionResp(message, statusCode);
    }

    @JsonIgnore
    public ResponseEntity<ExceptionResp> getResponseEntity() {
        return ResponseEntity.status(getStatusCode()).body(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}

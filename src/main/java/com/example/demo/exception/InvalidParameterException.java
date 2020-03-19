package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {
    public InvalidParameterException(String message) {
        super(message);
        this.setErrorCode("USER_INFO_NOT_INVALID");
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorType(ErrorType.Client);
    }
}

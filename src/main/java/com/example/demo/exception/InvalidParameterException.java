package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {
    public InvalidParameterException(String message) {
        super(message);
        this.setErrorCode(BizErrorCode.INVALID_PARAMTER);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorType(ErrorType.Client);
    }
}

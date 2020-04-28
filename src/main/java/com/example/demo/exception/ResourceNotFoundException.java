package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ServiceException {

    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setErrorType(ErrorType.Client);
        this.setErrorCode(BizErrorCode.RESOURCE_NOT_FOUND);
    }
}

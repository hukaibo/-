package com.example.demo.exception;

public enum BizErrorCode {
    NO_AUTHORIZED("NO_AUTHORIZED"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    INVALID_PARAMTER("INVALID_PARAMETER"),
    INCORRECT_CREDENTIALS("INCORRECT_CREDENTIALS");
    private String message;

    BizErrorCode(String message) {
        this.message = message;
    }
}

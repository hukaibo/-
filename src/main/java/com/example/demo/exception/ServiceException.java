package com.example.demo.exception;

public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode;
    private ServiceException.ErrorType errorType;
    public enum ErrorType{
        Client,
        Service,
        Unknow
    }
    public ServiceException(String message){
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}

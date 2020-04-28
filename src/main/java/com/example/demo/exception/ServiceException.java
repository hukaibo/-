package com.example.demo.exception;

public class ServiceException extends RuntimeException {
    private int statusCode;
    private BizErrorCode errorCode;
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

    public BizErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(BizErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}

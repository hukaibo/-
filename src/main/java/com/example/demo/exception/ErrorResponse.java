package com.example.demo.exception;


import lombok.Builder;
import lombok.Data;
//不用写get/set方法
@Data
//不用写builder
@Builder
public class ErrorResponse {
    private String Code;
    private ServiceException.ErrorType errorType;
    private String message;
    private int statusCode;
}

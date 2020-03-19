package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//异常处理拦截器
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> serviceException(ServiceException ex){
        ErrorResponse build = ErrorResponse.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .Code(ex.getErrorCode())
                .errorType(ex.getErrorType())
                .build();
        return ResponseEntity.status(ex.getStatusCode())
                .body(build);
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
//        ErrorResponse user_info_not_found = ErrorResponse.builder()
//                .statusCode(HttpStatus.NOT_FOUND.value())
//                .message(ex.getMessage())
//                .Code("USER_INFO_NOT_FOUND")
//                .errorType(ServiceException.ErrorType.Client)
//                .build();
//       return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
//                .body(user_info_not_found);
//    }
//    @ExceptionHandler(InvalidParameterException.class)
//    ResponseEntity<?> handleInvalidParameterException(InvalidParameterException ex){
//        ErrorResponse user_info_Invalid = ErrorResponse.builder()
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getMessage())
//                .Code("USER_INFO_NOT_INVALID")
//                .errorType(ServiceException.ErrorType.Client)
//                .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
//                .body(user_info_Invalid);
//    }
}

package com.example.demo.exception;


import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//异常处理拦截器（控制器增强）
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
    @ExceptionHandler(IncorrectCredentialsException.class)
    ResponseEntity<?> IncorrectCredentialsException(IncorrectCredentialsException ex){
        ErrorResponse build = ErrorResponse.builder()
                .statusCode(400)
                .message(ex.getMessage())
                .Code(BizErrorCode.INCORRECT_CREDENTIALS)
                .errorType(ServiceException.ErrorType.Client)
                .build();
        return ResponseEntity.status(400)
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

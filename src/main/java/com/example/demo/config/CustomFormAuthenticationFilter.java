package com.example.demo.config;

import com.example.demo.exception.BizErrorCode;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.example.demo.exception.BizErrorCode.NO_AUTHORIZED;

@Slf4j
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {

                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //saveRequest(request);
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ErrorResponse build = ErrorResponse.builder()
                    .Code(NO_AUTHORIZED)
                    .errorType(ServiceException.ErrorType.Client)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .message("No access for related url")
                    .build();
            try (PrintWriter writer = response.getWriter()){
                  writer.write(new ObjectMapper().writeValueAsString(build));
            }catch (IOException ex){
                 ex.printStackTrace();
                 return false;
            }
            return false;
        }
    }
}

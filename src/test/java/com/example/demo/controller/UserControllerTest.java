package com.example.demo.controller;

import com.example.demo.converter.c2s.UserInfoC2SConverter;
import com.example.demo.converter.p2c.UserInfoP2CConverter;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.manager.UserInfoManager;
import com.example.demo.model.common.UserInfo;
import com.example.demo.model.common.UserInfoBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserInfoManager userInfoManager;

    private UserInfoC2SConverter userInfoC2SConverter = new UserInfoC2SConverter();

    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userInfoManager, userInfoC2SConverter))
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @AfterEach
    void teardowm() {
        reset(userInfoManager);

    }

    @Test
    void getUserInfoByUserIdTest() throws Exception {
        Long userId = 10L;
        String username = "hardcore";
        String password = "hardcore";
        LocalDate createTime = LocalDate.now();
        UserInfo build = UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .build();
        doReturn(build).when(userInfoManager).getUserByUserId(anyLong());
        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"id\":10,\"username\":\"hardcore\",\"password\":\"hardcore\"}"));
        verify(userInfoManager).getUserByUserId(anyLong());

    }

    @Test
    void getUserInfoByUserIdTestInvalid() throws Exception {
        //Arrange
        Long userId = -100L;
        //Act
        doThrow(new ResourceNotFoundException(String.format("User %s was not found", userId)))
                .when(userInfoManager)
                .getUserByUserId(anyLong());
        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"errorType\":\"Client\",\"message\":\"User id -100 is invalid\",\"statusCode\":400,\"code\":\"USER_INFO_NOT_INVALID\"}"));
        //Assert
    }
}

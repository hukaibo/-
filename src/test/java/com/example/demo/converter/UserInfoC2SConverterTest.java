package com.example.demo.converter;

import com.example.demo.converter.c2s.UserInfoC2SConverter;
import com.example.demo.model.common.UserInfo;
import com.example.demo.model.common.UserInfoBuilder;
import com.google.common.base.Converter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserInfoC2SConverterTest {

    private UserInfoC2SConverter userInfoC2SConverter=new UserInfoC2SConverter();
    @Test
    void testDoForward(){
        //Arrange输入
        long userId = 1L;
        String username = "hardcore";
        String password = "hardcore";
        LocalDate createTime = LocalDate.now();
        UserInfo userInfo=UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .build();
        //Act行为
        com.example.demo.model.service.UserInfo result = userInfoC2SConverter.convert(userInfo);
        //Assert断言
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);


    }
    @Test
    void testDoBackward(){
        //Arrange输入
        long userId = 1L;
        String username = "hardcore";
        String password = "hardcore";
        LocalDate createTime = LocalDate.now();
        com.example.demo.model.service.UserInfo build = com.example.demo.model.service.UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .build();
        //Act行为
        UserInfo convert = userInfoC2SConverter.reverse().convert(build);

        //Assert断言
        assertThat(convert).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);

    }
}

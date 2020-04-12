package com.example.demo.converter;



import com.example.demo.converter.p2c.UserInfoP2CConverter;
import com.example.demo.model.persistence.UserInfo;
import com.example.demo.model.persistence.UserInfoBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoP2CConverterTest {
    private UserInfoP2CConverter userInfoP2CConverter=new UserInfoP2CConverter();
    @Test
    void testDoForward(){
        //Arrange输入
        long userId = 1L;
        String username = "hardcore";
        String password = "hardcore";
        LocalDate createTime = LocalDate.now();
        UserInfo build = UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .build();
        //Act行为
        com.example.demo.model.common.UserInfo convert = userInfoP2CConverter.convert(build);
        //Assert断言
        assertThat(convert).isNotNull()
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
        com.example.demo.model.common.UserInfo build = com.example.demo.model.common.UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .build();
        //Act行为
        UserInfo convert = userInfoP2CConverter.reverse().convert(build);

        //Assert断言
        assertThat(convert).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);

    }
}

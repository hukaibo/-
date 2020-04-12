package com.example.demo.dao;

import com.example.demo.dao.Mapper.UserInfoMapper;
import com.example.demo.manager.UserInfoManager;
import com.example.demo.model.persistence.UserInfo;
import com.example.demo.model.persistence.UserInfoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class UserInfoDAOTest {

    @Mock
    private UserInfoMapper userInfoMapper;
    @Mock
    private UserInfoDAO userInfoDAO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userInfoDAO = new UserInfoDAOImpl(userInfoMapper);
    }

    @Test
    public void testGetUserInfoById() {
        //Arrange输入
        Long userId = 100L;
        String username = "hardcore";
        String password = "hardcore";
        LocalDate now = LocalDate.now();
        UserInfo build = UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .withUpdateTime(now)
                .build();
        doReturn(build).when(userInfoMapper).getUserInfoByUserId(userId);
        //Act行为
        UserInfo userInfoById = userInfoDAO.getUserInfoById(userId);
        //Assert判断
        assertThat(userInfoById).isNotNull()
                .hasFieldOrPropertyWithValue("id",userId)
                .hasFieldOrPropertyWithValue("username",username)
                .hasFieldOrPropertyWithValue("password",password);
        verify(userInfoMapper).getUserInfoByUserId(userId);
    }
}

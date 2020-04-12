package com.example.demo.manager;

import com.example.demo.converter.p2c.UserInfoP2CConverter;
import com.example.demo.dao.Mapper.UserInfoMapper;
import com.example.demo.dao.UserInfoDAO;
import com.example.demo.dao.UserInfoDAOImpl;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.common.UserInfo;
import com.example.demo.model.persistence.UserInfoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserInfoManagerTest {
    private UserInfoManager userInfoManager;
    @Mock
    private UserInfoDAO userInfoDAO;

    @BeforeEach
    void setup() {
        //用Mock注解时，下面这行代码一定要写
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, new UserInfoP2CConverter());
    }

    @Test
    void testGetUserInfoByUserId() {
        //Arrange输入
        long userId = 1L;
        String username = "hardcore";
        String password = "hardcore";
        LocalDate createTime = LocalDate.now();

        com.example.demo.model.persistence.UserInfo build = UserInfoBuilder.anUserInfo()
                .withId(userId)
                .withUsername(username)
                .withPassword(password)
                .withCreateTime(createTime)
                .build();
        doReturn(build).when(userInfoDAO).getUserInfoById(userId);
        //Act行为
        UserInfo userByUserId = userInfoManager.getUserByUserId(userId);
        //Assert判断
        assertThat(userByUserId).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
        verify(userInfoDAO,times(2)).getUserInfoById(userId);

    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() {
        long userId = -1L;
        doReturn(null).when(userInfoDAO).getUserInfoById(userId);
        assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserByUserId(userId));
        verify(userInfoDAO).getUserInfoById(userId);
    }

    @Test
    void testadd() {
        List mockedList = mock(List.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn(new RuntimeException());
        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        //verify的参数中默认是调用1次的，下面程序的意思是是否get(0)被调用了1次。
        verify(mockedList).get(0);
        verify(mockedList).get(1);


    }
}

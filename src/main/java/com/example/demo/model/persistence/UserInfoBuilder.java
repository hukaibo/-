package com.example.demo.model.persistence;

import java.time.LocalDate;

public final class UserInfoBuilder {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private LocalDate createTime;
    private LocalDate updateTime;

    private UserInfoBuilder() {
    }

    public static UserInfoBuilder anUserInfo() {
        return new UserInfoBuilder();
    }

    public UserInfoBuilder withId(Long id) {
        this.id = id;
        return this;
    }


    public UserInfoBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserInfoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    public UserInfoBuilder withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public UserInfoBuilder withCreateTime(LocalDate createTime) {
        this.createTime = createTime;
        return this;
    }

    public UserInfoBuilder withUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public UserInfo build() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setSalt(salt);
        userInfo.setCreateTime(createTime);
        userInfo.setUpdateTime(updateTime);
        return userInfo;
    }
}

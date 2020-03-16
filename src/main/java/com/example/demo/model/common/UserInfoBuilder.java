package com.example.demo.model.common;


public final class UserInfoBuilder {
    private Long id;
    private String username;
    private String password;
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

    public UserInfo build() {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        return userInfo;
    }
}

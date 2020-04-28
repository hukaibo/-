package com.example.demo.model.service;


import com.fasterxml.jackson.annotation.JsonInclude;


//service是给前端展示的UserInfo
//忽略返回为null的字段
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {

    private Long id;
    private String username;
    private String password;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1.0/greeting")
public class HelloController {
    @GetMapping
    public String hello(@RequestParam("name") String name){

        return String.format("hello,%s",name);
    }
}

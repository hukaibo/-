package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import javax.swing.text.AbstractDocument;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    HelloController helloController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(helloController).build();
    }
    @Test
    void testSayHello() throws Exception {
        //Arrange输入
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/v1.0/greeting");
        mockMvc.perform(mockHttpServletRequestBuilder.param("name","hukaibo"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello,hukaibo"));


        //Act行为

        //Assert
    }
}

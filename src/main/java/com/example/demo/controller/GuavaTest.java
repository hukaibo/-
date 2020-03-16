package com.example.demo.controller;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MultimapBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuavaTest {
    public static void main(String[] args) {
        GuavaTest guavaTest=new GuavaTest();
        Integer a=null;
        Integer b= 10;
        List<String> ilist= Lists.newArrayList();
        ListMultimap<Object, Object> build = MultimapBuilder.hashKeys().arrayListValues().build();
       // Map<String,String> build=new HashMap<>();
        build.put("a",1);
        build.put("a",1);
        System.out.println(build.get("a"));

    }
}

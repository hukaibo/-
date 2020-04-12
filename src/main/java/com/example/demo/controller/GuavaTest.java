package com.example.demo.controller;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MultimapBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class GuavaTest  {
    public static void main(String[] args) {
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println(addition.operation(4,5));

    }
    interface MathOperation {
        int operation(int a, int b);
    }
}

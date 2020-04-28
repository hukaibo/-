package com.example.demo.converter.p2c;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static int a = 0;
    static boolean flag = false;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("1");
            System.out.println("2");
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            System.out.println("a");
            System.out.println("b");
        }
    }

    public static void main(String[] args) {
        for (int j = 1; j < 10; j++) {
            ThreadA threadA = new ThreadA();
            ThreadB threadB = new ThreadB();
            threadB.start();
            threadA.start();
        }

    }
}


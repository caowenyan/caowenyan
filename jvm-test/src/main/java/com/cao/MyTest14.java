package com.cao;

import java.io.IOException;

/**
 数组类的对象不是被类加载器创建的，而是在需要的时候自动被JVM创建的
 数组类的类加载器和他元素的类加载器是一致的
 原始类型是没有类加载器的
 */
public class MyTest14 {
    public static void main(String[] args) throws IOException {
        String[]strings = new String[1];
        System.out.println(strings.getClass().getClassLoader());

        MyTest14[]test14s = new MyTest14[1];
        System.out.println(test14s.getClass().getClassLoader());

        // 不同于String数组的类加载器，String的是启动类加载器，此处是没有类加载器
        int[]ints = new int[1];
        System.out.println(ints.getClass().getClassLoader());

    }
}

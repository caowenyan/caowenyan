package com.cao;

import java.io.IOException;

public class MyTest14 {
    public static void main(String[] args) throws IOException {
        String[]strings = new String[1];
        System.out.println(strings.getClass().getClassLoader());

        MyTest14[]test14s = new MyTest14[1];
        System.out.println(test14s.getClass().getClassLoader());
    }
}

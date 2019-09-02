package com.cao;

public class MyTest8 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        Class<?> clazz1 = Class.forName("com.cao.C");
        System.out.println(clazz1.getClassLoader());
    }
}
class C {}
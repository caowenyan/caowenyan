package com.cao;

import java.util.Random;
/**
 *   当一个常量的值并非编译期间可以确定的，那么其值就不会被放到调用类的常量池中， 
 * 这是在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化  三次结果的值是一样的
 */
public class MyTest4 {
    public static void main(String[] args) {
        System.out.println(MyParent4.str);
        System.out.println(MyParent4.str);
        System.out.println(MyParent4.str);
    }
}

class MyParent4 {
    public static final int str = new Random().nextInt();

    static {
        System.out.println("static block");
    }
}
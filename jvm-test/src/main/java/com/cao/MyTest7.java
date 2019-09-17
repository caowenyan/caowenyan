package com.cao;

/**
 * 接口类的初始化不会导致父接口的初始化
 */
public class MyTest7 {
    public static void main(String[] args) {
//        System.out.println(MySon7.parent);
        System.out.println(MySon7.son);
    }
}

interface MyParent7 {
    StringBuilder parent = new StringBuilder("parent");
    Thread thread = new Thread(){
        {
            System.out.println("interface final field init");
        }
    };
}

interface MySon7 extends MyParent7{
    StringBuilder son = new StringBuilder("son");
}
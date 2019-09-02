package com.cao;

public class MyTest7 {
    public static void main(String[] args) {
        System.out.println(MySon7.parent);
    }
}

interface MyParent7 {
    StringBuilder parent = new StringBuilder("32");
    Thread thread = new Thread(){
        {
            System.out.println("interface final field init");
        }
    };
}

interface MySon7 extends MyParent7{
    StringBuilder son = new StringBuilder("32");
}
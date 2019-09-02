package com.cao;

class Parent9 {
    static int a = 3;
    static {
        System.out.println("parent static block");
    }
}
class Child9 extends Parent9 {
    static int b = 3;
    static {
        System.out.println("child static block");
    }
}
public class MyTest9 {
    static {
        System.out.println("mytest9 static block");
    }
    public static void main(String[] args) {
        System.out.println(Child9.b);
    }
}

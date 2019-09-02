package com.cao;

class Parent10 {
    static int a = 3;
    static {
        System.out.println("parent static block");
    }
}
class Child10 extends Parent10 {
    static int b = 3;
    static {
        System.out.println("child static block");
    }
}
public class MyTest10 {
    static {
        System.out.println("mytest10 static block");
    }
    public static void main(String[] args) {
        Parent10 parent10;
        System.out.println("--------------");
        parent10 = new Parent10();
        System.out.println("--------------");
        System.out.println(parent10.a);
        System.out.println("--------------");
        System.out.println(Child10.b);
    }
}
package com.cao;

public class MyTest11 {
    public static void main(String[] args) {
        System.out.println(Child11.a);
        Child11.doSomething();
    }
}
class Parent11 {
    static int a = 3;
    static {
        System.out.println("parent static block");
    }
    static void doSomething() {
        System.out.println("do something");
    }
}
class Child11 extends Parent11 {
    static {
        System.out.println("child static block");
    }
}

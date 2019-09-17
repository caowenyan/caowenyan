package com.cao;

/**
 * 注意此处说明的问题：执行getStatic和putStatic和invokeStatic效果是一样的，只是初始化持有具体的类
 */
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

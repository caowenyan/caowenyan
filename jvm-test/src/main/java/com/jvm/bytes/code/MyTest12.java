package com.jvm.bytes.code;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 方法重写：
 1.两个方法的方法名、参数列表必须一致，子类返回值必须和父类的返回值相同或者是其子集
 2.子类抛出的异常不能超过父类抛出的异常
 3.子类方法的访问级别不能低于父类级别
 */
public class MyTest12 {
    public static void main(String[] args) throws Exception {
        MyTest12 test = new MyTest12Son();
        test.test("发顺丰");

    }
    public AbstractList test(String s) throws Exception{
        System.out.println("parent" + s);
        return null;
    }
}
class MyTest12Son extends MyTest12 {
    @Override
    public ArrayList test(String s) throws NullPointerException {
        System.out.println("son" + s);
        return null;
    }
}
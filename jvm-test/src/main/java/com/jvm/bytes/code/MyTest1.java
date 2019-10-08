package com.jvm.bytes.code;

/**
 * 进入终端的target/classes下
 * javap -c com.jvm.bytes.code.MyTest1
 * javap -verbose com.jvm.bytes.code.MyTest1
 */
public class MyTest1 {
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

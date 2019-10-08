package com.jvm.bytes.code;

/**
 * 进入终端的target/classes下
 * javap -verbose -p com.jvm.bytes.code.MyTest2
 */
public class MyTest2 {
    String str = "Hello world";
    private int x = 8;
    public static Integer in = 10;

    public static void main(String[] args) {
        MyTest2 test2 = new MyTest2();

        test2.setX(20);

        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }
}

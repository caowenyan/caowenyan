package com.jvm.bytes.code;

/**
 * 进入终端的target/classes下
 * javap -verbose -p com.jvm.bytes.code.MyTest3
 */
public class MyTest3 {
    String str = "Hello world";
    private int x = 8;
    public static Integer in = 10;

    public static void main(String[] args) {
        MyTest3 test3 = new MyTest3();

        test3.setX(20);

        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }
    private void test (String s) {
        System.out.println(this.str);
        synchronized (s) {
            System.out.println(s);
        }
        System.out.println(in);
    }
}

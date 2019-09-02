package com.cao;

public class MyTest6 {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.i1);
        System.out.println(Singleton.i2);

        System.out.println(Singleton2.getInstance());
        System.out.println(Singleton2.i1);
        System.out.println(Singleton2.i2);
    }
}
class Singleton {
    public static int i1;
    public static int i2 = 0;
    private static Singleton single = new Singleton();

    private Singleton() {
        i1++;
        i2++;
    }

    public static Singleton getInstance() {
        return single;
    }
}

class Singleton2 {
    public static int i1;
    private static Singleton2 single = new Singleton2();

    private Singleton2() {
        i1 ++;
        i2 ++; //准备阶段的重要意义 
    }

    public static int i2 = 0;

    public static Singleton2 getInstance() {
        return single;
    }
}
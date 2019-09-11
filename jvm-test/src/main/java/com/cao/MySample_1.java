package com.cao;

public class MySample_1 {
    static {
        System.out.println("mySample static block");
    }
    public MySample_1() {
        System.out.println("mySample is loaded by: " + this.getClass().getClassLoader());
        new MyCat_1();
    }
}

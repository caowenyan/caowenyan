package com.cao;

public class MySample {
    static {
        System.out.println("mySample static block");
    }
    public MySample() {
        System.out.println("mySample is loaded by: " + this.getClass().getClassLoader());
        new MyCat();
    }
}

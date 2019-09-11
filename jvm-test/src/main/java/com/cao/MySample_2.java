package com.cao;

public class MySample_2 {
    static {
        System.out.println("mySample static block");
    }
    public MySample_2() {
        System.out.println("mySample is loaded by: " + this.getClass().getClassLoader());
        new MyCat();
        System.out.println("from mySample: " + MyCat.class);
    }
}

package com.cao;

public class MyCat_1 {
    static {
        System.out.println("myCat static block");
    }
    public MyCat_1() {
        System.out.println("myCat is loaded by: " + this.getClass().getClassLoader());
        System.out.println("from myCat: " + MySample_1.class);
    }
}

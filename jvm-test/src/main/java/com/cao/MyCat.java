package com.cao;

public class MyCat {
    static {
        System.out.println("myCat static block");
    }
    public MyCat() {
        System.out.println("myCat is loaded by: " + this.getClass().getClassLoader());
    }
}

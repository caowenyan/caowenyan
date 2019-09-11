package com.cao;

/**
 * 不同类加载器所加载的路径、jar等
 */
public class MyTest18 {
    public static void main(String[] args) {
        System.out.println("boot class loader: " + System.getProperty("sun.boot.class.path"));
        System.out.println();
        System.out.println("ext class loader: " + System.getProperty("java.ext.dirs"));
        System.out.println();
        System.out.println("app class loader: " + System.getProperty("java.class.path"));
    }
}

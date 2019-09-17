package com.cao;

/**
 1. 正常情况下类加载器是AppClassLoader
 2. 若是将MySample移动到启动类加载器的路径下：/Library/Java/JavaVirtualMachines/jdk1.8.0_151.jdk/Contents/Home/jre/classes
    则显示类加载器是null，即启动类加载器
 */
public class MyTest18_1 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader("loader1");
        loader.setPath("/Users/caowenyan/Desktop/");

        Class<?> clazz = loader.loadClass("com.cao.MySample");
        System.out.println("class: " + clazz.hashCode());
        System.out.println("class loader: " + clazz.getClassLoader());
    }
}

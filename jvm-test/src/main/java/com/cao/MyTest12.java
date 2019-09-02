package com.cao;

/**
 * 调用ClassLoader类的loadClass方法加载一个类时，并不是对类的主动使用，不会导致类的初始化
 */
public class MyTest12 {
    public static void main(String[] args) throws Exception{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Class<?> clazz = classLoader.loadClass("com.cao.CL");
        System.out.println(clazz);

        System.out.println("-----------");

        clazz = Class.forName("com.cao.CL");
        System.out.println(clazz);
    }
}
class CL {
    static {
        System.out.println("CL static block");
    }
}
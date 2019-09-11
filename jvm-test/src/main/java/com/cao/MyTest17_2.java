package com.cao;

/**
前提：
 将MySample放置到桌面的文件夹中，MyCat还在classpath下，
结论：
 通过自定义类加载器加载MySample，然后加载MyCat，有类加载器的可见性原理是可以的
结果：
 findClass invoke: com.cao.MySample
 class loader name: loader1
 class: 1639705018
 mySample static block
 mySample is loaded by: [loader1]
 myCat static block
 myCat is loaded by: sun.misc.Launcher$AppClassLoader@135fbaa4
 */
public class MyTest17_2 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader("loader1");
        loader.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz = loader.loadClass("com.cao.MySample");
        System.out.println("class: " + clazz.hashCode());

        Object object = clazz.newInstance();
    }
}

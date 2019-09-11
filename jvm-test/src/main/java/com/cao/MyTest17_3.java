package com.cao;

/**
前提：
 将MyCat放置到桌面的文件夹中，MySample还在classpath下，
结论：
 通过系统类加载器加载MySample，然后加载MyCat，但是系统类加载器无法加载MyCat，所以报错
 */
public class MyTest17_3 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader("loader1");
        loader.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz = loader.loadClass("com.cao.MySample");
        System.out.println("class: " + clazz.hashCode());

        Object object = clazz.newInstance();
    }
}

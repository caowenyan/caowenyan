package com.cao;

import java.lang.reflect.Method;

/**
 判断两个类是否一样，开始受限于来自于两个类加载器认为是不一样的
 但是因为他们都是通过系统类加载器进行加载的，所以是同一个类，因为是相等的

 注意这个地方使用的是setMyPerson(Object object)，是Object而不是MyPerson，
 主要是因为若是不这么写Method处class对象无法编译通过，所以无法看到想要的结果
 */
public class MyTest20 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz1 = loader1.loadClass("com.cao.MyPerson");

        MyClassLoader loader2 = new MyClassLoader("loader2");
        loader2.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz2 = loader2.loadClass("com.cao.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        // object1 对象，object2是参数
        method.invoke(object1, object2);
    }
}

package com.cao;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 设置当前线程的上下文类加载器为系统类加载器，和上面的例子是一样的
 */
public class MyTest26_1 {
    public static void main(String[] args) {
        // 开始一直看为什么没有设置成功自定义类加载器，后面才知道用的自定义加载器的加载器，也就是系统类加载器
        Thread.currentThread().setContextClassLoader(MyClassLoader.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println("==============");

        MyClassLoader classLoader = new MyClassLoader("classLoader");
        Thread.currentThread().setContextClassLoader(classLoader);
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println("==============");

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver: " + driver.getClass() + ", loader: " + driver.getClass().getClassLoader());
        }
        System.out.println("当前线程的上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
    }
}

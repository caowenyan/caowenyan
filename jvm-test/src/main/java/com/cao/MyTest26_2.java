package com.cao;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 加载jvm参数配置：-XX:+TraceClassLoading
 根本无法加载到spi的实现类，以为根本无法通过扩展类加载器找到该文件
 */
public class MyTest26_2 {
    public static void main(String[] args) {
        // 设置当前线程的上下文类加载器为扩展类加载器，所以导致java.class.path路径下的类无法被加载到
        Thread.currentThread().setContextClassLoader(MyClassLoader.class.getClassLoader().getParent());
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

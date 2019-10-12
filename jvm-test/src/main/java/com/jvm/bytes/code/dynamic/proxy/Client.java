package com.jvm.bytes.code.dynamic.proxy;

import java.lang.reflect.Proxy;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月12日 10:21
 */
public class Client {
    public static void main(String[] args) {
        // 通过源码可以得知，这个参数的意思是将生成的字节码存到磁盘上，目录是com/sun/proxy
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject object = new RealSubject();
        Class clazz = object.getClass();
        DynamicSubjectHandler handler = new DynamicSubjectHandler(object);
        Subject subject = (Subject) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
        subject.test();
        System.out.println(subject.toString());
    }
}

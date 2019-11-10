package com.jvm.bytes.code.dynamic.proxy;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月12日 10:16
 */
public class RealSubject implements Subject {
    @Override
    public void test() {
        System.out.println("real subject");
        // 此处调用test2的主要原因是因为主要想看看生成的代理对象是如何处理这个this的
        this.test2();
    }

    @Override
    public void test2() {
        System.out.println("test2 real subject");
    }

    @Override
    public String toString() {
        return "real";
    }
}

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
    }

    @Override
    public String toString() {
        return "real";
    }
}

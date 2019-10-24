package com.jvm.bytes.code;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月18日 15:17
 */
public class MyTest11 {
    public static void main(String[] args) {
        float f = 1.20f;
        // 是把(f+1)的值转化为dubbo
        double d = f + 1;
        System.out.println(d);
        Float f1 = 230.2f;
        // 上下两个的差别：一个是通过f2d，一个是通过Float.valueOf
        double d1 = f1 + 1000;
        Float f2 = f1 + 1000;
//        Double d2 = f1 + 1000;
        System.out.println(d1);
        System.out.println(f2);
    }
}
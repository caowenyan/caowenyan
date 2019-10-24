package com.jvm.memory;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月18日 11:44
 */
public class MyTest2 {
    interface Interface0 {
        int A = 0;
    }
    interface Interface1 extends Interface0 {
        int A = 1;
    }
    interface Interface2 {
        int A = 2;
    }
    static class Parent implements Interface1 {
        public static int A = 10;
    }
    static class Sub extends Parent implements Interface2 {
        public static int A = 11;
    }

    public static void main(String[] args) {
        // 如果A不存在sub类中，则会报问题，
        // 主要和类加载器加载字段有关，不能同时存在于父类和接口中，无法确定调用哪个
        System.out.println(Sub.A);
    }
}

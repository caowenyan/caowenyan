package com.jvm.memory;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月23日 15:32
 */
public class MyTest3 {
    public static void main(String[] args) {
        // 所有的类型都是通过Integer.valueOf转化来的
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        // 整数常量池
        System.out.println((c == d));
        // 不在常量池内，通过new产生的
        System.out.println(e == f);
        // 这个地方a+b做了拆箱，c也是
        System.out.println(c == (a+b));
        // equals不会做拆箱，所以后面的a+b转化为了Integer
        System.out.println(c.equals(a+b));
        // 拆箱，a+b最后转化为l
        System.out.println(g == (a+b));
        System.out.println(g.equals(a+b));
    }
}

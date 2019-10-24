package com.jvm.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 注意这个最后执行的是拆箱，会先执行原有的类型以及他的父类型，最后才是拆箱
 * @date 2019年10月23日 15:57
 */
public class MyTest4 {
    // 看看变量的顺序
    {
        a = 1;
    }
    int a = 4;
    public static void main(String[] args) {
//        System.out.println(new MyTest4().a);

        Map<String, Long> map = new HashMap<>();
        map.put("hello", 1L);
        map.put("world", 1003L);
        System.out.println(map.get("hello"));
        System.out.println(map.get("hello1"));
        print(map.get("hello"));
        print(map.get("hello1"));
    }

//    static void print(Long s) {
//        System.out.println("Long: " + s);
//    }
    static void print(long s) {
        System.out.println("long: " + s);
    }

//    static void print(Object s) {
//        System.out.println("object: " + s);
//    }
}

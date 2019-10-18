package com.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月16日 10:01
 */
public class MyTest1 {
    public static void main(String[] args) {
        String string = new StringBuilder().append("发顺丰").append("发送").toString();
        System.out.println(string == string.intern());

        String string1 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(string1 == string1.intern());

        String string3 = new StringBuilder().append("lo").append("ng").toString();
        System.out.println(string3 == string3.intern());

        String string2 = new StringBuilder().append("My").append("Test1").toString();
        System.out.println(string2 == string2.intern());

    }
}

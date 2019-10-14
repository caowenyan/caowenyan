package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 相关的题目：a,b交换顺序不用临时变量，完全通过异或去实现
 * @date 2019年10月14日 18:32
 */
public class Test47 {
    /**
     * 不用加减乘除做加法
     */
    public static int add(int a ,int b) {
        if (b  == 0) {
            return a;
        }
        if (a  == 0) {
            return b;
        }
        return add((a&b)<<1,a^b);
    }

    public static void main(String[] args) {
        System.out.println(add(1, 2)  == (1 + 2));
        System.out.println(add(13, 34) == (13 + 34));
        System.out.println(add(19, 95) == (19 + 95));
        System.out.println(add(865, 245) == (865 + 245));
    }
}

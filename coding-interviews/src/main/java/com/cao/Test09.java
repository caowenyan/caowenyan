package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月18日 12:27
 */
public class Test09 {
    /**
     * 写一个函数，输入n，求斐波那契（Fibonacci) 数列的第n项
     * @param n Fibonacci数的项数
     * @return 第n项的结果
     */
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new RuntimeException("参数异常");
        }
        int min = 0;
        if (n == 0) {
            return min;
        }
        int max = 1;
        if (n == 1) {
            return max;
        }
        int fin = 0;
        for (int i = 2; i <= n ; i ++ ) {
            fin = min + max;
            min = max;
            max = fin;
        }
        return fin;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
    }
}

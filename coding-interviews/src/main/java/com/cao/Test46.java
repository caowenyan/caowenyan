package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月14日 18:04
 */
public class Test46 {
    /**
     * 求1+2+3+ ... +n
     * 不能使用乘除法、for、while、if、else、switch等关键词和条件判断语句
     *
     * 解法1：1+2+3+ ... +n = (n-1）*n/2 = (n^2-n)>>1
     */
    public static int getSum(int n) {
       return (int)(Math.pow(n, 2) + n) >> 1;
    }

    /**
     * 通过短路运算来实现
     */
    public static int getSum2(int n) {
        int result=0;
        boolean flag = n > 0 && (result = getSum2(n - 1) + n) > 0;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getSum(10));
        System.out.println(getSum2(10));
    }
}

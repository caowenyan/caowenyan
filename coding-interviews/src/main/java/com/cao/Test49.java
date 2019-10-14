package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月14日 18:56
 */
public class Test49 {
    /**
     * 题目：实现一个函数stringToInt,实现把字符串转换成整数这个功能，
     * 不能使用atoi或者其他类似的库函数。
     *
     * @param num
     * @return
     */
    public static int stringToInt(String num) {
        if (num == null || num.length() == 0) {
            return 0;
        }
        // 表示正负数
        boolean flag = true;
        boolean isContinuous0 = true;

        long sum = 0;

        for (int i = 0 ;i < num.length(); i ++) {
            if (num.charAt(i) == '-' &&i == 0) {
                flag = false;
                continue;
            }
            if (num.charAt(i) == '+' &&i == 0) {
                continue;
            }
            if (num.charAt(i) < '0' || num.charAt(i) > '9') {
                throw new RuntimeException("非法的输入");
            }
            if (isContinuous0 && num.charAt(i) == '0') {
                continue;
            }
            isContinuous0 = false;
            sum = sum * 10 + (num.charAt(i) - '0');
            // 注意这个地方整数和负数的范围不一样，所以需要特殊处理一下
            if (flag && sum > Integer.MAX_VALUE) {
                throw new RuntimeException("整数溢出");
            }
            if (!flag && sum - 1 > Integer.MAX_VALUE) {
                throw new RuntimeException("整数溢出");
            }
        }
        if (!flag) {
            sum = -sum;
        }
        return (int)sum;
    }
    public static void main(String[] args) {
        System.out.println(stringToInt("123"));
        System.out.println(stringToInt("+123"));
        System.out.println(stringToInt("-123"));
        System.out.println(stringToInt("+2147483647"));
        System.out.println(stringToInt("-2147483647"));
        System.out.println(stringToInt("-2147483648"));
        System.out.println(stringToInt("+2147483648"));
//        System.out.println(stringToInt("1a123"));
//        System.out.println(stringToInt("+2147483649"));
        System.out.println(stringToInt("-2147483649"));
//        System.out.println(stringToInt("+"));
//        System.out.println(stringToInt("-"));
    }
}

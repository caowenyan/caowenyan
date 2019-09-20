package com.cao;

/**
 误区1：指数可以为0或者负数
 误区2：若除数是0的情况，即求0的负整数次方
 */
public class Test11 {
    /**
     * 实现函数double Power(double base, int exponent)，求base的exponent次方。
     * 不得使用库函数，同时不需要考虑大数问题。
     *
     * @param base     指次
     * @param exponent 幂
     * @return 结果
     */
    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        // 0的指数不能小于0，无法计算了
        if (Math.abs(base) <= 0.00000001 && exponent < 0) {
            throw new RuntimeException("invalid input.");
        }

        // 是否是整数
        boolean positive = false;
        if (exponent < 0) {
            positive = true;
            exponent = -exponent;
        }
        double result = powerStep(base, exponent);
//        double result = powerWithUnsignedExponent(base, exponent);
        if (positive) {
            result = 1.0/result;
        }
        return result;
    }

    /**
     * 一步步计算幂次方
     */
    private static double powerStep(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i ++) {
            result *= base;
        }
        return result;
    }

    /**
     * 二倍法计算幂次方
     */
    private static double powerWithUnsignedExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double result = powerWithUnsignedExponent(base, exponent >> 1);
        result *= result;
        if ((exponent&1) == 1) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(0.0000000000000000000000001111 == 0);
        System.out.println(0.0000000000000000000000000000 == 0);

        System.out.println(power(2, -4));
        System.out.println(power(2, 4));
        System.out.println(power(2, 0));
        System.out.println(power(0.00000000000000000000000000001, 1));
        System.out.println(power(0.00000000000000000000000000001, 0));
        System.out.println(power(0.00000000000000000000000000000, 0));
        System.out.println(power(0, 1));
        System.out.println(power(0, 0));
//        System.out.println(power(0.00000000000000000000000000001, -1));
        System.out.println(power(0, -1));
    }
}

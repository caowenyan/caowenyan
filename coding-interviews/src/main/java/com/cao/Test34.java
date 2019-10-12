package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月12日 16:33
 */
public class Test34 {
    /**
     * 丑数是只含有因子2、3、5的数称作为丑数。
     * 例如6，8是丑数，6=2*3，8=2*2*2
     * 但是14不是，因为14=2*7
     * 习惯上我们把1当做第一个丑数。
     *
     * 找第index个丑数，速度太慢
     *
     * @param index 第index个丑数
     * @return 对应的丑数值
     */
    public static int getUglyNumber1(int index) {
        if (index <= 0) {
            return 0;
        }
        int uglyNum = 0;
        int current = 0;
        while (uglyNum < index) {
            if (isUgly(++current)) {
                uglyNum ++;
            }
        }
        return current;
    }
    /**
     * 判断一个数是否只有2，3，5因子（丑数）或是1
     *
     * @param num 待判断的数，非负
     * @return true是丑数，false丑数
     */
    private static boolean isUgly(int num) {
        while (num % 2 == 0) {
            num/=2;
        }
        while (num % 3 == 0) {
            num/=3;
        }
        while (num % 5 == 0) {
            num/=5;
        }
        if (num == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        test1();
    }

    /**
     * 找第index个丑数，【第二种方法】
     *
     * @param index 第index个丑数
     * @return 对应的丑数值
     */
    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] pUglyNumbers = new int[index];
        pUglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        // 这3个参数表示计算到2，3，5的最小的索引
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        while (nextUglyIndex < index) {
            int c2 = pUglyNumbers[p2] * 2;
            int c3 = pUglyNumbers[p3] * 3;
            int c5 = pUglyNumbers[p5] * 5;
            // 开始写的获取最小值，导致问题
            // int min = c2 > c3 ? (c3 > c5 ? c5 : c3) :c2;
            int min = c2 > c3 ? c3 : c2;
            min = min > c5 ? c5 : min;
            if (c2 == min) {
                p2++;
            }
            // 下面不用else if的主要原因是可能值一样就需要往后计算了，不能插入相等的值
            if (c3 == min) {
                p3++;
            }
            if (c5 == min){
                p5++;
            }
            pUglyNumbers[nextUglyIndex ++] = min;
        }
        return pUglyNumbers[index - 1];
    }
    private static void test1() {
        System.out.println(getUglyNumber(1) == 1);
        System.out.println(getUglyNumber(2) == 2);
        System.out.println(getUglyNumber(3) == 3);
        System.out.println(getUglyNumber(4) == 4);
        System.out.println(getUglyNumber(5) == 5);
        System.out.println(getUglyNumber(6) == 6);
        System.out.println(getUglyNumber(7) == 8);
        System.out.println(getUglyNumber(8) == 9);
        System.out.println(getUglyNumber(9) == 10);
        System.out.println(getUglyNumber(10) == 12);
        System.out.println(getUglyNumber(11) == 15);
        System.out.println(getUglyNumber(1500) == 859963392);
        System.out.println(getUglyNumber(0) == 0);
    }
}

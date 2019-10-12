package com.arithmetic;

/**
 给出一个正整数，从该整数中去掉k个数字，要求剩下的数字形成的整数尽可能的小

 例如：
 12345  移除1位：1234   移除2位：123
 54321  移除1位：4321   移除2位：321
 32541  移除1位：2541   移除2位：241
 32451  移除1位：2451   移除2位：241

 综合得出移除第一个出现倒序的数字，若是没有移除最后一位
 */
public class MinDateAfterRemoveK {
    public static int removeKDigit(String num, int k) {
        if (k <= 0 || num == null || num.length() <= k) {
            return 0;
        }
        while (k > 0) {
            // 是否删除过，若都是正序，删除最后一位，若出现逆序，删除逆序前面一位
            boolean removeFlag = false;
            for (int i = 0; i < num.length() - 1 ; i ++) {
                if (num.charAt(i) > num.charAt(i + 1)) {
                    removeFlag = true;
                    num = num.substring(0, i).concat(num.substring(i + 1));
                    break;
                }
            }
            if (!removeFlag) {
                num = num.substring(0, num.length() - 1);
            }
            k -- ;
        }
        return Integer.valueOf(num);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigit("12345", 1) == 1234);
        System.out.println(removeKDigit("12345", 2) == 123);

        System.out.println(removeKDigit("54321", 1) == 4321);
        System.out.println(removeKDigit("54321", 2) == 321);

        System.out.println(removeKDigit("32541", 1) == 2541);
        System.out.println(removeKDigit("32541", 2) == 241);

        System.out.println(removeKDigit("32451", 1) == 2451);
        System.out.println(removeKDigit("32451", 2) == 241);

        System.out.println(removeKDigit("1593212", 3) == 1212);

        System.out.println(removeKDigit("30200", 1) == 200);

        System.out.println(removeKDigit("10", 2) == 0);

        System.out.println(removeKDigit("541270936", 3) == 120936);
    }
}

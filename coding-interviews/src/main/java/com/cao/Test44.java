package com.cao;

import java.util.Arrays;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月14日 15:35
 */
public class Test44 {
    /**
     * 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子， 即这5张牌是不是连续的。
     * 2～10为数字本身， A为1。 J为11、Q为12、 K为13。大王小王可以看成任意数字，默认是0。
     * @param numbers
     * @return
     */
    public static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }

        // 对元素进行排序
        Arrays.sort(numbers);
        int numOf0 = 0;
        // 可能是全0的情况
        while(numOf0< numbers.length && numbers[numOf0] == 0) {
            numOf0++;
        }
        int start = numOf0;
        int next = numOf0 + 1;
        while (next < numbers.length) {
            if (numbers[next] == numbers[start]) {
                return false;
            }
            if (numbers[next] - numbers[start] > 1) {
                // 注意差了一个1
                numOf0 -= numbers[next] - numbers[start] - 1;
                if (numOf0 < 0) {
                    return false;
                }
            }
            start ++;
            next ++;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 3, 2, 5, 4};
        System.out.println(isContinuous(numbers1) == true);
        int[] numbers2 = {1, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers2) == false);
        int[] numbers3 = {0, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers3) == true);
        int[] numbers4 = {0, 3, 1, 6, 4};
        System.out.println(isContinuous(numbers4) == false);
        int[] numbers5 = {1, 3, 0, 5, 0};
        System.out.println(isContinuous(numbers5) == true);
        int[] numbers6 = {1, 3, 0, 7, 0};
        System.out.println(isContinuous(numbers6) == false);
        int[] numbers7 = {1, 0, 0, 5, 0};
        System.out.println(isContinuous(numbers7) == true);
        int[] numbers8 = {1, 0, 0, 7, 0};
        System.out.println(isContinuous(numbers8) == false);
        int[] numbers9 = {3, 0, 0, 0, 0};
        System.out.println(isContinuous(numbers9) == true);
        int[] numbers10 = {0, 0, 0, 0, 0};
        System.out.println(isContinuous(numbers10) == true);
        int[] numbers11 = {1, 0, 0, 1, 0};
        System.out.println(isContinuous(numbers11) == false);
    }
}

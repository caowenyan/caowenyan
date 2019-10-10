package com.cao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月10日 10:34
 */
public class Test28 {
    /**
     * 题目：输入一个字符串，打印出该字符事中字符的所有排列。例如输入字符串abc。
     * 则打印出由字符a、b、c 所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
     *
     * @param chars 待排序的字符数组
     */
    public static void permutation(char[] chars) {
        // 输入较验
        if (chars == null || chars.length < 1) {
            return;
        }
        // 进行排列操作
        permutation(chars, 0);
    }

    /**
     * 求字符数组的排列
     *
     * @param chars 待排列的字符串
     * @param begin 当前处理的位置
     */
    private static void permutation(char[] chars, int begin) {
        if (begin == chars.length - 1) {
            System.out.println(Arrays.toString(chars));
            // 开始少了个return，一直报错，真的粗心害死人
            return;
        }
        char temp = chars[begin];
        for (int i = begin; i < chars.length ; i ++) {
            chars[begin] = chars[i];
            chars[i] = temp;
            permutation(chars, begin + 1);
            chars[i] = chars[begin];
            chars[begin] = temp;
        }
    }

    public static void main(String[] args) {
        char[] c1 = {'a', 'b', 'c'};
        permutation(c1);
        System.out.println();
        combination(c1);

        System.out.println();
        System.out.println("====================");
        System.out.println();

        char[] c2 = {'a', 'b', 'c', 'd'};
        permutation(c2);
        System.out.println();
        combination(c2);
        System.out.println();
    }
    /**
     * 题目：输入一个字符串，打印出该字符事中字符的所有组合。例如输入字符串abc。
     * 则打印a,b,c,ab,ac,bc,abc
     *
     * @param chars 待排序的字符数组
     */
    public static void combination(char[]chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        for (int i = 0; i < chars.length; i ++) {
            List<Character> combs = new ArrayList<>();
            combination(chars, i + 1, 0, combs);
        }
    }

    private static void combination(char[] chars, int size, int begin, List<Character>addChars) {
        if (addChars.size() == size) {
            System.out.println(Arrays.toString(addChars.toArray()));
            return;
        }
        if (begin >= chars.length) {
            return;
        }
        addChars.add(chars[begin]);
        combination(chars, size, begin + 1, addChars);
        addChars.remove(addChars.size() - 1);
        combination(chars, size, begin + 1, addChars);
    }

}

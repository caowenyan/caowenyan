package com.cao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月12日 17:52
 */
public class Test35 {
    /**
     * 找到字符串中第一个只出现一次的字符。
     * @param s
     * @return
     */
    public static char firstNotRepeatingChar(String s) {
        if (s == null || s.length() < 1) {
            throw new IllegalArgumentException("Arg should not be null or empty");
        }
        char[]chars = s.toCharArray();
        // 统计每个字符的虚数量，有一个记录1，有多个记录2
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : chars) {
            if (countMap.containsKey(c)) {
                countMap.put(c, 2);
            } else {
                countMap.put(c,1);
            }
        }
        for (char c : chars) {
            if (countMap.get(c) == 1) {
                return c;
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        System.out.println(firstNotRepeatingChar("google") == 'l');
        System.out.println(firstNotRepeatingChar("aabccdbd") == '\0');
        System.out.println(firstNotRepeatingChar("abcdefg") == 'a');
        System.out.println(firstNotRepeatingChar("gfedcba") == 'g');
        System.out.println(firstNotRepeatingChar("zgfedcba") == 'z');
        System.out.println(firstNotRepeatingChar("你是谁啊你") == '是');
    }
}

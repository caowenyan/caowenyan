package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月14日 10:48
 */
public class Test42 {
    /**
     * 题目一：输入一个英文句子，翻转句子中单词的顺序，但单词内字啊的顺序不变。
     * 为简单起见，标点符号和普通字母一样处理。
     * 例如"I'm a student."-->"student. a I'm"
     *
     * @param data
     * @return
     */
    public static char[] reverseSentence(char[] data) {
        if (data == null || data.length <= 1) {
            return data;
        }
        reverse(data, 0, data.length - 1);
        int start = 0, end = 0;
        /*while (end < data.length) {
            if (data[end] == ' ') {
                reverse(data, start, end - 1);
                start = end + 1;
            }
            if (end == data.length - 1 && start < end) {
                reverse(data, start, end);
            }
            end ++;
        }*/
        while (start < data.length) {
            if (data[start] == ' ') {
                start ++ ;
                end ++;
            } else if (end == data.length || data[end] == ' ') {
                reverse(data, start, end -1);
                end ++;
                start = end;
            } else {
                end ++;
            }
        }
        return data;
    }

    /**
     * 题目二：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。
     * 例如"abcdef"左旋转2位得到"cdefab"
     * @param data
     * @param n
     * @return
     */
    public static char[] leftRotateString(char[] data, int n) {
        if (data == null || data.length == n) {
            return data;
        }
        if (n > data.length) {
            throw new RuntimeException("ds");
        }
        reverse(data, 0, data.length - 1);
        reverse(data, 0, data.length - n - 1);
        reverse(data, data.length -n, data.length - 1);
        return data;
    }

    /**
     * 将data中start到end之间的数字反转
     *
     * @param data
     * @param start
     * @param end
     */
    public static void reverse(char[] data, int start, int end) {
        if (data == null || data.length == 0 || start >= end) {
            return;
        }
        while (start < end) {
            char temp = data[start];
            data[start] = data[end];
            data[end] = temp;
            start ++ ;
            end -- ;
        }
    }
    public static void main(String[] args) {
        test01();
        test02();
    }

    private static void test01() {
        System.out.println(new String(reverseSentence("I am a student.".toCharArray())));
        System.out.println(new String(reverseSentence("Lily is a student. ".toCharArray())));
        System.out.println(new String(reverseSentence("Wonderful".toCharArray())));
        System.out.println(new String(reverseSentence("".toCharArray())));
        System.out.println(new String(reverseSentence("    ".toCharArray())));
    }

    private static void test02() {
        System.out.println(new String(leftRotateString("abcdefg".toCharArray(), 2)));
        System.out.println(new String(leftRotateString("abcdefg".toCharArray(), 1)));
        System.out.println(new String(leftRotateString("abcdefg".toCharArray(), 6)));
        System.out.println(new String(leftRotateString("abcdefg".toCharArray(), 7)));
        System.out.println(new String(leftRotateString("abcdefg".toCharArray(), 0)));
    }
}

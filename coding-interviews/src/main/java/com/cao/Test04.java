package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月17日 10:41
 */
public class Test04 {
    /**
     * 请实现一个函数，把字符串中的每个空格替换成"%20"，例如“We are happy.“，则输出”We%20are%20happy.“。
     *
     * @param string     要转换的字符数组
     * @param usedLength 已经字符数组中已经使用的长度
     * @return 转换后使用的字符长度，-1表示处理失败
     */
    public static int replaceBlank(char[] string, int usedLength) {
        if (string == null) {
            return -1;
        }
        if (string.length == 0 || usedLength==0) {
            return 0;
        }
        // 首先查找当前字符串有多少个空格，然后看看是否有足够的空间转换
        int spaceNum = 0;
        for (int i = 0 ; i < usedLength ; i++) {
            if (string[i] == ' ') {
                spaceNum ++;
            }
        }
        int finalLength = usedLength + spaceNum * 2;
        // 若是string容不下扩展后的string
        if(string.length < finalLength) {
            return -1;
        }
        // 从后往前写的索引位置
        int realIndex = finalLength - 1;
        for (int i = usedLength - 1 ; i >=0 && realIndex > i; i -- ) {
            if (string[i] == ' ') {
                string[realIndex -- ] = '0';
                string[realIndex -- ] = '2';
                string[realIndex -- ] = '%';
            } else {
                string[realIndex --] = string[i];
            }
        }
        return finalLength;
    }

    public static void main(String[] args) {
        char[] string = new char[50];
        string[0] = ' ';
        string[1] = 'e';
        string[2] = ' ';
        string[3] = ' ';
        string[4] = 'r';
        string[5] = 'e';
        string[6] = ' ';
        string[7] = ' ';
        string[8] = 'a';
        string[9] = ' ';
        string[10] = 'p';
        string[11] = ' ';

        int length = replaceBlank(string, 12);
        System.out.println(new String(string, 0, length));
    }
}

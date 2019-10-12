package com.cao;

public class Test12 {
    /**
     * 输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则打印出1、2、3 一直到最大的3位数即999。
     * @param n 数字的最大位数
     */
    public static void printOneToNthDigits2(int n) {
        if (n <= 0){
            return;
        }
        int[] nums = new int[n];
        printOneToNthDigits(0, nums);
    }

    /**
     * 输入数字n，按顺序打印出从1最大的n位十进制数。
     *
     * @param n   当前处理的是第个元素，从0开始计数
     * @param arr 存放结果的数组
     */
    public static void printOneToNthDigits(int n, int[] arr) {
        if (n >= arr.length) {
            printInts(arr);
        } else {
            for (int i = 0; i <= 9 ; i ++) {
                arr[n] = i;
                printOneToNthDigits(n + 1, arr);
            }
        }
    }

    /**
     * 输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则打印出1、2、3 一直到最大的3位数即999。
     * @param n 数字的最大位数
     */
    public static void printOneToNthDigits(int n) {
        if (n <= 0){
            return;
        }
        // 若是这块使用int更方便
        char[] chars = new char[n + 1];
        // 初始化数组，开始漏掉这部分，导致出现未知字符，毕竟初始化后不是'0'
        for (int i = 0; i <= n; i ++ ) {
            chars[i] = '0';
        }
        int sum = 0;
        while(isAdd(chars, n + 1)) {
            printChars(chars);
            sum ++ ;
        }
        System.out.println("打印数字数量：" + sum);
    }

    /**
     * 是否可以自增，若是可以，则自增，否则返回false
     */
    private static boolean isAdd(char[] chars, int n) {
        int sum = 0;
        for (int i = n-1 ; i >= 0; i-- ){
            if (i == 0) {
                return false;
            }
            if (i == n -1) {
                sum += 1;
            }
            sum += (chars[i] - '0');
            if (sum < 10) {
                chars[i] = (char)('0' + sum);
                break;
            } else {
                chars[i] = '0';
                // 加上进位的1
                sum = sum - 10 + 1;
            }
        }
        return true;
    }

    private static void printChars(char[]chars) {
        boolean start = false;
        for (char c : chars) {
            if (!start && c == '0'){
                continue;
            }
            if (!start && c != '0') {
                start = true;
            }
            System.out.print(c);
        }
        System.out.println();
    }

    private static void printInts(int[]chars) {
        // 这个主要是为了看是否真的有值，因为存在0。。。0的情况，不需要打印
        boolean start = false;
        for (int c : chars) {
            if (!start && c != 0) {
                start = true;
            }
            if (start) {
                System.out.print(c);
            }
        }
        if (start) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printOneToNthDigits2(4);
    }
}

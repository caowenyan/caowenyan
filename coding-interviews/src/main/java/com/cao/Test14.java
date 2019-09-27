package com.cao;

import java.util.function.Predicate;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月27日 10:00
 */
public class Test14 {
    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
     * 这个方法需要比较的次数比较多
     * @param arr 输入的数组
     */
    public static void reorderOddEven1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 奇数索引
        int oddIndex = 0;
        for (int i = 0 ;i < arr.length; i ++ ) {
            int num = arr[i];
            // 若是奇数，需要交换第一个偶数和当前奇数的位置
            if ((num & 1) == 1) {
                swap(arr, oddIndex, i);
                oddIndex ++;
            }
        }
    }

    /**
     * 书中的代码实现
     */
    public static void reorderOddEven2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // 若是不加条件会出现全奇数报null异常
            while (start < end && (arr[start]&1)==1) {
                start ++;
            }
            // 若是不加条件会出现全偶数报null异常
            while (start < end && (arr[end]&1) == 0) {
                end -- ;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
    }


    /**
     * 扩展实现
     */
    public static void reorder(int[] arr, Predicate<Integer> predicate) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // 若是不加条件会出现报null异常
            while (start < end && predicate.test(arr[start])) {
                start ++;
            }
            while (start < end && !predicate.test(arr[end])) {
                end -- ;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
    }
    /**
     * 交换两个索引元素的元素
     */
    private static void swap(int[]arr , int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 输出数组的信息
     *
     * @param arr 待输出数组
     */
    public static void printArray(int[] arr) {
        if (arr != null && arr.length > 0) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
//        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//        reorderOddEven1(array);
//        printArray(array);
//
//        int[] array1 = {1, 3, 5, 7, 9};
//        reorderOddEven1(array1);
//        printArray(array1);
//
//        int[] array2 = {0, 2, 4, 6, 8};
//        reorderOddEven1(array2);
//        printArray(array2);
        Predicate<Integer> predicate = (p)->{return (p&1)==1;};
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        reorder(array, predicate);
        printArray(array);

        int[] array1 = {1, 3, 5, 7, 9};
        reorder(array1, predicate);
        printArray(array1);

        int[] array2 = {0, 2, 4, 6, 8};
        reorder(array2, predicate);
        printArray(array2);
    }
}

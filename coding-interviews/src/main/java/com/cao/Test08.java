package com.cao;

import org.junit.Assert;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 递增数组的旋转
 * @date 2019年09月17日 17:27
 */
public class Test08 {
    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。
     * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3, 4, 5, 1, 2｝为｛l ,2, 3, 4, 5}的一个旋转，该数组的最小值为
     *
     * @param numbers 旋转数组
     * @return 数组的最小值
     */
    public static int min(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("Invalid input.");
        }
        int start = 0;
        int end = numbers.length - 1;
        int mid = 0;
        while (end - start > 1) {
            mid = (start + end) >> 1;
            // 这个是特例，例如 1..1.0...1..1，这个就不好判断中间是不是有其他的元素，只好遍历了。
            // 这个是个盲区，需要特别注意
            if (numbers[end] == numbers[start] && numbers[start] == numbers[mid]) {
                return minInorder(numbers, start, end);
            }
            if (numbers[end] >= numbers[mid]) {
                end = mid;
            } else if (numbers[end] < numbers[mid]) {
                start = mid;
            } else if (numbers[start] >= numbers[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        // 处理全都是递增子序列的情况
        return (numbers[start] > numbers[end]? numbers[end]:numbers[start]);
    }

    /**
     * 找数组中的最小值
     *
     * @param numbers 数组
     * @param start   数组的起始位置
     * @param end     数组的结束位置
     * @return 找到的最小的数
     */
    public static int minInorder(int[] numbers, int start, int end) {
        int result = numbers[start];
        for (int i = start + 1; i <= end; i++) {
            if (result > numbers[i]) {
                result = numbers[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 典型输入，单调升序的数组的一个旋转
        int[] array1 = {3, 4, 5, 1, 2};
        Assert.assertEquals(min(array1), 1);

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        Assert.assertEquals(min(array2), 1);

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        Assert.assertEquals(min(array3), 1);

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        Assert.assertEquals(min(array4), 0);

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array5 = {1, 1, 0, 1, 1};
        Assert.assertEquals(min(array5), 0);

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array6 = {1, 1, 1, 0, 1};
        Assert.assertEquals(min(array6), 0);

        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int[] array7 = {1, 2, 3, 4, 5};
        Assert.assertEquals(min(array7), 1);

        // 数组中只有一个数字
        int[] array8 = {2};
        Assert.assertEquals(min(array8), 2);

        // 数组中数字都相同
        int[] array9 = {1, 1, 1, 1, 1, 1, 1};
        Assert.assertEquals(min(array9), 1);

        // 输入NULL
        min(null);
    }
}

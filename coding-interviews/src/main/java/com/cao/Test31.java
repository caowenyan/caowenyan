package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月11日 11:21
 */
public class Test31 {
    /**
     * 输入一个整型数组，数组里有正数也有负数。数组中一个或连
     * 续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
     * 此题很难形成代码，必须仔细斟酌，改了好几次才可以正常运行
     * @param arr 输入数组
     * @return 最大的连续子数组和
     */
    public static int findGreatestSumOfSubArray(int[] arr) {
        // 参数校验
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("Array must contain an element");
        }
        // 最大的值，每次和这个值比较
        int max = arr[0];
        // 前面连续的最大值，可以是当前元素，也可以是当前元素+前面最大的连续元素
        int lastSum = arr[0];
        for (int i = 1 ; i < arr.length ; i ++) {
            if (lastSum < 0){
                lastSum = arr[i];
            } else {
                lastSum += arr[i];
            }
            if (lastSum > max) {
                max = lastSum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] data2 = {-2, -8, -1, -5, -9};
        int[] data3 = {2, 8, 1, 5, 9};

        System.out.println(18 == findGreatestSumOfSubArray(data));
        System.out.println(-1 == findGreatestSumOfSubArray(data2));
        System.out.println(25 == findGreatestSumOfSubArray(data3));
    }
}

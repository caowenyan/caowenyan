package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月13日 14:45
 */
public class Test40 {
    /**
     * 一个整数数组中除了两个数字之外，其他的两个数字都出现了两次。要求时间复杂度是O(n)，空间复杂度O(1)
     * 一个数字的话通过异或可以找到，异或是不是不一样。
     * @param data
     * @return
     */
    public static int[] findNumbersAppearanceOnce(int[] data) {
        int[] result = new int[2];
        // 找到两个数异或的结果，再通过分组将不同的两个数分到不同的组内
        int temp = 0;
        for (int obj : data) {
            temp ^= obj;
        }
        // 从后向前找到第一个索引位置不为1，按照此索引位将数据分为两组。这个地方没必要真的分成两组，理论上分为两组就好了
        int lastIndexOf1 = getLastIndexOf1(temp);

        for (int obj : data) {
            if (((obj >> lastIndexOf1) & 1) == 0) {
                result[0] ^= obj;
            } else {
                result[1] ^= obj;
            }
        }
        return result;
    }

    private static void swap(int[] data, int start, int end) {
        int temp = data[start];
        data[start] = data[end];
        data[end] = temp;
    }

    public static int getLastIndexOf1(int data) {
        int index = 0;
        while (((data >> index) & 1) == 0) {
            index ++;
        }
        return index;
    }
    public static void main(String[] args) {
        int[] data1 = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] result1 = findNumbersAppearanceOnce(data1);
        System.out.println(result1[0] + " " + result1[1]);

        int[] data2 = {4, 6};
        int[] result2 = findNumbersAppearanceOnce(data2);
        System.out.println(result2[0] + " " + result2[1]);

        int[] data3 = {4, 6, 1, 1, 1, 1};
        int[] result3 = findNumbersAppearanceOnce(data3);
        System.out.println(result3[0] + " " + result3[1]);
    }
}

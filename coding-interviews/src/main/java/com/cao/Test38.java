package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月13日 13:26
 */
public class Test38 {

    /**
     * 题目：统计一个数字：在排序数组中出现的次数
     * 例如{1, 2, 3, 3, 3, 3, 4, 5}中3出现4次。
     * @param data
     * @param k
     * @return
     */
    public static int getNumberOfK(int[] data, int k) {
        if (data == null || data.length == 0) {
            return 0;
        }
        if (data[0] > k || data[data.length - 1] < k) {
            return 0;
        }
        return getEndIndexOfK(data, k, 0, data.length - 1)  - getStartIndexOfK(data, k, 0, data.length - 1) + 1;
    }

    private static int getEndIndexOfK(int[] data, int k, int start, int end) {
        if (data[end] == k) {
            return end;
        }
        if (data[end] < k || start == end) {
            return -1;
        }
        while (start <= end && data[end] > k) {
            int middle = (start + end) >> 1;
            if (data[middle] < k) {
                start = middle + 1;
            } else if (data[middle] > k) {
                end = middle - 1;
            } else {
                end = getEndIndexOfK(data, k, middle, end);
            }
        }
        return end;
    }

    private static int getStartIndexOfK(int[] data, int k, int start, int end) {
        if (data[start] == k) {
            return start;
        }
        if (data[start] > k || start == end) {
            return -1;
        }
        // 开始落了data[start]<k，导致死循环了,这个主要处理start=k的情况
        while (start <= end && data[start] < k) {
            int middle = (start + end) >> 1;
            if (data[middle] < k) {
                start = middle + 1;
            } else if (data[middle] > k) {
                end = middle - 1;
            } else {
                start =  getStartIndexOfK(data, k, start, middle);;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        // 查找的数字出现在数组的中间
        int[] data1 = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data1, 3) == 4);

        // 查找的数组出现在数组的开头
        int[] data2 = {3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data2, 3) == 4);

        // 查找的数组出现在数组的结尾
        int[] data3 = {1, 2, 3, 3, 3, 3};
        System.out.println(getNumberOfK(data3, 3) == 4);

        // 查找的数字不存在
        int[] data4 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data4, 2) == 0);

        // 查找的数字比第一个数字还小，不存在
        int[] data5 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data5, 0) == 0);

        // 查找的数字比最后一个数字还大，不存在
        int[] data6 = {1, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(data6, 0) == 0);

        // 数组中的数字从头到尾都是查找的数字
        int[] data7 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(data7, 3) == 4);

        // 数组中的数字从头到尾只有一个重复的数字，不是查找的数字
        int[] data8 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(data8, 4) == 0);

        // 数组中只有一个数字，是查找的数字
        int[] data9 = {3};
        System.out.println(getNumberOfK(data9, 3) == 1);

        // 数组中只有一个数字，不是查找的数字
        int[] data10 = {3};
        System.out.println(getNumberOfK(data10, 4) == 0);
    }
}

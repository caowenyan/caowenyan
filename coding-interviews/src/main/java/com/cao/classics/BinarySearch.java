package com.cao.classics;

import org.junit.Assert;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 二分查找
 * @date 2019年09月17日 14:31
 */
public class BinarySearch {

    /**
     * 循环二分查找
     */
    public static int searchIndex1(int[]arr, int key) {
        if (arr == null) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        int mid = (start + end) >> 1;
        while (start <= end) {
            int value = arr[mid];
            if (value == key) {
                return mid;
            } else if (value > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) >> 1;
        }
        return -1;
    }
    /**
     * 二分查找，返回查找数据的索引，递归
     */
    public static int searchIndex(int[]arr, int key) {
        if (arr == null) {
            return -1;
        }
        return searchIndex(arr, key, 0, arr.length - 1);
    }

    private static int searchIndex(int[] arr, int key, int start, int end) {
        if(start > end) {
            return -1;
        }
        int mid = (start + end) >> 1;
        int midVal = arr[mid];
        if (midVal == key) {
            return mid;
        } else if (midVal > key) {
            return searchIndex(arr, key, start, mid - 1);
        } else {
            return searchIndex(arr, key, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,8,9,11,23};
        Assert.assertNotEquals(searchIndex(arr, 1), -1);
        Assert.assertNotEquals(searchIndex(arr, 23), -1);
        Assert.assertNotEquals(searchIndex(arr, 9), -1);
        Assert.assertNotEquals(searchIndex(arr, 11), -1);
        Assert.assertEquals(searchIndex(arr, 0), -1);
        Assert.assertEquals(searchIndex(arr, 10), -1);
        Assert.assertEquals(searchIndex(arr, 29), -1);
        Assert.assertEquals(searchIndex(null, 29), -1);
    }
}

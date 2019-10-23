package com.cao.classics;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 归并排序
 * 时间复杂度：O(nlogn),空间复杂度O(n)
 * @date 2019年09月17日 17:13
 */
public class MergeSort {
    public static void main(String[] args) {
//        int arr[] = { 4, 1, 3, 2, 7, 6, 8};
        int arr[] = { 1, 2, 3,  4, 6, 7, 8 };
//        int arr[] = { 8, 7, 6, 4, 3, 2, 1 };
        mergeSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void mergeSort(int[]arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[]arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    /**
     * 从左向右将两个已排序的数组合并
     * @param arr   原数组
     * @param start 开始位置
     * @param mid   中间位置
     * @param end   结束位置
     */
    public static void merge(int[]arr, int start, int mid, int end) {
        int[]copy = new int[end - start + 1];
        // 已经排过序的索引
        int i = 0;
        // 表示两个指针
        int p1 = start;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= end) {
            while (p1 <= mid && arr[p1]<arr[p2]) {
                copy[i++] = arr[p1++];
            }
            while (p2 <= end && arr[p1]>=arr[p2]) {
                copy[i++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            copy[i++] = arr[p1++];
        }
        while (p2 <= end) {
            copy[i++] = arr[p2++];
        }
        System.arraycopy(copy, 0, arr, start, copy.length);
    }
}

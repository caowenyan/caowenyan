package com.cao.classics;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 快速排序，思路，分区合并
 * @date 2019年09月17日 15:14
 */
public class QuickSort {
    public static void main(String[] args) {
        int arr[] = { 4, 1, 3, 2, 7, 6, 8};
//        int arr[] = { 1, 2, 3, 4, 6, 7, 8 };
//        int arr[] = { 8, 7, 6, 4, 3, 2, 1 };
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int arr[]) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序，使得整数数组 arr 的 [L, R] 部分有序
     */
    private static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            int p = partition(arr, L, R);
            quickSort(arr, L, p - 1);
            quickSort(arr, p + 1, R);
        }
    }

    /**
     * 分区的过程
     * 大于 arr[L] 的元素位于基准元素的右边，但这部分数据不一定有序
     * 小于 arr[L] 的元素位于基准元素的左边，但这部分数据不一定有序
     * 返回基准元素最终的位置
     */
    public static int partition(int[] arr, int L, int R) {
        // 选取第一个元素作为基准元素
        int pivot = arr[L];
        // 开始这个值设置为L+1，导致一直有元素没有被排序，以此谨记（因为若是这样会导致最后一个元素无法处理，导致遗漏）
        int left = L;
        int right = R;
        while (right > left) {
            while (right > left && arr[right] >= pivot) {
                right --;
            }
            while (left < right && arr[left] <= pivot) {
                left ++ ;
            }
            if (right > left) {
                swap(arr, left, right);
            }
        }
        // 注意此处不能同left，因为left是自增的，有可能大于right，导致越界
        swap(arr, L, right);
        return right;
    }

    /*
     * 交换数组 arr 中下标为 i 和下标为 j 位置的元素
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

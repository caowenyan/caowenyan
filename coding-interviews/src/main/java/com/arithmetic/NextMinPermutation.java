package com.arithmetic;

import java.util.Arrays;

/**
 * 求下一个最小的全排列组合，
 * 例如12345的下一个全排列组合是12354
 *     12543的下一个全排列组合是13245
 * 通过全排序肯定太慢，所以通过比较每个位的特征得到，若是某个位置的数字比他的前一位大，就可以通过转化得到
 *
 * 此算法叫做字典序排列
 */
public class NextMinPermutation {
    public static int[] findNextMinPermutation(int arr[]) {
        if (arr == null || arr.length == 1) {
            return null;
        }
        // 从后向前找到出现逆序区域（递增）出现的第一个异常
        int index = findTransferPoint(arr);
        if (index == 0) {
            return null;
        }
        // 把逆序位置的前一位和后面最小的元素交换顺序
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        exchangeHead(arrCopy, index);
        // 把原来的逆序区域的数据修改为顺序的
        reverse(arrCopy, index);

        return arrCopy;
    }

    private static int findTransferPoint(int[] arr) {
        for (int i = arr.length - 1 ; i > 0; i -- ) {
            if (arr[i] > arr[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 将逆序异常的第一位与逆序区域的（比index-1的值大一点）的最小值交互
     * 毕竟是逆序，所以最小的肯定最后一位
     */
    private static void exchangeHead(int[] arr, int index) {
        for (int i = arr.length - 1 ; i > 0; i --) {
            if (arr[i] > arr[index - 1]) {
                swap(arr, index - 1, i);
                // 交换一个就ok，记得退出，找了半天bug
                break;
            }
        }
    }

    /**
     * 交换后index区域是全逆序的，所以需要反转即可
     */
    private static void reverse(int[] arr, int index) {
        for (int i = 0; i < (arr.length - index) >> 1; i++) {
            swap(arr, index + i, arr.length - 1 - i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[]arr = {1,2,3,4,5,6};
        // 打印arr之后的n个全排列整数
        for (int i = 0 ; i < 720 ; i ++) {
            arr = findNextMinPermutation(arr);
            System.out.println(Arrays.toString(arr));
        }
    }
}

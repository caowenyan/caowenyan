package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月10日 12:26
 */
public class Test29 {

    /**
     * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
     * 书中的第二种方法，若是不看这个解法，吾恐怕一辈子都想不出来，太巧
     * @param numbers 输入数组
     * @return 找到的数字
     */
    public static int moreThanHalfNum1(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("请输入合适的参数");
        }
        // 当前数组的某个元素
        int result = numbers[0];
        // 当前数字的个数
        int num = 1;
        for (int i = 1; i < numbers.length; i ++) {
            if (num == 0) {
                result = numbers[i];
                num = 1;
            } else if (result == numbers[i]) {
                num ++;
            } else {
                num -- ;
            }
        }
        // 校验当前数字是否符合条件
        if (checkVaild(numbers, result)) {
            return result;
        }
        throw new RuntimeException("请输入合适的参数");
    }

    /**
     * 有快速排序改编而来的，主要是取决于数组的特性
     */
    public static int moreThanHalfNum(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("请输入合适的参数");
        }
        int start = 0;
        int end = numbers.length - 1;
        int mid = (numbers.length)>>1;
        int index = partition(numbers, start, end);
        while (index != mid) {
            if (index > mid) {
                // 这个地方还和快排的思想是一样的，误区直接写index，导致运行不出来，蠢
                end = index - 1;
            } else  {
                start = index + 1;
            }
            index = partition(numbers, start, end);
        }
        // 校验当前数字是否符合条件
        if (checkVaild(numbers, numbers[index])) {
            return numbers[index];
        }
        throw new RuntimeException("请输入合适的参数");
    }

    public static int partition(int[] arr, int L, int R) {
        int start = L;
        int end = R;
        int pivot = arr[start];
        while (start < end) {
            while (arr[end] > pivot) {
                end --;
            }
            while (arr[start] <= pivot) {
                start ++;
            }
            if (start < end) {
                swap(arr, start, end);
            }
        }
        swap(arr, L, end);
        return end;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean checkVaild(int[] numbers, int result) {
        int count = 0;
        for (int number : numbers) {
            if (result == number) {
                count++;
            }
        }
        // 如果出现次数大于数组的一半就返回对应的值
        if (count > numbers.length / 2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 存在出现次数超过数组长度一半的数字
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThanHalfNum(numbers));

        // 出现次数超过数组长度一半的数字都出现在数组的前半部分
        int numbers2[] = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalfNum(numbers2));

        // 出现次数超过数组长度一半的数字都出现在数组的后半部分
        int numbers3[] = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        System.out.println(moreThanHalfNum(numbers3));

        // 只有一个数
        int numbers4[] = {1};
        System.out.println(moreThanHalfNum(numbers4));

        // 输入空指针
        moreThanHalfNum(null);
        // 不存在出现次数超过数组长度一半的数字
        int numbers5[] = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        moreThanHalfNum(numbers5);
    }
}

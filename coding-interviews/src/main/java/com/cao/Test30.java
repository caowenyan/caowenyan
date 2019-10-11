package com.cao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月10日 16:55
 */
public class Test30 {

    /**
     * 题目： 输入n个整数，找出其中最小的k个数。
     * 【第一种解法】利用快速排序的思想，改变了数组中原有元素的位置
     * @param input  输入数组
     * @param output 输出数组
     */
    public static void getLeastNumbers(int[] input, int[] output) {
        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }
        int start = 0;
        int end = input.length - 1;
        int finalNum = output.length - 1;
        int index = partition(input, start, end);
        while (index != finalNum) {
            if (index > finalNum) {
                end = index - 1;
            } else  {
                start = index + 1;
            }
            index = partition(input, start, end);
        }
        System.arraycopy(input, 0, output, 0, output.length);
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
    /**
     * 大顶堆
     *
     * @param <T> 参数化类型
     */
    private final static class MaxHeap<T extends Comparable<T>> {
        // 堆中元素存放的集合
        private List<T> items;
        // 用于读取计数
        private int cursor;

        /**
         * 构造一个椎，始大小是32
         */
        public MaxHeap() {
            this(32);
        }

        /**
         * 造诣一个指定初始大小的堆
         *
         * @param size 初始大小
         */
        public MaxHeap(int size) {
            items = new ArrayList<>(size);
        }

        /**
         * 向上调整堆
         *
         * @param index 被上移元素的起始位置
         */
        public void siftUp(int index) {
            T temp = items.get(index);
            while (index > 0) {
                int parent = (index - 1) / 2;
                // 若是父元素大于子元素，符合要求，退出，否则需要继续上移
                if (items.get(parent).compareTo(temp) > 0) {
                    break;
                }
                items.set(index, items.get(parent));
                index = parent;
            }
            items.set(index, temp);
        }

        /**
         * 向下调整堆
         *
         * @param index 被下移的元素的起始位置
         */
        public void siftDown(int index) {
            T temp = items.get(index);
            int son = index * 2 + 1;
            while (son < items.size()) {
                T maxValue = items.get(son);
                if (son + 1 < items.size() && items.get(son + 1).compareTo(maxValue) > 0) {
                    son ++;
                    maxValue = items.get(son);
                }
                // 若是当前值大于左右孩子，满足最大堆，退出
                if (maxValue.compareTo(temp) < 0) {
                    break;
                }
                items.set(index, maxValue);
                index = son;
                son = index * 2 + 1;
            }
            items.set(index, temp);
        }

        /**
         * 向堆中添加一个元素
         *
         * @param item 等待添加的元素
         */
        public void add(T item) {
            items.add(item);
            siftUp(items.size() - 1);
        }

        /**
         * 删除堆顶元素
         *
         * @return 堆顶部的元素
         */
        public T deleteTop() {
            if (items.size() == 0) {
                throw new RuntimeException("堆没有元素,无法删除元素");
            }
            // 若是只有一个元素，不需要移动，而且下面的items.set(0,value)会报错，因为set方法只能是设置已经设置过的元素而不能是新的元素
            if (items.size() == 1) {
                return items.remove(0);
            }
            // 删除堆顶元素，将最后一个元素替换成堆顶，进行下沉
            T topValue = items.get(0);
            items.set(0, items.remove(items.size() - 1));
            siftDown(0);
            return topValue;
        }

        /**
         * 获取堆中的第一个元素
         *
         * @return 堆中的第一个元素
         */
        public T first() {
            if (items.size() == 0) {
                throw new RuntimeException("堆没有元素，无法获取到第一个元素");
            }
            return items.get(0);
        }

        public boolean hasNext() {
            if (items.size() == 0) {
                return false;
            }
            if (cursor < items.size()) {
                return true;
            }
            return false;
        }

        public T next() {
            if (cursor < items.size()) {
                return items.get(cursor ++ );
            }
            throw new RuntimeException("堆没有元素，无法获取到第一个元素");
        }
        /**
         * 判断堆是否为空
         *
         * @return true是，false否
         */
        public boolean isEmpty() {
            return items.isEmpty();
        }

        /**
         * 获取堆的大小
         *
         * @return 堆的大小
         */
        public int size() {
            return items.size();
        }

        /**
         * 清空堆
         */
        public void clear() {
            items.clear();
        }

        @Override
        public String toString() {
            return items.toString();
        }
    }
    /**
     * 题目： 输入n个整数，找出其中最小的k个数。
     * 【第二种解法】
     * @param input  输入数组
     * @param output 输出数组
     */
    public static void getLeastNumbers2(int[] input, int[] output) {
        if (input == null || output == null || output.length <= 0 || input.length < output.length) {
            throw new IllegalArgumentException("Invalid args");
        }

        MaxHeap<Integer> maxHeap = new MaxHeap<>(output.length);
        for (int i : input) {
            if (maxHeap.size() < output.length) {
                maxHeap.add(i);
            } else {
                int max = maxHeap.first();
                if (max > i) {
                    maxHeap.deleteTop();
                    maxHeap.add(i);
                }
            }
        }

        for (int i = 0; maxHeap.hasNext(); i++) {
            output[i] = maxHeap.next();
        }
    }

    public static void main(String[] args) {
        System.out.println("第一种解法：");
        test1();
        System.out.println();
        System.out.println("第二种解法：");
        test2();
    }

    private static void test1() {
        int[] data = {4, 5, 1, 6, 2, 7, 3, 8};

        int[] output = new int[4];
        getLeastNumbers(data, output);
        for (int i : output) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] output2 = new int[8];
        getLeastNumbers(data, output2);
        for (int i : output2) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] output3 = new int[1];
        getLeastNumbers(data, output3);
        for (int i : output3) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] data2 = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] output4 = new int[2];
        getLeastNumbers(data2, output4);
        for (int i : output4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void test2() {
        int[] data = {4, 5, 1, 6, 2, 7, 3, 8};

        int[] output = new int[4];
        getLeastNumbers2(data, output);
        for (int i : output) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] output2 = new int[8];
        getLeastNumbers2(data, output2);
        for (int i : output2) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] output3 = new int[1];
        getLeastNumbers2(data, output3);
        for (int i : output3) {
            System.out.print(i + " ");
        }
        System.out.println();


        int[] data2 = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] output4 = new int[2];
        getLeastNumbers2(data2, output4);
        for (int i : output4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

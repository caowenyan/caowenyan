package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 归并排序的一个应用
 * @date 2019年10月13日 11:17
 */
public class Test36 {
    /**
     * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成的逆序对。
     * 输入一个数组，求这个数组中的逆序对的总数
     7 5 6 4 5
     7 5     6 4 5
     7 5     6      4 5
     5 7     6      4 5     逆序对为1，(7,5)
     5 7     4 5 6          逆序对为3，增加(6,4),(6,5)
     4 5 5 6 7              逆序对为7，增加(7,6),(7,5),(7,4),(5,4)
     * @param data
     * @return
     */
    public static int inversePairs(int[] data) {
        if (data == null || data.length < 1) {
            throw new IllegalArgumentException("Array arg should contain at least a value");
        }

        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);

        return inversePairsCore(data, copy, 0, data.length - 1);
    }

    /**
     *
     * @param data  指的是元素的数组，新生成的数组最后需要覆盖原数组
     * @param copy  新生成的数组
     */
    private static int inversePairsCore(int[] data, int[] copy, int start, int end) {
        if (start == end) {
            data[start] = copy[start];
            return 0;
        }
        int middle = (start + end) >> 1;
        int sum = inversePairsCore(copy, data, start, middle);
        sum += inversePairsCore(copy, data, middle + 1, end);

        // 这个值得是需要插入copy中的数据，形成在[start,end]区间有序的数据
        int insertIndex = end;
        // p1指的是左半端已经排序号的，p2指的是后半段已经排序号的
        int p1 = middle, p2 = end;
        while (p1 >= start && p2 > middle) {
            if (data[p2] >= data[p1]) {
                copy[insertIndex --] = data[p2];
                p2 -- ;
            } else {
                copy[insertIndex --] = data[p1];
                p1--;
                sum += p2 - middle;
            }
        }
        while (p1 >= start) {
            copy[insertIndex --] = data[p1];
            p1 --;
        }
        while (p2 > middle) {
            copy[insertIndex --] = data[p2];
            p2 --;
        }

        // 上一步已经修改过了data的值，需要重新复制到copy
        // 注意这里开始用引用，导致一直出问题，后来才知道copy，data同时改了，无法比较了
        System.arraycopy(copy, 0, data, 0, copy.length);
        return sum;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 7, 6, 5};
        System.out.println(inversePairs(data) == 3);
        int[] data2 = {6, 5, 4, 3, 2, 1};
        System.out.println(inversePairs(data2) == 15);
        int[] data3 = {1, 2, 3, 4, 5, 6};
        System.out.println(inversePairs(data3) == 0);
        int[] data4 = {1};
        System.out.println(inversePairs(data4) == 0);
        int[] data5 = {1, 2};
        System.out.println(inversePairs(data5) == 0);
        int[] data6 = {2, 1};
        System.out.println(inversePairs(data6) == 1);
        int[] data7 = {1, 2, 1, 2, 1};
        System.out.println(inversePairs(data7) == 3);
    }
}

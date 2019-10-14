package com.cao;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月14日 09:47
 */
public class Test41 {
    /**
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得得它们的和正好是s。
     * 如果有多对数字的和等于s，输出任意一对即可。
     *
     * @param data
     * @param sum
     * @return
     */
    public static List<Integer> findNumbersWithSum(int[] data, int sum) {
        if (data == null || data.length < 2) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0, end = data.length - 1;
        while (start < end) {
            int realSum = data[start] + data[end];
            if (realSum == sum) {
                result.add(data[start]);
                result.add(data[end]);
                return result;
            }
            if (realSum > sum) {
                end --;
            } else {
                start ++;
            }
        }
        return null;
    }
    /**
     * 题目二：输入一个正数s，打印出所有和为s 的连续正数序列（至少两个数）。
     * @param sum
     * @return
     */
    public static List<List<Integer>> findContinuousSequence(int sum) {
        if (sum <= 1) {
            return null;
        }
        // 临时链表中此时的最小,最大值
        int small = 1, big = 2, middle = (sum + 1 ) >> 1;
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> temp = new LinkedList<>();
        // 提前加进去两个
        temp.addLast(1);
        temp.addLast(2);
        long realSum = small + big;

        // 注意这里的条件temp.size()>=2,因为小于2个数就表明没有数据可用了
        // 删除了最小的，肯定大于期望值，再加期望值也无用
        // 开始这么写导致下面的条件都变得复杂了，所以改成了这种方式
        // while (big <= sum && temp.size() >= 2) {
        while (big <= middle) {
            if (realSum == sum) {
                result.add(new ArrayList<>(temp));
            }
            if (realSum >= sum) {
                // 若是真实结果大于等于期望值，需要删除最小的数字（加上等号是因为多个元素，删除一个其他的可以继续成立）
                temp.removeFirst();
                realSum -= small++;
            } else {
                // 真实结果小于期望值，继续添加元素
                temp.addLast(++big);
                realSum += big;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        test01();
        System.out.println("---------------");
        test02();
    }

    private static void test01() {
        int[] data1 = {1, 2, 4, 7, 11, 15};
        System.out.println(findNumbersWithSum(data1, 15));

        int[] data2 = {1, 2, 4, 7, 11, 16};
        System.out.println(findNumbersWithSum(data2, 17));

        int[] data3 = {1, 2, 4, 7, 11, 16};
        System.out.println(findNumbersWithSum(data3, 10));
    }

    public static void test02(){
        System.out.println(findContinuousSequence(1));
        System.out.println(findContinuousSequence(3));
        System.out.println(findContinuousSequence(4));
        System.out.println(findContinuousSequence(9));
        System.out.println(findContinuousSequence(15));
        System.out.println(findContinuousSequence(100));
    }
}

package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月09日 10:52
 */
public class Test24 {
    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则返回true。否则返回false。假设输入的数组的任意两个数字都互不相同。
     *
     * @param sequence 某二叉搜索树的后序遍历的结果
     * @return true：该数组是某二叉搜索树的后序遍历的结果。false：不是
     */
    public static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0 || sequence.length == 1) {
            return true;
        }
        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    private static boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        if (end <= start) {
            return true;
        }
        int midValue = sequence[end];
        // 特别注意他是属于右子树第一个节点
        int split = start;
        while (sequence[split] < midValue && split <= end) {
            split ++;
        }
        for (int i = split; i <= end; i ++) {
            if (sequence[i] < midValue) {
                return false;
            }
        }
        boolean flag = true;
        // 开始此条件写错了，导致一直死循环，注意split是右边的最小值，因为第一个数可能也是右边的，每次自增的都是右边的
        if (split > start) {
            flag = verifySequenceOfBST(sequence , start, split - 1);
        }
        if (flag && end > split) {
            flag = verifySequenceOfBST(sequence, split, end - 1);
        }
        return flag;
    }

    public static void main(String[] args) {
        //           10
        //         /   \
        //        6     14
        //       /\     /\
        //      4  8  12  16
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + verifySequenceOfBST(data));

        //           5
        //          / \
        //         4   7
        //            /
        //           6
        int[] data2 = {4, 6, 7, 5};
        System.out.println("true: " + verifySequenceOfBST(data2));

        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        int[] data3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + verifySequenceOfBST(data3));

        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        int[] data4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + verifySequenceOfBST(data4));

        // 树中只有1个结点
        int[] data5 = {5};
        System.out.println("true: " + verifySequenceOfBST(data5));

        int[] data6 = {7, 4, 6, 5};
        System.out.println("false: " + verifySequenceOfBST(data6));

        int[] data7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + verifySequenceOfBST(data7));
    }
}

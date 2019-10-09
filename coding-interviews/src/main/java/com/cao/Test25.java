package com.cao;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月09日 12:03
 */
public class Test25 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     * 输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
     * 从树的根结点开始往下一直到叶销点所经过的结点形成一条路径。
     *
     * @param root        树的根结点
     * @param expectedSum 要求的路径和
     */
    public static void findPath(BinaryTreeNode root, int expectedSum) {
        findPath(root, expectedSum, 0, new ArrayList<>());
    }

    private static void findPath(BinaryTreeNode root, int expectedSum, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.value);
        sum += root.value;
        // 开始一直在sum>=expectedSum清空list，而在相反条件的时候不知道如何清理，还是理解的有问题
        if (sum == expectedSum) {
            //满足要求，打印路径
            for (Integer obj : list) {
                System.out.print(obj + " ");
            }
            System.out.println();
        } else if (sum < expectedSum) {
            findPath(root.left, expectedSum, sum, list);
            findPath(root.right, expectedSum, sum, list);
        }
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 10;
        root.left = new BinaryTreeNode();
        root.left.value = 5;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 4;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 12;

        // 有两条路径上的结点和为22
        System.out.println("findPath(root, 22);");
        findPath(root, 22);

        // 没有路径上的结点和为15
        System.out.println("findPath(root, 15);");
        findPath(root, 15);

        // 有一条路径上的结点和为19
        System.out.println("findPath(root, 19);");
        findPath(root, 19);

        System.out.println();

        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 5;
        root2.left = new BinaryTreeNode();
        root2.left.value = 4;
        root2.left.left = new BinaryTreeNode();
        root2.left.left.value = 3;
        root2.left.left.left = new BinaryTreeNode();
        root2.left.left.left.value = 2;
        root2.left.left.left.left = new BinaryTreeNode();
        root2.left.left.left.left.value = 1;

        // 有一条路径上面的结点和为15
        System.out.println("findPath(root2, 15);");
        findPath(root2, 15);

        // 没有路径上面的结点和为16
        System.out.println("findPath(root2, 16);");
        findPath(root2, 16);

        System.out.println();

        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        BinaryTreeNode root3 = new BinaryTreeNode();
        root3.value = 1;
        root3.right = new BinaryTreeNode();
        root3.right.value = 2;
        root3.right.right = new BinaryTreeNode();
        root3.right.right.value = 3;
        root3.right.right.right = new BinaryTreeNode();
        root3.right.right.right.value = 4;
        root3.right.right.right.right = new BinaryTreeNode();
        root3.right.right.right.right.value = 5;

        // 有一条路径上面的结点和为15
        System.out.println("findPath(root3, 15);");
        findPath(root3, 15);

        // 没有路径上面的结点和为16
        System.out.println("findPath(root3, 16);");
        findPath(root3, 16);

        System.out.println();

        // 树中只有1个结点
        BinaryTreeNode root4 = new BinaryTreeNode();

        root4.value = 1;
        // 有一条路径上面的结点和为1
        System.out.println("findPath(root4, 1);");
        findPath(root4, 1);

        // 没有路径上面的结点和为2
        System.out.println("findPath(root4, 2);");
        findPath(root4, 2);

        System.out.println();

        // 树中没有结点
        System.out.println("findPath(null, 0);");
        findPath(null, 0);
    }
}

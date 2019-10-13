package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月13日 14:06
 */
public class Test39 {

    private static class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode() {
        }

        public BinaryTreeNode(int val) {
            this.val = val;
        }
    }

    public static int treeDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = 1 + treeDepth(root.left);
        int rightDepth = 1 + treeDepth(root.right);
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }

    /**
     * 判断是否是平衡二叉树，第一种解法，每个节点遍历了多次，但是理解起来比较简单
     *
     * @param root
     * @return
     */
    public static boolean isBalanced1(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        int leftDepth = 1 + treeDepth(root.left);
        int rightDepth = 1 + treeDepth(root.right);
        if (leftDepth > rightDepth + 1 || rightDepth > leftDepth + 1) {
            return false;
        }
        return isBalanced1(root.left) && isBalanced1(root.right);
    }

    /**
     * 判断是否是平衡二叉树，第二种解法，通过遍历每个节点，通过后序遍历实现
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        int[]depth = new int[1];
        return isBalanced(root, depth);
    }

    private static boolean isBalanced(BinaryTreeNode root, int[] depth) {
        if (root == null) {
            return true;
        }
        int[]lefthDepths = {depth[0]};

        if (!isBalanced(root.left, lefthDepths)) {
            return false;
        }
        int leftDepth = lefthDepths[0];

        int[]rightDepths = {depth[0]};
        if (!isBalanced(root.right, rightDepths)) {
            return false;
        }
        int rightDepth = rightDepths[0];

        if (leftDepth > rightDepth + 1 || rightDepth > leftDepth + 1) {
            return false;
        }
        depth[0] = leftDepth > rightDepth? leftDepth + 1 : rightDepth + 1;
        return true;
    }


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }


    // 完全二叉树
    //             1
    //         /      \
    //        2        3
    //       /\       / \
    //      4  5     6   7
    private static void test1() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(1);
        BinaryTreeNode n3 = new BinaryTreeNode(1);
        BinaryTreeNode n4 = new BinaryTreeNode(1);
        BinaryTreeNode n5 = new BinaryTreeNode(1);
        BinaryTreeNode n6 = new BinaryTreeNode(1);
        BinaryTreeNode n7 = new BinaryTreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        System.out.println(treeDepth(n1) == 3);
        System.out.println(isBalanced(n1) == true);
        System.out.println("----------------");

    }

    // 不是完全二叉树，但是平衡二叉树
    //             1
    //         /      \
    //        2        3
    //       /\         \
    //      4  5         6
    //        /
    //       7
    private static void test2() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(1);
        BinaryTreeNode n3 = new BinaryTreeNode(1);
        BinaryTreeNode n4 = new BinaryTreeNode(1);
        BinaryTreeNode n5 = new BinaryTreeNode(1);
        BinaryTreeNode n6 = new BinaryTreeNode(1);
        BinaryTreeNode n7 = new BinaryTreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n3.right = n6;

        System.out.println(treeDepth(n1) == 4);
        System.out.println(isBalanced(n1) == true);
        System.out.println("----------------");
    }

    // 不是平衡二叉树
    //             1
    //         /      \
    //        2        3
    //       /\
    //      4  5
    //        /
    //       7
    private static void test3() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(1);
        BinaryTreeNode n3 = new BinaryTreeNode(1);
        BinaryTreeNode n4 = new BinaryTreeNode(1);
        BinaryTreeNode n5 = new BinaryTreeNode(1);
        BinaryTreeNode n6 = new BinaryTreeNode(1);
        BinaryTreeNode n7 = new BinaryTreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;

        System.out.println(treeDepth(n1) == 4);
        System.out.println(isBalanced(n1) == false);
        System.out.println("----------------");
    }

    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test4() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(1);
        BinaryTreeNode n3 = new BinaryTreeNode(1);
        BinaryTreeNode n4 = new BinaryTreeNode(1);
        BinaryTreeNode n5 = new BinaryTreeNode(1);
        BinaryTreeNode n6 = new BinaryTreeNode(1);
        BinaryTreeNode n7 = new BinaryTreeNode(1);

        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n4.left = n5;

        System.out.println(treeDepth(n1) == 5);
        System.out.println(isBalanced(n1) == false);
        System.out.println("----------------");
    }

    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    private static void test5() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(1);
        BinaryTreeNode n3 = new BinaryTreeNode(1);
        BinaryTreeNode n4 = new BinaryTreeNode(1);
        BinaryTreeNode n5 = new BinaryTreeNode(1);
        BinaryTreeNode n6 = new BinaryTreeNode(1);
        BinaryTreeNode n7 = new BinaryTreeNode(1);

        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        n4.right = n5;

        System.out.println(treeDepth(n1) == 5);
        System.out.println(isBalanced(n1) == false);
        System.out.println("----------------");
    }
}

package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月15日 11:22
 */
public class Test50 {
    /**
     * 二叉树的树结点
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    /**
     * 找二叉排序树中两个结点的最低公共祖先
     * 通过找到在两个节点之间的节点则是他们的最低公共祖先
     * @param root 树的根结点
     * @param p1 结点1
     * @param p2 结点2
     * @return 公共结点，没有返回null
     */
    public static BinaryTreeNode getLastCommonParent(BinaryTreeNode root, BinaryTreeNode p1, BinaryTreeNode p2) {
        if (root == null || p1 == null || p2 == null) {
            return null;
        }
        // p1是小的节点，p2是大的节点
        if (p1.value > p2.value) {
            BinaryTreeNode temp = p1;
            p1 = p2;
            p2 = temp;
        }
        if (root.value > p2.value) {
            return getLastCommonParent(root.left, p1, p2);
        }
        if (root.value < p1.value) {
            return getLastCommonParent(root.right, p1, p2);
        }
        return root;
    }

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
    }

    //            10
    //         /      \
    //        6        14
    //       /\        /\
    //      4  8     12  16
    private static void test01() {
        BinaryTreeNode node10 = new BinaryTreeNode();
        node10.value = 10;

        BinaryTreeNode node6 = new BinaryTreeNode();
        node6.value = 6;

        BinaryTreeNode node14 = new BinaryTreeNode();
        node14.value = 14;

        BinaryTreeNode node4 = new BinaryTreeNode();
        node4.value = 4;

        BinaryTreeNode node8 = new BinaryTreeNode();
        node8.value = 8;

        BinaryTreeNode node12 = new BinaryTreeNode();
        node12.value = 12;

        BinaryTreeNode node16 = new BinaryTreeNode();
        node16.value = 16;

        node10.left = node6;
        node10.right = node14;

        node6.left = node4;
        node6.right = node8;

        node14.left = node12;
        node14.right = node16;

        System.out.println(getLastCommonParent(node10, node4, node8) == node6);
        System.out.println(getLastCommonParent(node10, node12, node14) == node14);
        System.out.println(getLastCommonParent(node10, node4, node12) == node10);
        System.out.println(getLastCommonParent(node10, node4, node14) == node10);
        System.out.println();
    }

    //               5
    //              /
    //             4
    //            /
    //           3
    //          /
    //         2
    //        /
    //       1
    private static void test02() {
        BinaryTreeNode node1 = new BinaryTreeNode();
        node1.value = 1;

        BinaryTreeNode node2 = new BinaryTreeNode();
        node2.value = 2;

        BinaryTreeNode node3 = new BinaryTreeNode();
        node3.value = 3;

        BinaryTreeNode node4 = new BinaryTreeNode();
        node4.value = 4;

        BinaryTreeNode node5 = new BinaryTreeNode();
        node5.value = 5;

        node5.left = node4;
        node4.left = node3;
        node3.left = node2;
        node2.left = node1;

        System.out.println(getLastCommonParent(node5, node4, node3) == node4);
        System.out.println(getLastCommonParent(node5, node4, node1) == node4);
        System.out.println();
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
    private static void test03() {
        BinaryTreeNode node1 = new BinaryTreeNode();
        node1.value = 1;

        BinaryTreeNode node2 = new BinaryTreeNode();
        node2.value = 2;

        BinaryTreeNode node3 = new BinaryTreeNode();
        node3.value = 3;

        BinaryTreeNode node4 = new BinaryTreeNode();
        node4.value = 4;

        BinaryTreeNode node5 = new BinaryTreeNode();
        node5.value = 5;

        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        node4.right = node5;

        System.out.println(getLastCommonParent(node1, node4, node3) == node3);
        System.out.println(getLastCommonParent(node1, node4, node5) == node4);
        System.out.println();
    }

    // 异常
    private static void test04() {
        BinaryTreeNode node1 = new BinaryTreeNode();
        node1.value = 1;

        System.out.println(getLastCommonParent(node1, null, node1) == null);
        System.out.println();
    }
}

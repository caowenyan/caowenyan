package com.cao;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月15日 12:13
 */
public class Test50_1 {
    /**
     * 树的结点定义
     */
    private static class TreeNode {
        int val;

        List<TreeNode> children = new LinkedList<>();


        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    /**
     * 找树中两个结点的最低公共祖先
     * 若是在树中存在父节点parent，则根据两个节点所在的链表，然后求解两条链表的相交点
     * 本题在书中不存在parent节点，则需要遍历树找到树的位置，然后根据比较两个链表
     * @param root 树的根结点
     * @param p1 结点1
     * @param p2 结点2
     * @return 公共结点，没有返回null
     */
    public static TreeNode getLastCommonParent(TreeNode root, TreeNode p1, TreeNode p2) {
        if (root == null || p1 == null || p2 == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        boolean b1 = getNodePathStack(root, p1, stack);
        boolean b2 = getNodePathStack(root, p2, stack2);
        if (!b1 || !b2) {
            return null;
        }
        if (stack.size() > stack2.size()) {
            // 删除节点
            while (stack.size() != stack2.size()) {
                stack.pop();
            }
        } else {
            // 删除节点
            while (stack.size() != stack2.size()) {
                stack2.pop();
            }
        }
        while (stack.peek() != stack2.peek() && !stack.isEmpty()) {
            stack.pop();
            stack2.pop();
        }
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    public static boolean getNodePathStack(TreeNode root, TreeNode p1, Stack stack) {
        if (root == null) {
            return false;
        }
        stack.add(root);
        if (p1 == root) {
            return true;
        }
        if (root.children != null && root.children.size() != 0) {
            for (TreeNode node : root.children) {
                boolean result = getNodePathStack(node, p1, stack);
                if (result) {
                    return true;
                }
            }
        }
        stack.pop();
        return false;
    }

    public static void main(String[] args) {
        test01();
        System.out.println("==========");
        test02();
        System.out.println("==========");
        test03();
    }

    // 形状普通的树
    //             1
    //           /   \
    //         2      3
    //        /         \
    //      4            5
    //     / \        /  |  \
    //    6   7      8   9  10
    public static void test01() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.children.add(n2);
        n1.children.add(n3);

        n2.children.add(n4);

        n4.children.add(n6);
        n4.children.add(n7);

        n3.children.add(n5);

        n5.children.add(n8);
        n5.children.add(n9);
        n5.children.add(n10);

        System.out.println(getLastCommonParent(n1, n6, n8) == n1);
        System.out.println(getLastCommonParent(n1, n6, n7) == n4);
        System.out.println(getLastCommonParent(n1, n9, n10) == n5);
        System.out.println(getLastCommonParent(n1, n8, n3) == n3);
    }

    // 树退化成一个链表
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test02() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);

        System.out.println(getLastCommonParent(n1, n4, n5) == n4);
        System.out.println(getLastCommonParent(n1, n4, n1) == n1);
    }

    // 树退化成一个链表，一个结点不在树中
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test03() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);

        System.out.println(getLastCommonParent(n1, n5, n6) == null);
    }
}
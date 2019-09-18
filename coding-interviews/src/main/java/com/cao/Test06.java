package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月17日 11:07
 */
public class Test06 {
    /**
     * 二叉树节点类
     */
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二节树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * @param preorder 前序遍历
     * @param inorder  中序遍历
     * @return 树的根结点
     */
    public static BinaryTreeNode construct(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        return constructCore(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static BinaryTreeNode constructCore(int[]preorder, int[]inorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd) {
        if (preorderEnd < preorderStart || inorderEnd < inorderStart) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = rootVal;
        int leftNum = 0;
        for (int i = inorderStart ; i <= inorderEnd ; i ++) {
            if (inorder[i] == rootVal) {
                break;
            } else {
                leftNum ++;
            }
        }
        // 这个地方是网上例子系那是的，开始没考虑到这块，顺带将上边的遍历改成了自增，而不是left = i - inorderStart
        if (leftNum > inorderEnd - inorderStart) {
            throw new RuntimeException("参数异常");
        }
        // 递归构建当前根结点的左子树，左子树的元素个数：leftNum个
        // 左子树对应的前序遍历的位置在[ps + 1, ps + leftNum]
        // 左子树对应的中序遍历的位置在[is, is + leftNum - 1]
        root.left = constructCore(preorder, inorder, preorderStart + 1, preorderStart + leftNum, inorderStart, inorderStart + leftNum - 1);
        // 右子树对应的前序遍历的位置在[ps + leftNum + 1, pe]
        // 右子树对应的中序遍历的位置在[is + leftNum + 1, ie]
        root.right = constructCore(preorder, inorder, preorderStart + 1 + leftNum, preorderEnd, inorderStart + leftNum + 1, inorderEnd);
        return root;
    }

    public static void main(String[] args) {

        test1();
        System.out.println();
        test2();
        System.out.println();
        test3();
        System.out.println();
        test4();
        System.out.println();
        test5();
        System.out.println();
        test6();
        System.out.println();
        test7();
    }

    // 中序遍历二叉树
    public static void printTree(BinaryTreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.value + " ");
            printTree(root.right);
        }

    }

    // 普通二叉树
    //              1
    //           /     \
    //          2       3
    //         /       / \
    //        4       5   6
    //         \         /
    //          7       8
    private static void test1() {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 所有结点都没有右子结点
    //            1
    //           /
    //          2
    //         /
    //        3
    //       /
    //      4
    //     /
    //    5
    private static void test2() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {5, 4, 3, 2, 1};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 所有结点都没有左子结点
    //            1
    //             \
    //              2
    //               \
    //                3
    //                 \
    //                  4
    //                   \
    //                    5
    private static void test3() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 树中只有一个结点
    private static void test4() {
        int[] preorder = {1};
        int[] inorder = {1};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 完全二叉树
    //              1
    //           /     \
    //          2       3
    //         / \     / \
    //        4   5   6   7
    private static void test5() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 输入空指针
    private static void test6() {
        construct(null, null);
    }

    // 输入的两个序列不匹配
    private static void test7() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 8, 1, 6, 3, 7};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

}

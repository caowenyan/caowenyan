package com.cao;

import java.util.Stack;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月13日 12:17
 */
public class Test37 {
    /**
     * 链表结点类
     */
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    /**
     * 后一种解法是从尾部开始遍历的，若是有相交点，则相交点后面肯定存在相同的位数，
     * 所以获取两个链表的长度，然后长的节点后移多的个数，然后比较那个数据相等也可以
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null ) {
            return null;
        }
        if (head1 == head2) {
            return head1;
        }
        int length1 = getListLength(head1);
        int length2 = getListLength(head2);
        int step = Math.abs(length1 - length2);
        if (length1 > length2) {
            while (step > 0) {
                head1 = head1.next;
                step --;
            }
        } else if (length2 > length1) {
            while (step > 0) {
                head2 = head2.next;
                step --;
            }
        }
        while (head1 != null) {
            if (head1 == head2) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return null;
    }

    /**
     例如下面的一种结构，若是相交，最后一个节点肯定相同。
     1 - 2 - 3 \
     6 - 7
     4 - 5 /
     * 可以比较最后一位相等的话找到前一位继续比较，直到不相等的前一个节点就是相交点
     * 从头结点遍历找到最后一个节点比较，然后从尾节点开始遍历，后进先出，栈
     *
     * @param head1 第一个链表
     * @param head2 第二个链表
     * @return 找到的公共结点，没有返回null
     */
    public static ListNode findFirstCommonNode1(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null ) {
            return null;
        }
        if (head1 == head2) {
            return head1;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.add(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.add(head2);
            head2 = head2.next;
        }
        if (stack1.peek() != stack2.peek()) {
            return null;
        }
        ListNode result = null;
        while (stack1.peek() == stack2.pop()) {
            result = stack1.pop();
        }
        return result;
    }

    private static int getListLength(ListNode head) {
        int result = 0;
        while (head != null) {
            result++;
            head = head.next;
        }

        return result;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
        // 第一个公共结点在链表中间
        // 1 - 2 - 3 \
        //            6 - 7
        //     4 - 5 /
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n6;
        n6.next = n7;

        n4.next = n5;
        n5.next = n6;

        System.out.println(findFirstCommonNode(n1, n4) == n6);
    }


    private static void test2() {
        // 没有公共结点
        // 1 - 2 - 3 - 4
        //
        // 5 - 6 - 7
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        n5.next = n6;
        n6.next = n7;
        System.out.println(findFirstCommonNode(n1, n5) == null);
    }

    private static void test3() {
        // 公共结点是最后一个结点
        // 1 - 2 - 3 - 4 \
        //                7
        //         5 - 6 /
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n7;

        n5.next = n6;
        n6.next = n7;
        System.out.println(findFirstCommonNode(n1, n5) == n7);
    }

    private static void test4() {
        // 公共结点是第一个结点
        // 1 - 2 - 3 - 4 - 5
        // 两个链表完全重合
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(findFirstCommonNode(n1, n1) == n1);
    }
}

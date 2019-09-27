package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月27日 16:40
 */
public class Test17 {
    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
     * 改变了原有的元素的顺序很不好，所以可以用值来代替地址
     * @param head1 第一个有序链表
     * @param head2 第二个有序链表
     * @return 合并后的有序链表头
     */
    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode head = new ListNode();
        ListNode node = head;
        while (head1 != null && head2 != null) {
            if (head1.value > head2.value) {
                node.next = head2;
                head2 = head2.next;
            } else {
                node.next = head1;
                head1 = head1.next;
            }
            node = node.next;
        }
        if (head1 != null) {
            node.next = head1;
        }
        if (head2 != null) {
            node.next = head2;
        }
        return head.next;
    }

    public static ListNode merge2(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode head = new ListNode();
        if (head1.value > head2.value) {
            head.value = head2.value;
            head.next = merge2(head1, head2.next);
        } else {
            head.value = head1.value;
            head.next = merge2(head1.next, head2);
        }
        return head;
    }
    /**
     * 输出链表的元素值
     *
     * @param head 链表的头结点
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode();
        head1.value = 1;

        head1.next = new ListNode();
        head1.next.value = 2;

        head1.next.next = new ListNode();
        head1.next.next.value = 3;

        head1.next.next.next = new ListNode();
        head1.next.next.next.value = 4;

        head1.next.next.next.next = new ListNode();
        head1.next.next.next.next.value = 5;


        ListNode head2 = new ListNode();
        head2.value = 1;

        head2.next = new ListNode();
        head2.next.value = 3;

        head2.next.next = new ListNode();
        head2.next.next.value = 5;

        head2.next.next.next = new ListNode();
        head2.next.next.next.value = 6;

        head2.next.next.next.next = new ListNode();
        head2.next.next.next.next.value = 7;

        ListNode head = merge(head1, head2);
        printList(head);
        printList(head1);
        printList(head2);
        System.out.println();

        head = merge(head1, head2);
        printList(head);
    }
}

package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月26日 16:21
 */
public class Test13 {
    /**
     * 链表结点
     */
    public static class ListNode {
        int value; // 保存链表的值
        ListNode next; // 下一个结点
    }

    /**
     * 给定单向链表的头指针和一个结点指针，定义一个函数在0(1)时间删除该结点,
     * 【注意1：这个方法和文本上的不一样，书上的没有返回值，这个因为JAVA引用传递的原因，
     * 如果删除的结点是头结点，如果不采用返回值的方式，那么头结点永远删除不了】
     * 【注意2：输入的待删除结点必须是待链表中的结点，否则会引起错误，这个条件由用户进行保证】
     *
     * @param head        链表表的头
     * @param toBeDeleted 待删除的结点
     * @return 删除后的头结点
     */
    public static ListNode deleteNode(ListNode head, ListNode toBeDeleted) {
        if (head == null || toBeDeleted == null) {
            return head;
        }
        if (head == toBeDeleted) {
            return head.next;
        }

        // 若是待删除的节点有后继节点，直接替换删除
        if (toBeDeleted.next != null) {
            ListNode next = toBeDeleted.next;
            toBeDeleted.next = next.next;
            toBeDeleted.value = next.value;
            next.next = null;
            next = null;
        } else {
            // 若是删除的是最后一个元素，需要从头遍历删除
            ListNode pre = head;
            // 这个地方需要注意若是删除的元素不在，有可能导致空指针，所以需要判断pre!=null
            while (pre != null && pre.next != toBeDeleted) {
                pre = pre.next;
            }
            if (pre != null) {
                pre.next = null;
                toBeDeleted = null;
            }
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


        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        ListNode middle = head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        ListNode last = head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        head = deleteNode(head, null); // 删除的结点为空
        printList(head);
        ListNode node = new ListNode();
        node.value = 12;

        head = deleteNode(head, head); // 删除头结点
        printList(head);
        head = deleteNode(head, last); // 删除尾结点
        printList(head);
        head = deleteNode(head, middle); // 删除中间结点
        printList(head);

        head = deleteNode(head, node); // 删除的结点不在链表中
        printList(head);
    }
}

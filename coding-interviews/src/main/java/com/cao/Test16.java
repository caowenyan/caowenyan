package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月27日 15:53
 */
public class Test16 {
    public static class ListNode {
        int value;
        ListNode next;
    }

    /**
     * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
     *
     * @param head 链表的头结点
     * @return 反转后的链表的头结点
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 新的头结点
        ListNode newHead = null;
        while (head != null) {
            // pre作为本来的新的头结点的next节点
            ListNode pre = newHead;
            // 下一个循环的节点
            ListNode next = head.next;
            // 设置新的头结点为当前节点，next指向前一个头结点，构成短暂的头结点
            newHead = head;
            newHead.next = pre;
            // 头结点指向下一个位置
            head = next;
        }
        return newHead;
    }

    /**
     * 递归方法，脑子总是迷糊，递归还是学的不好
     a->b->c->d->e
     方法执行：
        f(b)
            f(c)
                f(d) = e
                    a->b->c->d<-e
                    这个地方需要改变d的方向了
                        d.next.next = d
                        a->b->c<-d<-e
                这个地方需要改变c的方向了
                    c.next.next = c
                    a->b->c<-d<-e
            这个时候需要改变b的方向了
                b.next.next = b
                a->b<-c<-d<-e
        这个时候需要改变a的方向了
            a.next.next = a
            a<-b<-c<-d<-e
        return e;
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
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

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        printList(head);
        head = reverseList(head);
        printList(head);
        System.out.println();
    }
}

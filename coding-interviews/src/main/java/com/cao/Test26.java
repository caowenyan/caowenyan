package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 这道题开始没有看明白，主要是因为没有搞清楚为什么书中提倡的是这种方案，
 * 仔细想想才知道，若是新建一个链表，则next的时间复杂度是O(n),sibling的时间复杂度是O(n^2)
 * 但是书中推荐的方式，next是O(n),sibling是O(n),拆分也是O(n)，所以时间复杂度更低
 * @date 2019年10月09日 15:28
 */
public class Test26 {
    /**
     * 复杂链表结点
     */
    public static class ComplexListNode {
        int value;
        ComplexListNode next;
        ComplexListNode sibling;
    }

    /**
     * 实现函复制一个复杂链表。在复杂链表中，每个结点除了有一个next字段指向下一个结点外，
     * 还有一个sibling字段指向链表中的任意结点或者NULL
     *
     * @param head 链表表头结点
     * @return 复制结点的头结点
     */
    public static ComplexListNode clone(ComplexListNode head) {
        // 如果链表为空就直接返回空
        if (head == null) {
            return null;
        }

        // 先复制结点
        cloneNodes(head);
        // 再链接sibling字段
        connectNodes(head);
        // 将整个链表拆分，返回复制链表的头结点
        return reconnectNodes(head);
    }

    /**
     * 复制一个链表，并且将复制后的结点插入到被复制的结点后面，只链接复制结点的next字段
     * A->A'->B->B'....
     * @param head 待复制链表的头结点
     */
    public static void cloneNodes(ComplexListNode head) {
        while (head != null) {
            ComplexListNode node = new ComplexListNode();
            node.value = head.value;
            node.next = head.next;
            head.next = node;
            head = node.next;
        }
    }

    /**
     * 设置复制结点的sibling字段
     *
     * @param head 链表的头结
     */
    public static void connectNodes(ComplexListNode head) {
        while (head != null) {
            if (head.sibling != null) {
                // head有sibling节点，需要设置复制节点的sibling
                head.next.sibling = head.sibling.next;
            }
            // 主要中间有复制节点，肯定有next节点
            head = head.next.next;
        }
    }

    /**
     * 刚复制结点和被复制结点拆开，还原被复制的链表，同时生成监制链表
     * A->A'->B->B'->C->C'...
     * @param head 链表的头结点
     * @return 复制链表的头结点
     */
    public static ComplexListNode reconnectNodes(ComplexListNode head) {
        if (head == null) {
            return null;
        }
        ComplexListNode newHead = head.next;
        ComplexListNode temp = newHead;
        while(temp.next != null) {
            head.next = temp.next;
            temp.next = temp.next.next;
            head = head.next;
            temp = temp.next;
        }
        head.next = null;
        temp.next = null;
        return newHead;
    }

    /**
     * 输出链表信息
     *
     * @param head 链表头结点
     */
    public static void printList(ComplexListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }
    /**
     * 判断两个链表是否是同一个链表，不是值相同
     *
     * @param h1 链表头1
     * @param h2 链表头2
     * @return true：两个链表是同一个链表，false：不是
     */
    public static boolean isSame(ComplexListNode h1, ComplexListNode h2) {
        while (h1 != null && h2 != null) {
            if (h1 == h2) {
                h1 = h1.next;
                h2 = h2.next;
            } else {
                return false;
            }
        }

        return h1 == null && h2 == null;
    }

    public static void main(String[] args) {
        //          -----------------
        //         \|/              |
        //  1-------2-------3-------4-------5
        //  |       |      /|\             /|\
        //  --------+--------               |
        //          -------------------------
        ComplexListNode head = new ComplexListNode();
        head.value = 1;
        head.next = new ComplexListNode();
        head.next.value = 2;
        head.next.next = new ComplexListNode();
        head.next.next.value = 3;
        head.next.next.next = new ComplexListNode();
        head.next.next.next.value = 4;
        head.next.next.next.next = new ComplexListNode();
        head.next.next.next.next.value = 5;

        head.sibling = head.next.next;
        head.next.sibling = head.next.next.next.next.next;
        head.next.next.next.sibling = head.next;

        ComplexListNode tmp = head;
        printList(head);
        ComplexListNode newHead = clone(head);
        printList(head);
        System.out.println(isSame(head, tmp));
        printList(newHead);
        System.out.println(isSame(head, newHead));


        // 有指向自身的情况
        //          -----------------
        //         \|/              |
        //  1-------2-------3-------4-------5
        //         |       | /|\           /|\
        //         |       | --             |
        //         |------------------------|
        ComplexListNode head2 = new ComplexListNode();
        head2.value = 1;
        head2.next = new ComplexListNode();
        head2.next.value = 2;
        head2.next.next = new ComplexListNode();
        head2.next.next.value = 3;
        head2.next.next.next = new ComplexListNode();
        head2.next.next.next.value = 4;
        head2.next.next.next.next = new ComplexListNode();
        head2.next.next.next.next.value = 5;

        head2.next.sibling = head2.next.next.next.next;
        head2.next.next.next.sibling = head2.next.sibling;
        head2.next.next.sibling = head2.next.next;

        System.out.println("\n");
        tmp = head2;
        printList(head2);
        ComplexListNode newHead2 = clone(head2);
        printList(head2);
        System.out.println(isSame(head2, tmp));
        printList(newHead2);
        System.out.println(isSame(head2, newHead2));


        ComplexListNode head3 = new ComplexListNode();
        head3.value = 1;

        System.out.println("\n");
        tmp = head3;
        printList(head3);
        ComplexListNode newHead3 = clone(head3);
        printList(head3);
        System.out.println(isSame(head3, tmp));
        printList(newHead3);
        System.out.println(isSame(head3, newHead3));

        System.out.println("\n");
        ComplexListNode head4 = clone(null);
        printList(head4);
    }
}

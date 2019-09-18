package com.cao;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 两个栈实现队列（举一反三：两个队列实现栈）
 * @date 2019年09月17日 12:18
 */
public class Test07 {
    /**
     * 用两个栈模拟的队列
     * 用两个核实现一个队列。队列的声明如下，诸实现它的两个函数appendTail和deleteHead，
     * 分别完成在队列尾部插入结点和在队列头部删除结点的功能。
     */
    public static class MQueue<T> {
        Stack<T> stack1 = new Stack<T>();
        Stack<T> stack2 = new Stack<T>();

        // 添加操作，成在队列尾部插入结点
        public void appendTail(T t) {
            stack1.add(t);
        }

        // 删除操作，在队列头部删除结点
        public T deleteHead() {
            if (stack2.empty()) {
                while(!stack1.empty()) {
                    stack2.add(stack1.pop());
                }
            }
            if (stack2.empty()) {
                return null;
            }
            return stack2.pop();
        }
    }
    /**
     * 用两个队列模拟的栈，先进先出最后实现后进先出
     */
    public static class MStack<T> {
        Queue<T> queue1 = new LinkedList<T>();
        Queue<T> queue2 = new LinkedList<T>();

        // 添加操作，成在队列尾部插入结点
        public void appendTail(T t) {
            if (queue1.isEmpty()) {
                queue2.add(t);
            } else {
                queue1.add(t);
            }
        }

        // 删除操作，在队列头部删除结点
        public T deleteHead() {
            if (!queue1.isEmpty()){
                return deleteHead(queue1, queue2);
            }
            if (!queue2.isEmpty()) {
                return deleteHead(queue2, queue1);
            }
            return null;
        }

        private T deleteHead(Queue<T> dataQueue, Queue<T> emptyQueue) {
            T data = dataQueue.poll();
            while (!dataQueue.isEmpty()) {
                emptyQueue.add(data);
                data = dataQueue.poll();
            }
            return data;
        }
    }
    public static void main(String[] args) {
        MQueue queue = new MQueue();
        queue.appendTail("a");
        queue.appendTail("b");
        queue.appendTail("c");
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        queue.appendTail("d");
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());

        System.out.println("==========");

        MStack stack = new MStack();
        stack.appendTail("a");
        stack.appendTail("b");
        stack.appendTail("c");
        System.out.println(stack.deleteHead());
        System.out.println(stack.deleteHead());
        stack.appendTail("d");
        System.out.println(stack.deleteHead());
        System.out.println(stack.deleteHead());
    }
}

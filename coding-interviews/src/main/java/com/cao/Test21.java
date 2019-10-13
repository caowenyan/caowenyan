package com.cao;

import java.util.Stack;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月08日 19:06
 */
public class Test21 {
    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。
     * 在该栈中，调用pop、push 及min的时间复杂度都是0(1)
     *
     * @param <T> 泛型参数
     */
    public static class StackWithMin<T extends Comparable<T>> {
        // 数据栈，用于存放插入的数据
        private Stack<T> dataStack;
        // 最小数位置栈，存放数据栈中最小的数
        private Stack<T> minStack;

        // 构造函数
        public StackWithMin() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        /**
         * 出栈方法
         * @return 栈顶元素
         */
        public T pop() {
           if (dataStack.empty()) {
               throw new RuntimeException("没有元素了");
           }
           T t = dataStack.pop();
           minStack.pop();
           return t;
        }

        /**
         * 元素入栈
         * @param t 入栈的元素
         */
        public void push(T t) {
            T min = null;
            if (!dataStack.empty()) {
                // 若是已经有最小元素，需要重新计算
                min = min();
                min = t.compareTo(min) < 0? t:min;
            } else {
                // 若是没有最小元素，则最小元素是当前元素
                min = t;
            }
            dataStack.push(t);
            minStack.push(min);
        }

        /**
         * 获取栈中的最小元素
         * @return 栈中的最小元素
         */
        public T min() {
            if (dataStack.empty()) {
                throw new RuntimeException("没有元素了");
            }
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        StackWithMin<Integer> stack = new StackWithMin<>();
        stack.push(3);
        System.out.println(stack.min() == 3);
        stack.push(4);
        System.out.println(stack.min() == 3);
        stack.push(2);
        System.out.println(stack.min() == 2);
        stack.push(3);
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 2);
        stack.pop();
        System.out.println(stack.min() == 3);
        stack.push(0);
        System.out.println(stack.min() == 0);
    }
}

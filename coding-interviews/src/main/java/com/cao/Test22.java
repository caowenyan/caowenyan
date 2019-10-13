package com.cao;

import java.util.Stack;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月09日 09:55
 */
public class Test22 {
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如序列1 、2、3 、4、5 是某栈压栈序列，
     * 序列4、5、3、2、1是该压栈序列对应的一个弹出序列，
     * 但4、3、5、1、2就不可能是该压棋序列的弹出序列。
     *
     * @param push 入栈序列
     * @param pop  出栈序列
     * @return true：出栈序列是入栈序列的一个弹出顺序
     */
    public static boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null) {
            return false;
        }
        if (push.length == 0 || pop.length == 0) {
            return false;
        }
        if (push.length != pop.length) {
            return false;
        }
        Stack<Integer> stack = new Stack();
        int pushIndex = 0;
        int popIndex = 0;
        boolean flag = true;
        while (pushIndex < push.length) {
            int value = push[pushIndex++];
            stack.add(value);
            while (value == pop[popIndex]) {
                stack.pop();
                popIndex++;
                if (stack.isEmpty()) {
                    break;
                }
                value = stack.peek();
            }
        }
        // 既然是两者元素相等，若是等于最大值，则表示所有元素出栈，符合题意，否则表示有元素未出栈，不符合题意
        if (popIndex == pop.length) {
            return true;
        }
        return false;
    }
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。
     * 【按书本上的思路进行求解，两者相差不大】
     *
     * @param push 入栈序列
     * @param pop  出栈序列
     * @return true：出栈序列是入栈序列的一个弹出顺序
     */
    public static boolean isPopOrder2(int[] push, int[] pop) {

        // 用于记录判断出栈顺序是不是入栈顺的一个出栈序列，默认false
        boolean isPossible = false;

        // 当入栈和出栈数组者都不为空，并且都有数据，并且数据个数都相等
        if (push != null && pop != null && push.length > 0 && push.length == pop.length) {
            Stack<Integer> stack = new Stack<>();
            int pushIndex = 0;
            int popIndex = 0;
            while (popIndex < pop.length) {
                while (pushIndex < push.length && (stack.isEmpty() || (!stack.isEmpty() && stack.peek() != pop[popIndex]))) {
                    stack.add(push[pushIndex ++]);
                }
                while (popIndex < pop.length && !stack.isEmpty() && stack.peek() == pop[popIndex]) {
                    stack.pop();
                    popIndex++;
                }
                // 若是全部加到栈中但是还未完全出栈说明不正确，直接抛出错误
                if (pushIndex == push.length && !stack.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        return isPossible;
    }

    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {3, 5, 4, 2, 1};
        int[] pop3 = {4, 3, 5, 1, 2};
        int[] pop4 = {3, 5, 4, 1, 2};

        System.out.println(true == isPopOrder(push, pop1) );
        System.out.println(true == isPopOrder(push, pop2));
        System.out.println(false == isPopOrder(push, pop3));
        System.out.println(false == isPopOrder(push, pop4));

        int[] push5 = {1};
        int[] pop5 = {2};
        System.out.println(false == isPopOrder(push5, pop5));

        int[] push6 = {1};
        int[] pop6 = {1};
        System.out.println(true == isPopOrder(push6, pop6));

        System.out.println(false == isPopOrder(null, null));

        // 测试方法2
        System.out.println();
        System.out.println(true == isPopOrder2(push, pop1));
        System.out.println(true == isPopOrder2(push, pop2));
        System.out.println(false == isPopOrder2(push, pop3));
        System.out.println(false == isPopOrder2(push, pop4));
        System.out.println(false == isPopOrder2(push5, pop5));
        System.out.println(true == isPopOrder2(push6, pop6));
        System.out.println(false == isPopOrder2(null, null));
    }
}

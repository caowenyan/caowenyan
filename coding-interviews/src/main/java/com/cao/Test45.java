package com.cao;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月14日 16:26
 */
public class Test45 {
    /**
     * 0，1，2.。。，n-1这n个数字组成一个圆圈，从数字0开始从这个圆圈中删除第m个数字，求圆圈里最后剩下的数字
     * 每删除一个数字需要m步，通过remove(index)需要查询n次，总共遍历n次，时间复杂度是O(mn)还是O(n^2)
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining1(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        List<Integer> list = new LinkedList();
        for (int i = 0; i < n ; i ++) {
            list.add(i);
        }
        int remove = 0;
        int size = n;
        while (size > 1) {
            // 注意这个地方需要减去1
            remove = (remove + m - 1) % size;
            list.remove(remove);
            size --;
        }
        return list.get(0);
    }

    /**
     * 约瑟夫环问题
     * 定义f(n,m)表示的是n个数字每走m步删除一个数字
     * 第一个被删除的数字是(m-1)%n记做k
     * 删除k之后剩下n-1个数字k+1...n-1,0,1...k-1
     * 即现在的n-1个数字同样组成了f'(n-1,m)
     * f(n,m)=f'(n-1,m),因为f'(n-1,m)删除剩下一个数字，那f(n,m)肯定删除最后一个数字
     * 所以现在需要找到两个的关系。
     *
     * x: 0,.....n-k-2,n-k-1,n-k....n-2
     * y: k+1,...n-1,0,1...k-1
     * y(x) = (k + 1 + x )%n
     * f(n,m)=f'(n-1,m)=[k+1+f(n-1,m)]%n=[m+f(n-1,m)]%n
     */
    public static int lastRemaining(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        // 每走m步删除的数字，arr[0]:每走m步删除的第一个数字
        int arr[] = new int[n];
        arr[0] = 0;
        // index和n差了一个数字
        int currentIndex = 1;
        while (currentIndex < n) {
            // 注意这个地方%是当前的圆圈的大小，而不是n
            arr[currentIndex] = (arr[currentIndex - 1] + m) % (currentIndex + 1);
            currentIndex ++;
        }
        return arr[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3) == 3);
        System.out.println(lastRemaining(5, 2) == 2);
        System.out.println(lastRemaining(6, 7) == 4);
        System.out.println(lastRemaining(6, 6) == 3);
        System.out.println(lastRemaining(0, 0) == -1);
    }
}

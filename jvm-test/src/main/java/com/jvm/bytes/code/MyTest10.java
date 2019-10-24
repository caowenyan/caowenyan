package com.jvm.bytes.code;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月17日 11:36
 */
public class MyTest10 {
    int a = 1;

    /**
     * 注意局部变量除了有局部变量表重复利用的情况，也会存在一个变量被复制到好多个slot
     * 主要是因为return的时候需要把变量存储到某个slot中，然后执行final，最后返回在return前存储的slot
     * 所以在localVariableTable中有很多相同的slot
     * 在final语句中，先把x所在的值再复制一份存储到新的slot中
     * @return
     */
    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            // 若是加上下面的语句，则返回的就是下面的x而不是try语句中的x
            //return x;
        }
    }

    public String inc1() {
        StringBuilder x = new StringBuilder();
        try {
            // 在执行完这一行的时候有个pop，说明没有介绍append返回值
            x.append("1");
            return x.toString();
        } catch (Exception e) {
            x.append("2");
            return x.toString();
        } finally {
            x.append("3");
        }
    }

    public MyTest10 inc2() {
        MyTest10 x = new MyTest10();
        try {
           x.a = 1;
           return x;
        } catch (Exception e) {
            x.a = 2;
            return x;
        } finally {
            x.a = 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MyTest10().inc());
        System.out.println(new MyTest10().inc1());
        System.out.println(new MyTest10().inc2());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int[] a = new int[14];
        MyTest10[]myTest10s = new MyTest10[12];

    }

    @Override
    public String toString() {
        return "[a = " + a +"]";
    }

    // 查看内部类
    class InnerCls {
        char aChar;
    }
}

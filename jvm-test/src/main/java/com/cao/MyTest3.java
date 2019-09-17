package com.cao;
 
/** 助记符： javap -c com.cao.MyTest3
 ldc：表示将int，float，或是string类型的常量值从常量池中推至栈顶 
 bipush：表示将单个字节（-128~127）的常量值推至到栈顶 
 sipush：表示将一个短整型常量值（-32768~32767）推送到栈顶 
 iconst_1：表示将int类型的1推至到栈顶（-1~5）
 */
public class MyTest3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.str);
        System.out.println(MyParent3.s);
        System.out.println(MyParent3.s1);
        System.out.println(MyParent3.s2);
        System.out.println(MyParent3.s3);
        System.out.println(MyParent3.s4);
        System.out.println(MyParent3.i1);
        System.out.println(MyParent3.i2);
        System.out.println(MyParent3.i3);
        System.out.println(MyParent3.i4);
        System.out.println(MyParent3.i5);
        System.out.println(MyParent3.i6);
    }
}

class MyParent3 {
    public static final String str = "Hello world";
    public static final short s = 1;
    public static final short s1 = -127;
    public static final short s2 = -128;
    public static final short s3 = 127;
    public static final short s4 = 128;
    public static final short i1 = -2;
    public static final short i2 = -1;
    public static final short i3 = 5;
    public static final short i4 = 127;
    public static final short i5 = 128;
    public static final int i6 = 32769;
}

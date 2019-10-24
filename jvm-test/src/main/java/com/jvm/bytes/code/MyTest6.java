package com.jvm.bytes.code;

/**
 栈帧（stack frame）
 栈帧是一种用于帮助虚拟机执行方法调用与方法执行的数据结构。
 栈帧本身是一种数据结构，封装了方法的局部变量表、动态链接信息、方法的返回地址以及操作数栈等信息。

 动态链接引出了符号引用、直接引用

 有些符号引用是在类加载阶段或是第一次使用时就会转化为直接引用，这种转化叫做静态解析；
 另外一些符号引用则是在每次运行期转换为直接引用，这种转化叫做动态链接，这体现Java的多态性。
 */

/**
 1.invokeinterface：调用接口中的方法，实际上是在运行期决定的，决定到底调用实现该接口的那个对象的特定方法。
 2.invokestatic：调用静态方法。
 3.invokespecial：调用子类自己的私有方法、构造方法（<init>）以及父类的方法（super(),super.method()）
 4.invokevirtual：调用虚方法，运行期动态查找的方法。
 5.invokedynamic：动态调用方法

 静态解析的4种情形：
 1.静态方法
 2.父类方法
 3.构造方法
 4.私有方法（无法被重写）
 5.final方法

 以上5类方法被称作非虚方法，他们是在类加载阶段就可以将符号引用转化为直接引用的
 */
public class MyTest6 {
    /**
     invokevirtual
     Animal a = new Dog();
     a.sleep();
     a = new Cat();
     a.sleep();
     a = new Tiger();
     a.sleep();
     */

    public static void test() {
        System.out.println("static method");
    }

    public static void main(String[] args) {
        MyTest6.test();
        test();
    }
}

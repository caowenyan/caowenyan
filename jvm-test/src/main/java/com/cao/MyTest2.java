package com.cao;

/**
 常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中， 
 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化 
 注意：这里指的是将常量存到到了MyTest2的常量池中，之后MyTest2和MyParent2就没有任何联系了 
 甚至，我们可以将MyParent2的class文件删除（编译完之后运行之前可以删除）
 */
public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyChild2.finalStr);
    }
}
class MyParent2 {
    public static final String finalStr = "my parent 1 -- final";
    static {
        System.out.println("my parent static block");
    }
}
class MyChild2 extends MyParent2 {
    static {
        System.out.println("my parent static block");
    }
}
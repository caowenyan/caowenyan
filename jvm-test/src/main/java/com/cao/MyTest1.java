package com.cao;

/**
 对于静态字段来说，只有直接定义了该字段的类才会被初始化；（对于子类.父类的静态变量，静态变量是父类的，所以父类才会被初始化） 
 当一个类在初始化时，要求其父类全部都已经初始化完毕。(加载子类的静态变量时子类需要被初始化，导致父类也被初始化) 

 -XX:+TraceClassLoading，用于追踪类的加载信息并打印出来      
 -XX:+<option>，表示开启option操作 
 -XX:-<option>，表示关闭option操作 
 -XX:<option>=<value>，表示option选项的值设置为value 
 */
public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild1.parent);
//        System.out.println(MyChild1.son);
    }
}

class MyParent1 {
    public static String parent = "my parent1 static field";
    static {
        System.out.println("my parent static block");
    }
}
class MyChild1 extends MyParent1 {
    public static String son = "my parent1 static field";
    static {
        System.out.println("my parent static block");
    }
 }
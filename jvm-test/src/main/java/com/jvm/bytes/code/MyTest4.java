package com.jvm.bytes.code;

/**
  《重点：构造函数的外的优先于构造函数内的》
  注意构造函数的字节码：外面的所有的属性赋值和代码块都是按照顺序执行，都在构造函数之前
    所以拼接的顺序是属性赋值和代码块的代码顺序 + 构造方法的代码顺序
  初始化方法<cinit>中静态变量的赋值和静态代码块也是按照代码顺序进行排序的，和构造函数中的构造代码之前的顺序一致

  所以这两者都不能访问位于其后的属性变量，只能进行赋值
 */
public class MyTest4 {
    String str = "Hello world";

    public MyTest4() {
        str = "你好";
        x = 1000;
        System.out.println(str);
        System.out.println(x);
    }
    {
        str = "代码块";
        x = 100;
        System.out.println(str);
        // 报错，不可以打印
//        System.out.println(x);
    }
    private int x = 8;
    public static Integer in = 10;
    static {
        in = 20;
        sFin = "update string final";
        System.out.println(in);
        // 位于后面的无法实现打印，只能复制
//        System.out.println(sFin);
    }
    public static String sFin = "string final";

    public static void main(String[] args) {
        MyTest4 test4 = new MyTest4();

        test4.setX(20);

        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    private void test (String s) {
        System.out.println(this.str);
        synchronized (s) {
            System.out.println(s);
        }
        System.out.println(in);
    }
}

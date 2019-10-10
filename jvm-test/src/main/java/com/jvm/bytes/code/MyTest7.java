package com.jvm.bytes.code;

/**
 方法的静态分派。

 GrandPa pa = new Father();
 以上代码，pa的静态类型（声明类型）是Grandpa，而pa的实际类型（真正指向的类型）是Father。

 我们可以得出这样一个结论：变量的静态类型是不会发生变化的，而变量的实际类型则是可以变化的（多态的一种体现），实际类型是在运行期方可确定。
 */
public class MyTest7 {
    // 方法的重载，是一种静态的行为，编译期就可以完全确定的
    public void test(GrandPa pa) {
        System.out.println("GrandPa");
    }
    public void test(Father father) {
        System.out.println("Father");
    }
    public void test(Son pa) {
        System.out.println("Son");
    }

    public static void main(String[] args) {
        MyTest7 myTest7 = new MyTest7();
        GrandPa pa1 = new Father();
        myTest7.test(pa1);

        GrandPa pa2 = new Son();
        myTest7.test(pa2);
    }
}
class GrandPa {}
class Father extends GrandPa {}
class Son extends Father {}
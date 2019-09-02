package com.cao;
/**
 * 对于数组实例来说，其类型是由JVM早运行期动态生成的，表示为[L类的全限定名称
 * 对于这种形式，动态生成的类型，其父类是Object  对于数组来说，
 * JavaDoc经常将构成数组元素为Component，实际上就是将数组降低一个维度后的类型
 * <p>
 * 助记符：
 * anewarray：表示创建一个引用类型的（如类，接口，数组）数组，并将其引用值压入栈顶
 * newarray：表示创建一个指定的原始类型（如int，short，char等，不是装箱后的类型）的数组，并将其引用值压入栈顶
 */
public class MyTest5 {
    public static void main(String[] args) {
        MyParent5[] myParent5s = new MyParent5[1];
        System.out.println(myParent5s.getClass());
        System.out.println(myParent5s.getClass().getSuperclass());

        MyParent4[][] myParent5s1 = new MyParent4[1][];
        System.out.println(myParent5s1.getClass());
        System.out.println(myParent5s1.getClass().getSuperclass());
        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());

        Integer[] integers = new Integer[1];
        System.out.println(integers.getClass());
        System.out.println(integers.getClass().getSuperclass());
    }
}
class MyParent5 {
    static {
        System.out.println("static block");
    }
}
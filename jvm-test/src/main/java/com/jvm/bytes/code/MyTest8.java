package com.jvm.bytes.code;

/**
 方法的动态分派
 方法的动态分派涉及到一个重要概念：方法的接收者。
 invokevirtual字节码指令的多态查找流程
 比较方法重载(overload)与方法的重写(overwrite)，我们可以得到这样一个结论：
 方法重载是静态的，是编译器行为；方法重写是动态的，是运行期行为。

 针对于方法调用动态分派的过程，虚拟机会在类的方法区建立一个虚方法表的数据结构（virtual method table， vtable），
 针对于invokeinterface指令来说，虚拟机会建立一个叫做接口方法表的数据结构（interface method table，itable），
 */
/**
 * 新加入了构造器，主要是为了展示字节码中是否引入了有父类的构造函数，或者子类的其他构造函数
 */
public class MyTest8 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();
        Animal taidi = new TaidiDog();

        animal.name();
        dog.specialName("大黄");
        taidi.name();
        taidi.specialName("小黑");
        // 下面的打印要注意了，很诡异的情况
        TaidiDog.staticMethod();
        taidi.staticMethod();
        TaidiDog taidiDog = new TaidiDog();

    }
}
class Animal {
    public Animal() {
        System.out.println("animal init");
    }
    public void name() {
        System.out.println("Animal");
    }

    public void specialName(String name) {
        System.out.println("Animal special " + name);
    }

    public static void staticMethod() {
        System.out.println("Animal static");
    }
}
class Dog extends Animal {
    @Override
    public void name() {
        System.out.println("Dog");
    }

    @Override
    public void specialName(String name) {
        System.out.println("Dog special " + name);
    }

    public static void staticMethod() {
        System.out.println("Dog static");
    }
}
class TaidiDog extends Dog {
    public TaidiDog() {
        System.out.println("TaidiDog init");
    }
    public TaidiDog(String name) {
        this();
        System.out.println("TaidiDog init name");
    }
    @Override
    public void specialName(String name) {
        System.out.println("TaidiDog special " + name);
    }
}
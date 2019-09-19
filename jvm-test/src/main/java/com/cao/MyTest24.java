package com.cao;

/**
 一个获取的是当前线程的类加载器，一个获取的是加载这个对象的类加载器

 当前类加载器（Current ClassLoader）

 每个类都会使用自己的类加载器（即加载自身的类加载器）来去加载其它类（指的是所依赖的类），
 如果ClassX引用了ClassY，那么ClassX的类加载器就会去加载ClassY（前提是ClassY尚未被加载）

 线程上下文类加载器（Context ClassLoader）

 线程上下文类加载器是从JDK1.2开始引入的，类Thread中的getContextClassLoader()与setContextClassLoader(ClassLoader cl)
 分别用来获取和设置上下文类加载器。

 如果没有通过与setContextClassLoader(ClassLoader cl)进行设置的话，线程将继承其父线程的上下文类加载器。
 Java应用运行时的初始线程的上下文类加载器都是系统类加载器，在线程中运行的代码可以通过该类加载器来加载类与资源。

 线程上下文类加载器的重要性：

 SPI(Service Provider Interface)

 父ClassLoader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的classLoader加载的类，
 这就改变了父ClassLoader不能使用子ClassLoader或是其他没有直接父子关系的ClassLoader加载的类的情况，即改变了
 双亲委派模型。

 线程上下文类加载器就是当前线程的Current ClassLoader。

 在双亲委派模型下，类加载器是由下至上的，即下层的类加载器会委托上层进行加载。但是对于SPI来说，有些接口是Java核心库所提供的，
 而Java核心库是由启动类加载器来加载的，而这些接口的实现却来自于不同的jar包（厂商提供），Java的启动类加载器是不会加载其他
 来源的jar包，这些传统的双亲委拖模型就无法满足SPI的要求。而通过给当前线程设置上下文类加载器，就可以由设置的上下文类加载器
 来实现对于接口实现类的加载。
 */
public class MyTest24 {
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
    }
}

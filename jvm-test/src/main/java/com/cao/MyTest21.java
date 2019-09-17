package com.cao;

import java.lang.reflect.Method;

/**
 类加载器的双亲委派模型的好处：
    1.可以确保Java核心库的类型安全：所有的Java应用都至少会引用java.lang.Object，也就是说在运行期，java.lang.Object这个类
      会被加载到Java虚拟机中；如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的
      java.lang.Object类，而且这些类之间还是不兼容的，相互不可见（正是命名空间在发挥作用）。
      借助于双亲委派机制，Java核心类库中的类的加载工作都是由启动类加载器来统一完成，从而确保了Java应用所使用的都是同一版本的
      Java核心类库，他们之间是相互兼容的。
    2.可以确保Java核心类库所提供的类不会被自定义的类所替代
    3.不同的类加载器可以为相同名称（binary name）的类创建额外的的命名空间，相同名称的类可以共存在Java虚拟机中，只需要用不同的
      类加载器来加载他们即可。不同类加载器所加载的类之间是不兼容的，这就相当于在java虚拟机内部创建了一个又一个相互隔离的
      java类空间，这类技术在很多框架中都得到了实际应用。

 若是将MyPerson移动到桌面的文件夹中，则会出现的问题（经典的A不能转化为A），不同类加载器加载的类不同：
 findClass invoke: com.cao.MyPerson
 class loader name: loader1
 findClass invoke: com.cao.MyPerson
 class loader name: loader2
 false
 Exception in thread "main" java.lang.reflect.InvocationTargetException
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at com.cao.MyTest20.main(MyTest20.java:28)
 Caused by: java.lang.ClassCastException: com.cao.MyPerson cannot be cast to com.cao.MyPerson
 at com.cao.MyPerson.setMyPerson(MyPerson.java:7)
 ... 5 more
 */
public class MyTest21 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz1 = loader1.loadClass("com.cao.MyPerson");

        MyClassLoader loader2 = new MyClassLoader("loader2");
        loader2.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz2 = loader2.loadClass("com.cao.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        // object1 对象，object2是参数
        method.invoke(object1, object2);
    }
}

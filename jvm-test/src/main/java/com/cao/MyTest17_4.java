package com.cao;

/**
前提：
 将MySample_1放置到桌面的文件夹中，MyCat_1还在classpath下，
结论：
 通过自定义类加载器加载MySample_1，然后加载MyCat_1，有类加载器的可见性原理是可以的，
 通过系统类加载器加载MyCat_1，然后想要通过自定义类加载器加载MySample_1，但是不是同一个命名空间，失败
结果：
 findClass invoke: com.cao.MySample_1
 class loader name: loader1
 class: 1639705018
 mySample static block
 mySample is loaded by: [loader1]
 myCat static block
 myCat is loaded by: sun.misc.Launcher$AppClassLoader@135fbaa4
 Exception in thread "main" java.lang.NoClassDefFoundError: com/cao/MySample_1
 at com.cao.MyCat_1.<init>(MyCat_1.java:9)
 at com.cao.MySample_1.<init>(MySample_1.java:9)
 at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
 at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
 at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
 at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
 at java.lang.Class.newInstance(Class.java:442)
 at com.cao.MyTest17_4.main(MyTest17_4.java:26)
 Caused by: java.lang.ClassNotFoundException: com.cao.MySample_1
 at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
 at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
 ... 8 more
 */
public class MyTest17_4 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader("loader1");
        loader.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz = loader.loadClass("com.cao.MySample_1");
        System.out.println("class: " + clazz.hashCode());

        Object object = clazz.newInstance();
    }
}

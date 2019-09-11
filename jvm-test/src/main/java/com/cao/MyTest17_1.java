package com.cao;

/**
前提：
 将MySample和MyCat放置到桌面的文件夹中，所以会出现有自定义加载器加载类
结果是：
 findClass invoke: com.cao.MySample
 class loader name: loader1
 class:1639705018
 mySample static block
 mySample is loaded by: [loader1]
 findClass invoke: com.cao.MyCat
 class loader name: loader1
 myCat static block
 myCat is loaded by: [loader1]
 */
public class MyTest17_1 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader("loader1");
        loader.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz = loader.loadClass("com.cao.MySample");
        System.out.println("class: " + clazz.hashCode());

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，既没有MyCat进行主动使用，这里就不回家再MyCat Class
        Object object = clazz.newInstance();
    }
}

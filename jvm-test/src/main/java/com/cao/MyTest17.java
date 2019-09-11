package com.cao;

/**
 * classLoader.loadClass()只是加载类
 * 本系列关于命名空间的重要说明：
 *  1.子加载器所加载的类能够访问父加载器所加载的类
 *  2.父加载器所加载的类无法访问子加载器所加载的类
 */
public class MyTest17 {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader("loader1");
        Class<?> clazz = loader.loadClass("com.cao.MySample");
        System.out.println("class:" + clazz.hashCode());

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，既没有MyCat进行主动使用，这里就不回家再MyCat Class
        Object object = clazz.newInstance();
    }
}

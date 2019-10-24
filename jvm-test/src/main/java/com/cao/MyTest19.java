package com.cao;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 1. 正常情况下AESKeyGenerator类加载器是扩展类加载器，MyTest19是系统类加载器
 2. 修改扩展类加载器的家在路径，如java -Djava.ext.dirs=./ com.cao.MyTest19，
    由于扩展类加载器指向了本地空间，所以导致加载不到特定路径的class
 Exception in thread "main" java.lang.NoClassDefFoundError: com/sun/crypto/provider/AESKeyGenerator
 at com.cao.MyTest19.main(MyTest19.java:12)
 Caused by: java.lang.ClassNotFoundException: com.sun.crypto.provider.AESKeyGenerator
 at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
 at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
 ... 1 more

 */
public class MyTest19 {
    public static void main(String[] args) throws Exception {
        System.out.println(MyTest19.class.getClassLoader());
        AESKeyGenerator generator = new AESKeyGenerator();
        System.out.println(generator.getClass().getClassLoader());
    }
}

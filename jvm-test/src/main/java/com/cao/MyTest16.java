package com.cao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 若是通过系统类加载器进行加载，则执行gc不会回收MyTest1类，
 若是通过用户自定义类加载器加载MyTest1，执行gc则会回收MyTest1
 回收前提：必须类加载器，类，实例都置为null才可以回收

 方法：下面两段选择其中一个就可以实现回收
 */
public class MyTest16 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyTest16(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader classLoader, String classLoaderName) {
        super(classLoader);
        this.classLoaderName = classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        MyTest16 classLoader = new MyTest16("loader1");
        classLoader.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz = classLoader.loadClass("com.cao.MyTest1");
        Object object = clazz.newInstance();
        System.out.println("class hashcode: " + clazz.hashCode());
        System.out.println("class instance: " + object);
        System.out.println("class loader name: " + object.getClass().getClassLoader());
        System.out.println();

        // 1。 直接置为null回收
        classLoader = null;
        clazz = null;
        object = null;
        System.gc();

        classLoader = new MyTest16("loader1");
        classLoader.setPath("/Users/caowenyan/Desktop/");
        clazz = classLoader.loadClass("com.cao.MyTest1");
        object = clazz.newInstance();
        System.out.println("class hashcode: " + clazz.hashCode());
        System.out.println("class instance: " + object);
        System.out.println("class loader name: " + object.getClass().getClassLoader());
        System.out.println();

        // 2. 前面的第一轮都重新赋值了，通过可达性分析算法，可以直接被回收，所以也表名类被卸载
//        System.gc();
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoke: " + className);
        System.out.println("class loader name: " + this.classLoaderName);

        byte[] data = loadClassData(className);
        return super.defineClass(className, data,  0, data.length);
    }

    private byte[]loadClassData(String className) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        byte[] data = null;
        try {
            className = className.replace(".", "/");
            in = new FileInputStream(new File(getPath() + className + fileExtension));
            out = new ByteArrayOutputStream();

            int ch = 0;

            while ((ch = in.read()) != -1) {
                out.write(ch);
            }
            data = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {in.close(); } catch (Exception ex) {ex.printStackTrace();}
            }
            if (out != null) {
                try {out.close(); } catch (Exception ex) {ex.printStackTrace();}
            }
        }
        return data;
    }

    @Override
    public String toString() {
        return "[" + classLoaderName + "]";
    }

}

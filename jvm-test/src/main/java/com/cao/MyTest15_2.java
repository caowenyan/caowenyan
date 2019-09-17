package com.cao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 本例子前提是删除了classpath下的MyTest1.class文件，
 主要是为了系统类加载器加载不到MyTest1类，这样才可以保证用户自定义加载器加载到MyTest1类

 findClass invoke: com.cao.MyTest1
 class loader name: loader1
 class hashcode: 723074861
 class instance: com.cao.MyTest1@355da254
 class loader name: [loader1]
 */
public class MyTest15_2 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyTest15_2(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest15_2(ClassLoader classLoader, String classLoaderName) {
        super(classLoader);
        this.classLoaderName = classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        MyTest15_2 classLoader1 = new MyTest15_2("loader1");
        classLoader1.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz1 = classLoader1.loadClass("com.cao.MyTest1");
        Object object1 = clazz1.newInstance();
        System.out.println("class hashcode: " + clazz1.hashCode());
        System.out.println("class instance: " + object1);
        System.out.println("class loader name: " + object1.getClass().getClassLoader());
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

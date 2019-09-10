package com.cao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 和上一个例子不同的地方是主动设置了父类加载器，这样第二个类加载器就会用第一个类加载器加载过的类，不会重新加载
 */
public class MyTest15_4 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyTest15_4(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest15_4(ClassLoader classLoader, String classLoaderName) {
        super(classLoader);
        this.classLoaderName = classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        MyTest15_4 classLoader1 = new MyTest15_4("loader1");
        classLoader1.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz1 = classLoader1.loadClass("com.cao.MyTest1");
        Object object1 = clazz1.newInstance();
        System.out.println("class hashcode: " + clazz1.hashCode());
        System.out.println("class instance: " + object1);
        System.out.println("class loader name: " + object1.getClass().getClassLoader());
        System.out.println();

        MyTest15_4 classLoader2 = new MyTest15_4(classLoader1, "loader2");
        classLoader2.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz2 = classLoader2.loadClass("com.cao.MyTest1");
        Object object2 = clazz2.newInstance();
        System.out.println("class hashcode: " + clazz2.hashCode());
        System.out.println("class instance: " + object2);
        System.out.println("class loader name: " + object2.getClass().getClassLoader());
        System.out.println();

        MyTest15_4 classLoader3 = new MyTest15_4("loader3");
        classLoader3.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz3 = classLoader3.loadClass("com.cao.MyTest1");
        Object object3 = clazz3.newInstance();
        System.out.println("class hashcode: " + clazz3.hashCode());
        System.out.println("class instance: " + object3);
        System.out.println("class loader name: " + object3.getClass().getClassLoader());
        System.out.println();

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

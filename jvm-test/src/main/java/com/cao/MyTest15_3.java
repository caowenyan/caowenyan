package com.cao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 此例子主要是因为不同的类加载器的命名空间不同导致的问题，
 若是相同的命名空间只会加载一次，比如通过系统类加载器加载
 若是不同的命名空间会加载多次，比如删除target下的MyTest1.class通过自定义类类加载器加载
 */
public class MyTest15_3 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtension = ".class";

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyTest15_3(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest15_3(ClassLoader classLoader, String classLoaderName) {
        super(classLoader);
        this.classLoaderName = classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        MyTest15_3 classLoader1 = new MyTest15_3("loader1");
        classLoader1.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz1 = classLoader1.loadClass("com.cao.MyTest1");
        Object object1 = clazz1.newInstance();
        System.out.println("class hashcode: " + clazz1.hashCode());
        System.out.println("class instance: " + object1);
        System.out.println("class loader name: " + object1.getClass().getClassLoader());
        System.out.println();

        MyTest15_3 classLoader2 = new MyTest15_3("loader2");
        classLoader2.setPath("/Users/caowenyan/Desktop/");
        Class<?> clazz2 = classLoader2.loadClass("com.cao.MyTest1");
        Object object2 = clazz2.newInstance();
        System.out.println("class hashcode: " + clazz2.hashCode());
        System.out.println("class instance: " + object2);
        System.out.println("class loader name: " + object2.getClass().getClassLoader());
        System.out.println();

        MyTest15_3 classLoader3 = new MyTest15_3("loader3");
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

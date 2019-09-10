package com.cao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 开始看这个例子的时候很怀疑，这个如何读取文件的，都是错的，后来才知道这个类加载器根本没用使用
 因为父类加载器完全可以获取到当前类的实例，根据双亲委派模型所以不是本类加载器加载的
 */
public class MyTest15 extends ClassLoader {
    private String classLoaderName;

    private final String fileExtension = ".class";

    public MyTest15 (String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest15 (ClassLoader classLoader, String classLoaderName) {
        super(classLoader);
        this.classLoaderName = classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        MyTest15 classLoader1 = new MyTest15("loader1");
        Class<?> clazz1 = classLoader1.loadClass(MyTest1.class.getName());
        Object object1 = clazz1.newInstance();
        System.out.println(object1);
        System.out.println(object1.getClass().getClassLoader());
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);
        return super.defineClass(name, data,  0, data.length);
    }

    private byte[]loadClassData(String className) {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        byte[] data = null;
        try {
            in = new FileInputStream(new File(className));
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

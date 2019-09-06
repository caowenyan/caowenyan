package com.cao;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest13 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String resource = "com/cao/MyTest12.class";

        Enumeration<URL> urls = classLoader.getResources(resource);

        while(urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        Class<?> clazz = MyTest13.class;
        System.out.println(clazz.getClassLoader());

        clazz = String.class;
        System.out.println(clazz.getClassLoader());
    }
}

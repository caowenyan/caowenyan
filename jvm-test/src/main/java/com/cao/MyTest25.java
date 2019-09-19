package com.cao;

public class MyTest25 implements Runnable {
    private Thread thread;

    public MyTest25() {
       thread = new Thread(this);
       thread.start();
    }

    @Override
    public void run() {
        ClassLoader loader = this.thread.getContextClassLoader();

        // 这个地方设不设置都一样，因为本来的线程类的上下文类加载器就是系统类加载器
        this.thread.setContextClassLoader(loader);

        System.out.println("class loader: " + loader.getClass());
        System.out.println("parent loader: " + loader.getParent().getClass());
    }

    public static void main(String[] args) {
        new MyTest25();
    }
}

package com.cao;

/**
 单例模式
 */
public class Test02 {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
        System.out.println(Singleton2_1.getInstance() == Singleton2_1.getInstance());
        System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
        System.out.println(Singleton4.getInstance() == Singleton4.getInstance());
    }

    /**
     * 恶汉模式, 线程安全
     */
    private static class Singleton {
        private Singleton() {};
        private static Singleton instance = new Singleton();
        public static Singleton getInstance () {
            return instance;
        }
    }
    /**
     * 懒汉模式，一个对象字节码只能获取一次
     */
    private static class Singleton2 {
        private Singleton2() {};
        // volatile 主要是为了防止初始化对象的时候重排序导致逃逸
        private static volatile Singleton2 instance;
        public static Singleton2 getInstance () {
            if (instance == null) {
                synchronized (Singleton2.class) {
                    if (instance == null) {
                        instance = new Singleton2();
                    }
                }
            }
            return instance;
        }
    }
    /**
     * 饿汉式，变种，线程安全，感觉这种更厉害，毕竟利用了类加载只能加载一次的原则而且延迟加载，棒
     */
    public static class Singleton2_1 {
        private Singleton2_1() {}
        private static Singleton2_1 instance = null;
        static {
            instance = new Singleton2_1();
        }
        public static Singleton2_1 getInstance() {
            return instance;
        }
    }

    /**
     * 静态内部类
     */
    private static class Singleton3 {
        private Singleton3() {};
        public static Singleton3 getInstance () {
            return InnerClass.instance;
        }
        private static class InnerClass {
            private static Singleton3 instance = new Singleton3();
        }
    }
    /**
     * 枚举
     */
    private static enum Singleton4 {
        INSTANCE;
        private Singleton4() {};
        public static Singleton4 getInstance () {
            return INSTANCE;
        }
    }
}

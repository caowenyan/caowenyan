package com.cao;

/**
 4种情况下的执行结果的分析
 1.默认情况都是由系统类加载器进行加载的
 2.若是在类路径下执行java -Djava.ext.dirs=./ com.cao.MyTest22，发现还是由系统类加载器进行加载的
    原因是因为扩展类加载器有点特殊的地方，他加载的是jar包中的类，而不是class文件
 3.首先将MyTest1加入到jar中，执行：jar cvf test.jar com/cao/MyTest1.class，然后修改扩展类空间，执行
 java -Djava.ext.dirs=./ com.cao.MyTest22，发现MyTest1的类加载器是扩展类加载器
 4.若是现在执行java -Djava.ext.dirs=/ com.cao.MyTest22,发现还都是系统类加载器，因为扩展类加载器加载不到
 */
public class MyTest22 {
    static {
        System.out.println("MyTest22 static block");
    }
    public static void main(String[] args) {
        System.out.println(MyTest22.class.getClassLoader());
        System.out.println(MyTest1.class.getClassLoader());
    }
}

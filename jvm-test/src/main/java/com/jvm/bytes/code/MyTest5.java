package com.jvm.bytes.code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;

/**
 对于Java类中的每一个实例方法(非static方法)，其在编译后所生成的字节码当中，方法参数的数量总是会比源代码中方法
 参数的数量多一个(this)，它位于方法的第一个参数位置处；这样，我们就可以在Java的实例方法中使用this来访问当前
 对象的属性以及其他方法。

 这个操作是在编译器间完成的，即由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问，接下来在运行期间，
 由JVM在调用实例方法时，自动向实例方法传入该this参数。所以，在实例方法的局部变量表中，至少会有一个指向当前对象的局部变量。
 */

/**
 Java字节码对于异常的处理方式：

 1.统一采用异常表的方式来对异常进行处理。
 2.在jdk1.4.2之前的版本中，并不是使用异常表的方式来对异常进行处理的，而是采用特定的指令方式。
 3.当异常处理存在finally语句块时，现代化的JVM才去的处理方式是将finally语句块的字节码拼接到每一个catch块后面，
 换句话说，程序中存在多少个catch块（包含隐含的异常），就会在每一个catch块后面重复多少个finally语句块的字节码。
 */
public class MyTest5 {
    public void test() throws NullPointerException, Exception, FileNotFoundException {
        try {
            InputStream inputStream = new FileInputStream("a.txt");

            ServerSocket serverSocket = new ServerSocket(9090);
            serverSocket.accept();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (Exception e) {
            System.out.println("Exception");
        } finally {
            System.out.println("执行完毕了");
        }
    }
}

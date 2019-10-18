package com.jvm.bytes.code;

/**
 现代JVM在执行Java代码的时候，通常都会将解释执行与编译执行两者结合起来进行。

 所谓解释执行，就是通过解释器来来读取字节码，遇到相应的指令就去执行该执行。
 所谓编译执行，就是通过即时编译器（Just In Time，JIT）将字节码转化为本地机器码来执行；
 现代JVM会根据代码热点来生成相应的本地机器码。

 基于栈的指令集与基于寄存器的指令集之间的关系：
 1.JVM执行指令时所采取的方式是基于栈的指令集。
 2.基于栈的指令集主要的操作有入栈和出栈两种。
 3.基于栈的指令集的优点在于他可以在不同平台之间移植，而基于寄存器的指令集是与硬件架构紧密关联的，无法做到可移植。
 4.基于栈的指令集的缺点在于完成相同的操作，指令数量通常要比基于寄存器的指令集数量要多；
 基于栈的指令集在内存中完成操作的，而基于寄存器的指令集是直接由CPU来执行的，他是在高速缓冲区中进行执行的，速度要快很多。
 虽然虚拟机可以采用一些优化手段，但实体来说，基于栈的指令集的执行速度要慢一些。
 */

/**
 分析一下方法中code的参数
    stack：操作数栈的最大深度
    locals：局部变量表的最大数量
    args_size：参数的数量
 字节码分析
    iconst_n：将int类型的n压到操作数栈顶，等同于bitpush n
    istore_n:把操作数栈顶的元素压入到局部变量表索引为n的位置
    bipush n：把短整型n压到操作数栈顶
    ldc value：把value压到操作数的栈顶
    istore n：把操作数栈顶的元素移动到局部变量表索引为n的位置，等同于iload n
    iload_n：把局部变量表中索引为n的值压入到操作数栈顶
    iadd：将操作数栈顶的元素弹出两个相加，然后将结果压入栈顶

    pop2：将栈顶的一个（占8个字节）或者2个元素弹出
 */

/**
 现在把test方法复制一份修改为long类型，变化
 stack由2->4
 locals由6->11
 LocalVariableTable中slot的值long类型的也增加了1.

 由此可见类型的重要性。
 */
public class MyTest9 {
    public int test() {
        int a = 1;
        int b = 10;
        int c = 100;
        int d = 100000;
        int result = (a + b - c) * d;
        return result;
    }

    public long testLong() {
        long a = 1;
        long b = 10;
        long c = 100;
        long d = 100000;
        long result = (a + b - c) * d;
        return result;
    }

    public static void main(String[] args) {
        String s = "Fdsfsdfsfs";
        Integer i = 123;
        Integer i01 = 1023;
        // 执行完test之后会把返回值压入到栈顶，但是此次没有保存，所以多了pop命令将返回值出栈
        MyTest9 testNull;
        testNull = null;
        new MyTest9().test();
        new MyTest9().testLong();
        MyTest9 myTest9 = new MyTest9();
        int i1 = myTest9.test();
        long l1 = myTest9.testLong();
        System.out.println(i1);
        System.out.println(l1);
    }
}

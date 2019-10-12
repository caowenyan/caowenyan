package com.cao;

/**
 *思路：把一个整数减去1之后再和原来的整数做与运算，得到的结果相当于是把整数的二进制表示中的最右边的一个1变成0
 * 举一反三：
 *  1.一个整数是不是2的整数次方：若是2的整数次方，那么他的二进制表示中只有一位是1.
 *  2.输入两个整数m、n，计算需要改变m的二进制中的多少位可以得到n：先异或在求二进制中1的个数。
 */
public class Test10 {
    /**
     * 请实现一个函数， 输入一个整数，输出该数二进制表示中1的个数。
     * 例如把9表示成二进制是1001 ，有2位是1. 因此如果输入9，该出2。
     *
     * @param n 待的数字
     * @return 数字中二进制表表的1的数目
     */
    public static int numberOfOne(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n&1;
            n >>>= 1;
        }
        return sum;
    }

    /**
     * 摘抄的例子，通过整数位数控制
     */
    public static int numberOfOne2(int n) {
        // 记录数字中1的位数
        int result = 0;

        // JAVA语言规范中，int整形占四个字节，总计32位
        // 对每一个位置与1进行求与操作，再累加就可以求出当前数字的表示是多少位1
        for (int i = 0; i < 32; i++) {
            result += (n & 1);
            n >>>= 1;
        }

        // 返回求得的结果
        return result;
    }

    /**
     *【这种方法的效率更高】
     */
    public static int numberOfOne1(int n) {
        // 记录数字中1的位数
        int result = 0;

        // 数字的二进制表示中有多少个1就进行多少次操作
        while (n != 0) {
            result++;
            // 从最右边的1开始，每一次操作都使n的最右的一个1变成了0，
            // 即使是符号位也会进行操作。
            n = (n - 1) & n;
        }

        // 返回求得的结果
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOfOne(0) == 0);
        System.out.println(numberOfOne(1) == 1);
        System.out.println(numberOfOne(-1) == 32);
        System.out.println(numberOfOne(Integer.MAX_VALUE) == 31);
        System.out.println(numberOfOne(Integer.MIN_VALUE) == 1);
        System.out.println();

        System.out.println(getLineNum("A"));
        System.out.println(getLineNum("B"));
        System.out.println(getLineNum("Z"));
        System.out.println();
        System.out.println(getLineNum("AA"));
        System.out.println(getLineNum("AB"));
        System.out.println(getLineNum("AZ"));
        System.out.println();
        System.out.println(getLineNum("BA"));
        System.out.println();
        System.out.println(getLineNum("AAA"));
    }

    static int con = 26;
    static char fir = 'A';
    /**
     * 根据输入的字母输出列号
     */
    public static int getLineNum(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum = sum * con + c - fir + 1;
        }
        return sum;
    }
}

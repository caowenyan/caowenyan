package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月11日 14:28
 */
public class Test32 {
    /**
     * 题目：输入一个整数n求从1 到n这n个整数的十进制表示中1 出现的次数。
     * 最简单的做法就是把所有的都遍历一遍求得1的个数
     * @param n 最大的数字
     * @return 1-n中，各个数位1出现的次数
     */
    public static int numberOf1Between1AndN1(int n) {
        if (n <= 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 1 ; i <= n ; i++) {
            sum += numOf1With(i);
        }
        return sum;
    }

    /**
     * n中1的个数
     */
    private static int numOf1With(int n) {
        int sum = 0;
        while (n > 0) {
            if (n % 10 == 1) {
                sum ++;
            }
            n /= 10;
        }
        return sum;
    }

    public static int numberOf1Between1AndN(int n) {
        if (n <= 0) {
            return 0;
        }

        String value = n + "";
        int[] numbers = new int[value.length()];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = value.charAt(i) - '0';
        }

        return numberOf1(numbers, 0);
    }

    /**
     * 求0-numbers表的数字中的1的个数
     * 这个才是本题的亮点,把数组分为两部分，从第一位分割，如abcde分割为1~bcde,bcde+1~abcde
     * @param numbers 数字，如{1, 2, 3, 4, 5}表示数字12345
     * @param curIdx  当前处理的位置
     * @return 1的个数
     */
    private static int numberOf1(int[] numbers, int curIdx) {
        if (numbers == null || curIdx >= numbers.length || curIdx < 0) {
            return 0;
        }
        // 首先处理的数字
        int first = numbers[curIdx];
        // 处理的长度
        int length = numbers.length - curIdx;
        if (length == 1 && first == 0) {
            return 0;
        }
        if (length == 1 && first > 0) {
            return 1;
        }
        int sum = 0;
        // 一共三种情况，1是一种，大于1一种，0则没有
        // 如果第一位是1，如[1,2,3,4,5]，则第一位的1的个数是从[2345~12345]，开始就忘记了还有10000，少算了1
        if (first == 1) {
            sum += atoi(numbers, curIdx + 1) + 1;
        } else if (first >1) {
            // 如果第一位大于1，则如[2,1,3,4,5],则第一位的1的个数是从[1345~21345]
            sum += powerBase10(length - 1);
        }
        // 计算从[1345~21345]除了第一位之外的1的个数（这个地方需要注意，后面-前面=first*10 + 前面）
        // note： 这个地方推理的若不明白则很难理解下面一行代码的意思
        /**
         从 1 至 10，在它们的个位数中，数字1出现了 1 次。
         从 1 至 100，在它们的十位数中，数字1出现了 10 次。
         从 1 至 1000，在它们的百位数中，数字1出现了 100 次。
         从 1 至 2000，在它们的百位数中，数字1出现了 100 * 2 次。

         因此
         从 1 至 10，个位数中数字1出现了 1 次。
         从 1 至 100，十位数以下数字1出现了 10 + 1*10 == 10*2次。
         从 1 至 1000，百位数以下数字1出现了 100 + 10*(10 + 1*10) == 100 * 3 次。
         从 1 至 2000，百位数以下数字1出现了 2 *（100 + 10*(10 + 1*10)） == 2 * 100 * 3 次。
         */
        sum += first * (length - 1) * powerBase10(length - 2);
        // 计算从[1,1345]中1的个数
        sum += numberOf1(numbers, curIdx + 1);
        return sum;
    }

    /**
     * 将数字数组转换成数值，如{1, 2, 3, 4, 5}，i = 2，结果是345
     * @param numbers 数组
     * @param i 开始黑气的位置
     * @return 转换结果
     */
    private static int atoi(int[] numbers, int i) {
        int result = 0;
        for (int j = i; j < numbers.length; j++) {
            result = (result * 10 + numbers[j]);
        }
        return result;
    }

    /**
     * 求10的n次方，假定n不为负数
     * @param n 幂，非负数
     * @return 10的n次方
     */
    private static int powerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1Between1AndN(1) == 1);
        System.out.println(numberOf1Between1AndN(5) == 1);
        System.out.println(numberOf1Between1AndN(10) == 2);
        System.out.println(numberOf1Between1AndN(55) == 16);
        System.out.println(numberOf1Between1AndN(99) == 20);
        System.out.println(numberOf1Between1AndN(10000) == 4001);
        System.out.println(numberOf1Between1AndN(21345) == 18821);
        System.out.println(numberOf1Between1AndN(21346) == 18822);
        System.out.println(numberOf1Between1AndN(0) == 0);
    }
}

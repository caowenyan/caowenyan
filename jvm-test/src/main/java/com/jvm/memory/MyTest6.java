package com.jvm.memory;

import java.util.Arrays;
import java.util.List;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 装箱、变长参数、拆箱、遍历
 * @date 2019年10月23日 17:29
 */
public class MyTest6 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}

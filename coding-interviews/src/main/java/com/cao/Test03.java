package com.cao;

import org.junit.Assert;

/**
 查找数组中是否存在某个元素
 */
public class Test03 {
    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p/>
     * 这个地方需要注意下：为何选取的是右上角的元素，主要是因为他有两个范围，大于他的去下面找，小于他的去左边找
     * 规律：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束：
     * 如果该数字大于要查找的数字，剔除这个数字所在的列：如果该数字小于要查找的数字，剔除这个数字所在的行。
     * 也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除）行或者一列，这样每一步都可以缩小
     * 查找的范围，直到找到要查找的数字，或者查找范围为空。
     *
     * @param matrix 待查找的数组
     * @param number 要查找的数
     * @return 查找结果，true找到，false没有找到
     */
    public static boolean find(int[][] matrix, int number) {
        boolean exist = false;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return exist;
        }
        // 要查找的最小的行号
        int row = 0;
        // 要查找的最大的列表
        int line = matrix[0].length - 1;
        while (row < matrix.length && line >= 0) {
            int value = matrix[row][line];
            if (value == number) {
                exist = true;
                break;
            }
            // 若是比较的数字大于当前值，说明需要下移才能找到，否则需要左移
            if (number > value) {
                row ++;
            } else {
                line --;
            }
        }
        return exist;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        Assert.assertEquals(find(matrix, 7), true);
        Assert.assertEquals(find(matrix, 5), false);
        Assert.assertEquals(find(matrix, 1), true);
        Assert.assertEquals(find(matrix, 15), true);
        Assert.assertEquals(find(matrix, 0), false);
        Assert.assertEquals(find(matrix, 16), false);
        Assert.assertEquals(find(null, 16), false);
    }
}

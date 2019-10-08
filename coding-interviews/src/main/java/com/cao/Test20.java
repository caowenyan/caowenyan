package com.cao;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年09月27日 18:36
 */
public class Test20 {
    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印每一个数字
     *
     * @param numbers 输入的二维数组，二维数组必须是N*M的，否则分出错
     */
    public static void printMatrixClockWisely(int[][] numbers) {
        if (numbers == null || numbers[0].length == 0) {
            return;
        }
        printNMatrix(numbers, 0, numbers.length, numbers[0].length);
    }

    /**
     * 打印第n圈的二维数组
     * @param numbers
     * @param step 第n圈，从0开始
     * @param rows 行号,x轴的最大值
     * @param lines 列号，y轴的最大值
     */
    private static void printNMatrix(int[][] numbers, int step, int rows, int lines) {
        if ((step << 1) >= lines || (step << 1) >= rows) {
            return;
        }
        // 从左到右，x是固定值：第几圈，y是从step到line-step
        for (int y = step; y < lines - step ; y ++) {
            System.out.print(numbers[step][y] + " ");
        }
        // 从上到下，x是变量：从step+1到最后行 - step
        // rows - 1 - step ： 打印x轴的最后一行
        if (rows - 1 - step > step) {
            for (int x = step + 1; x < rows - step; x++) {
                System.out.print(numbers[x][lines - step - 1] + " ");
            }
        }
        // 从右到左
        if (rows - 1 - step > step && lines - 1 - step > step) {
            for (int y = lines - step - 2; y >= step; y--) {
                System.out.print(numbers[rows - step - 1][y] + " ");
            }
        }
        // 从下到上，
        if (lines - 1 - step > step && rows - 2 - step > step) {
            for (int x = rows - step - 2; x > step; x--) {
                System.out.print(numbers[x][step] + " ");
            }
        }
        printNMatrix(numbers, step + 1, rows, lines);
    }
    public static void main(String[] args) {
        int[][] numbers = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };
        printMatrixClockWisely(numbers);
        System.out.println();

        int[][] numbers2 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {22, 23, 24, 25, 26, 27, 28, 9},
                {21, 36, 37, 38, 39, 40, 29, 10},
                {20, 35, 34, 33, 32, 31, 30, 11},
                {19, 18, 17, 16, 15, 14, 13, 12},

        };
        printMatrixClockWisely(numbers2);
        System.out.println();


        int[][] numbers3 = {
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        printMatrixClockWisely(numbers3);
        System.out.println();

        int[][] numbers4 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {16, 15, 14, 13, 12, 11, 10, 9}
        };
        printMatrixClockWisely(numbers4);
        System.out.println();


        int[][] numbers5 = {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
        printMatrixClockWisely(numbers5);
        System.out.println();

        int[][] numbers6 = {
                {0, 1},
                {15, 2},
                {14, 3},
                {13, 4},
                {12, 5},
                {11, 6},
                {10, 7},
                {9, 8}
        };
        printMatrixClockWisely(numbers6);
        System.out.println();


        int[][] numbers7 = {
                {1, 2},
                {4, 3}
        };
        printMatrixClockWisely(numbers7);
        System.out.println();

        int[][] numbers8 = {
                {1}
        };
        printMatrixClockWisely(numbers8);
        System.out.println();

        // 0个元素的数组
        printMatrixClockWisely(new int[][]{{}});
        // 空数组
        printMatrixClockWisely(null);
    }
}

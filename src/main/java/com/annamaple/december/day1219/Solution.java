package com.annamaple.december.day1219;

class Solution {
    // 构建辅助数组
    // time: O(n^2) space: O(n^2) n为matrix.length
    public void rotate1(int[][] matrix) {
        int n = matrix.length;;
        int[][] matrixNew = new int[matrix.length][matrix.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixNew[j][n - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrixNew[i], 0, matrix[i], 0, n);
        }
    }

    // 旋转
    // time: O(n^2) space: O(1)
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }

    }

    // 顺时针旋转90度 = 先水平翻转 在对称翻转
    // time: O(n^2) space: O(1)
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix[i][j] + matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = matrix[i][j] - matrix[n - 1 - i][j];
                matrix[i][j] = matrix[i][j] - matrix[n - 1 - i][j];
            }
        }
        // 主对称线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                matrix[i][j] = matrix[j][i] + matrix[i][j];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
    }
}

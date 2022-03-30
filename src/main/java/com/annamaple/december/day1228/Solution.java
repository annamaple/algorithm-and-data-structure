package com.annamaple.december.day1228;

import java.util.Arrays;

public class Solution {


    // 动态规划 二维数组
    public int maxProfitReference1(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        k = Math.min(k, prices.length / 2); // 交易次数最大不能超过数组的长度除以二
        // buy[i][j] 表示对于数组 prices[0..i] 中的价格而言，恰好进行j笔交易
        // sell[i][j] 表示对于数组 prices[0...i] 中的价格而言, 恰好进行j笔交易
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];
        // 规定当出售股票时才算一笔交易完成, 因此当在prices[0] 时 buy[0] sell[0] 的交易次数都为 0,
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        // 且buy[0][1...k]、sell[0][1...k] 为不合法的值, 用一个很小的数表示
        for (int i = 1; i <= k; i++) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }
        // 以prices[1...i] 交易次数[1...k]进行状态转换
        for (int i = 1; i < n; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        // 返回prices[i] 时, 各个交易次数中的最大值
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    // 动态规划 1维数组
    public int maxProfitReference2(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        k = Math.min(k, prices.length / 2);
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i <= k; i++) {
            buy[i] = sell[i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < prices.length; i++) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell).max().getAsInt();
    }
}

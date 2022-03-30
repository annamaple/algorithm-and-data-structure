package com.annamaple.december.day1217;

public class Solution {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int sell = -fee;
        int buy = -prices[0];
        for (int p: prices) {
            sell = Math.max(sell, buy + p - fee);
            buy = Math.max(buy, sell - p);
        }
        return sell;
    }
}

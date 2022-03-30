package com.annamaple.december.day1221;

public class Solution {

    // my answer 动态规划 后一个最低 一定是（前一个最低或前两个最低） + cost[i]
    // time: O(n)   space: O(n)
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 1) {
            return 0;
        }
        int len = cost.length;
        int[] puy = new int[len];
        puy[0] = cost[0];
        puy[1] = cost[1];
        for (int i = 2; i < len; i++) {
            puy[i] = cost[i] + Math.min(puy[i - 1], puy[i - 2]);
        }
        return Math.min(puy[len - 1], puy[len - 2]);
    }

    // 注意到数据滚动데特征, 可使优化使得space: O(1)
    public int minCostClimbingStairs1(int[] cost) {
        if (cost.length <= 1) return 0;
        int len = cost.length;
        int a = cost[0];
        int b = cost[1];
        int cur = 0;
        for (int i = 2; i < len; i++) {
            cur = cost[i] +  Math.min(a, b);
            a = b;
            b = cur;
        }
        return Math.min(a, b);
    }
}

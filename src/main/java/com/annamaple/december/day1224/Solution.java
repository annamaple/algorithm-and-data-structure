package com.annamaple.december.day1224;

public class Solution {

    // 双从遍历 time:O(n)  space: O(n)
    public int candyReference1(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] left = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int res = 0;
        int right = 0;
        // 从哪一边开始, 哪一边的第一个为 1
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            res += Math.max(left[i], right);
        }
        return res;
    }

    // 方法二：常数空间遍历 time: O(n)    space: O(1)
    public int candyReference2(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int n = ratings.length;
        int ret = 1;
        int inc = 1; // 单调递增增部分的长度
        int dec = 0; // 递减部分的长度
        int pre = 1; // 前一个同学获得的糖果
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) dec++;
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}

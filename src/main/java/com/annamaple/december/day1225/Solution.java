package com.annamaple.december.day1225;

import java.util.Arrays;

public class Solution {

    // 参考答案 贪心
    // 复杂度分析
    //时间复杂度: O(m log m + nlog n),
    //         其中 m 和 n 分别是数组 g 和 s 的长度。对两个数组排序的时间复杂度是 O(m log m + n log n), 遍历数组的时间复杂度是 O(m+n)O(m+n)，因此总时间复杂度是 O(m \log m + n \log n)。
    //空间复杂度: O(log m + log n), 其中 m 和 n 分别是数组 g 和 s 的长度。空间复杂度主要是排序的额外空间开销。
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int count = 0;
        for (int i: s) {
            if (index < g.length && i >= g[index]) {
                index++;
                count++;
            }
        }
        return count;
    }
}

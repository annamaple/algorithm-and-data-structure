package com.annamaple.december.day1215;


/**
 * leetcode:738 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class Solution {

    // my answer
    public int monotoneIncreasingDigits(int N) {
        String nStr = String.valueOf(N);
        int index = 0; // 用于subString用
        for (int i = 1; i < nStr.length(); i++) {
            // 出现左 > 右 返回
            if (nStr.charAt(i - 1) > nStr.charAt(i)) {
                StringBuilder sb = new StringBuilder();
                sb.append(nStr, 0, index).append(nStr.charAt(index) - '1');
                for (int j = 0; j < nStr.length() - index - 1; j++) {
                    sb.append(9);
                }
                return Integer.parseInt(sb.toString());
            } else if (nStr.charAt(i - 1) == nStr.charAt(i)) {
                if (nStr.charAt(index) != nStr.charAt(i)) {
                    index = i - 1;
                }
            } else {
                index = i;
            }
        }
        return N;
    }

    public int monotoneIncreasingDigitsReferenceAnswer(int N) {
        char[] arr = Integer.toString(N).toCharArray();
        int i = 1;
        // 从最高位开始找第一次递减的时候
        while (i < arr.length && arr[i - 1] <= arr[i]) {
            i++;
        }
        // 当找到时i 一定小于arr.length
        if (i < arr.length) {
            // 找到分隔点
            while (i > 0 && arr[i - 1] > arr[i]) {
                arr[i - 1] -= 1;
                i--;
            }
            for (i += 1; i < arr.length; ++i) {
                arr[i] = '9';
            }
        }
        return Integer.parseInt(new String(arr));
    }
}

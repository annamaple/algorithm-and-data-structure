package com.annamaple.leetcode;

import cn.hutool.core.lang.Console;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 二倍数对数组<br>
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足
 * “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，
 * 返回 true；否则，返回 false。<br>
 * <br>
 * 示例 1：<br>
 * 输入：arr = [3,1,3,6]<br>
 * 输出：false<br>
 * 示例 2：<br>
 * 输入：arr = [2,1,2,6]<br>
 * 输出：false<br>
 * 示例 3：<br>
 * 输入：arr = [4,-2,2,-4]<br>
 * 输出：true<br>
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]<br>
 * <br>
 * 提示：
 * <br>
 * 0 <= arr.length <= 3 * 104<br>
 * arr.length 是偶数<br>
 * -105 <= arr[i] <= 105<br>
 *
 * @author xionglei
 * @link https://leetcode-cn.com/problems/array-of-doubled-pairs/
 * @create 2022-04-01 10:02
 */
public class LeetCode954 {

    public static void main(String[] args) {
        int[] arr = {2, 4, 0, 0, 8, 1};
        Console.log("arr: {}", Arrays.toString(arr));
        Console.log(new Solution().canReorderDoubled(arr));
    }

    private static class Solution {

        public boolean canReorderDoubled(int[] arr) {
            if (arr == null || arr.length == 0 || (arr.length & 1) == 1) {
                return false;
            }
            Set<Integer> set = new HashSet<>();
            for (int num : arr) {
                if (set.contains(num * 2)) {
                    set.remove(num * 2);
                } else if (((num & 1) == 0) && set.contains(num >> 1)) {
                    set.remove(num >> 1);
                } else {
                    set.add(num);
                }
            }
            return set.isEmpty();
        }
    }

    /**
     * 随机数据生成器
     *
     * @return arr[]
     */
    private static int[] dataCreatorRandom() {
        Random random = new Random();
        int len = random.nextInt(10);
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    /**
     * 标准数据生成器
     *
     * @return arr[]
     */
    private static int[] dataCreatorStand() {
        Random random = new Random();
        int len = random.nextInt(10);
        int[] arr = new int[len * 2];
        int maxIndex = arr.length - 1;
        for (int i = 0; i < len; i++) {
            arr[i] = arr[maxIndex - i] = random.nextInt(10);
        }
        return arr;
    }
}

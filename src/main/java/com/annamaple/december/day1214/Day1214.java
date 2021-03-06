package com.annamaple.december.day1214;


/**
 * leetcode:49 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明:
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class Day1214 {

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution().groupAnagrams(strs));
        System.out.println(new Solution().groupAnagramsReferenceAnswer1(strs));
        System.out.println(new Solution().groupAnagramsReferenceAnswer2(strs));
    }
}

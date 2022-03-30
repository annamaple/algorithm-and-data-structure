package com.annamaple.december.day1220;


/**
 * leetcode: 316 去除重复得的字母
 * 给你一个字符串 s, 请你去除字符串中重复的字母, 使得每个字母只出现一次。
 * 需保证返回结果的字典序最小(要求不能打乱其他字符的相对位置)
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class Day1220 {

    public static void main(String[] args) {

        String s = "cbacdcbc";
        System.out.println(new Solution().removeDuplicateLettersReference(s));
    }
}

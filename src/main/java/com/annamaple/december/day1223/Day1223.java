package com.annamaple.december.day1223;

/**
 *
 * leetcode387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 */
public class Day1223 {

    public static void main(String[] args) {
        String str = "asdafaf";
        System.out.println(new Solution().firstUniqChar(str));
        System.out.println(new Solution().firstUniqCharReference(str));
        System.out.println(new Solution().firstUniqCharReferenceUseQueue(str));
    }
}

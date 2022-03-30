package com.annamaple.december.day1218;

public class Solution {

    // my answer just so so 计数 time: O(n) space: O(52)
    public char findTheDifference(String s, String t) {
        char res = 'a';
        char[] chars = new char[26];
        char[] chart = new char[26];
        int i = 0;
        for (; i < s.length(); i++) {
            chars[s.charAt(i) - 'a'] += 1;
            chart[t.charAt(i) - 'a'] += 1;
        }
        chart[t.charAt(i) - 'a'] += 1;

        for (int j = 0; j < 26; j++) {
            if (chars[j] != chart[j]) {
                res = (char) ('a' + j);
                break;
            }
        }
        return res;
    }


    // 计数 time: O(n) space: O(26)
    public char findTheDifferenceReference1(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); ++i) {
            if (--cnt[t.charAt(i) - 'a'] < 0) {
                return t.charAt(i);
            }
        }
        return ' ';
    }


    // 求和 字符串中的每个Unicode码求和, 得到As, 对字符串t同样的方法得到At At - As 即得到对应的字符
    // time: O(n)   space: O(1)
    public char findTheDifferenceReference2(String s, String t) {
        int as = 0;
        int at = 0;
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            at += t.charAt(i);
        }
        return (char) (at - as);
    }

    // 位运算 把两个字符串拼接在一起，即且字符出现次数为计数的字符
    // time: O(n) space: O(1)
    public char findTheDifferenceReference3(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret = ret ^ s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret = ret ^ t.charAt(i);
        }
        return (char) ret;
    }

}

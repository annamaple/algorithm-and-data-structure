package com.annamaple.december.day1216;

public class Day1216 {

    public static void main(String[] args) {
        String pattern = "aad";
        String str = "dog dog cat";
        System.out.println(new Solution().wordPattern(pattern, str));

        // 12 ==> 0012
        String formatStr = String.format("%04d", 12);
        System.out.println(formatStr);
        // 字符串补位
        System.out.println(String.format("%4s", "IU").replaceAll(" ", "0"));
    }
}

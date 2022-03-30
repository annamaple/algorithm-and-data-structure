package com.annamaple.december.day1220;


import java.util.ArrayDeque;
import java.util.Deque;


public class Solution {

    // 参考答案 使用栈
    // time: O(n)  space: O(n)
    public String removeDuplicateLettersReference(String s) {

        int len = s.length();
        char[] charArray = s.toCharArray();
        int[] lastIndex = new int[26];
        // 记录每一个字符在字符串中最后一次出现的位置
        for (int i = 0; i < len; i++) {
            lastIndex[charArray[i] - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[26];
        for (int i = 0; i < len; i++) {
            if (visited[charArray[i] - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast() > charArray[i] && lastIndex[stack.peekLast() - 'a'] > i) {
                Character top = stack.removeLast();
                visited[top - 'a'] = false;
            }
            stack.addLast(charArray[i]);
            visited[charArray[i] - 'a']   = true;
        }

        StringBuilder sb = new StringBuilder();
        stack.forEach(sb::append);
        return sb.toString();
    }
}

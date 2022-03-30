package com.annamaple.december.day1223;

import java.util.*;

public class Solution {

    // my answer 太麻烦
    // time: O(n)   space: O(52)
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] charArray = s.toCharArray();
        int[] firstIndex = new int[26];
        int[] lastIndex = new int[26];
        Arrays.fill(firstIndex, -1);
        Arrays.fill(lastIndex, -1);
        int i = 0;
        int j = s.length() - 1;
        while (i < s.length() && j >= 0) {
            lastIndex[charArray[i] - 'a'] = i;
            firstIndex[charArray[j] - 'a'] = j;
            i++;
            j--;
        }
        int res = -1;
        for (int k = 0; k < 26; k++) {
            if (lastIndex[k] != -1 && lastIndex[k] == firstIndex[k]) {
                int p = lastIndex[k];
                res = res >= 0 && res <= p ? res : p;
            }
        }
        return res;
    }

    public int firstUniqCharReference(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] charArray = s.toCharArray();
        int[] times = new int[26];
        for (int i = 0; i < s.length(); i++) {
            times[charArray[i] - 'a'] += 1;
        }
        int res = -1;
        for (int i = 0; i < s.length(); i++) {
            if (times[charArray[i] - 'a'] == 1) {
                res = i;
                break;
            }
        }
        return res;
    }

    // 使用队列
    public int firstUniqCharReferenceUseQueue(String s) {
        if (s == null || s.length() == 0) return -1;
        Map<Character, Integer> indexMap = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        int n = s.length();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char ch = charArray[i];
            if (!indexMap.containsKey(ch)) {
                indexMap.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                indexMap.put(ch, -1);
                while (!queue.isEmpty() && indexMap.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.poll().index;
    }

    static class Pair {
        char ch;
        int index;

        public Pair(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }
    }

}

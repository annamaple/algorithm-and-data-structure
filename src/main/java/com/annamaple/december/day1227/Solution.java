package com.annamaple.december.day1227;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    // my answer
    // time: O(n) space: O(n)
    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) return false;
        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        Map<Character, Character> map = new HashMap<>();
        char temp = 'a';
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(charArrayS[i])) {
                charArrayS[i] = map.get(charArrayS[i]);
            } else {
                map.put(charArrayS[i], temp);
                charArrayS[i] = temp;
                temp++;
            }
        }
        map.clear();
        temp = 'a';
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(charArrayT[i])) {
                charArrayT[i] = map.get(charArrayT[i]);
            } else {
                map.put(charArrayT[i], temp);
                charArrayT[i] = temp;
                temp++;
            }
        }
        return Arrays.equals(charArrayS, charArrayT);
    }

    // reference answer
    public boolean isIsomorphicReference(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();
        int n = s.length();
        Map<Character, Character> mapS = new HashMap<>();
        Map<Character, Character> mapT = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = charArrayS[i];
            char c2 = charArrayT[i];
            if (mapS.containsKey(c1) && mapS.get(c1) != c2 || mapT.containsKey(c2) && mapT.get(c2) != c1) {
                return false;
            } else {
                mapS.put(c1, c2);
                mapT.put(c2, c1);
            }
        }
        return true;
    }
}

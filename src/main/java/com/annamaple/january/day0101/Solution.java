package com.annamaple.january.day0101;

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int prev = -1;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count += (i - prev - 2) / 2;
                }
                prev = i;
            }
        }
        if (prev < 0) {
            count = (len - 1) / 2 + 1;
        } else {
            count = count + (len - 1 - prev) / 2;
        }
        return count >= n;
    }
}
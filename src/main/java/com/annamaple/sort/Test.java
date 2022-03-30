package com.annamaple.sort;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 4, 7, 9, 8};

        System.out.println("before sort: " + Arrays.toString(nums));
        Sort sort = new SelectSort();
        sort.sort(nums);
        System.out.println("after sort: " + Arrays.toString(nums));

    }

}

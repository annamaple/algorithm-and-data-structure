package com.annamaple.sort;

/**
 * 选择排序
 */
public class SelectSort implements Sort {

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
                swap(nums, i, minIndex);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

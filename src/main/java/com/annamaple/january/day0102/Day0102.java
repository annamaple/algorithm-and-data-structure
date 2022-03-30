package com.annamaple.january.day0102;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode: 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * 提示：
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class Day0102 {

    // TODO: 2021/1/2 该题目未做出来 优先队列需要了解。 需要了解官方的三种解法
    // https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(nums, k)));

        priorityQueue();
    }


    /**
     * 优先队列 练习
     */
    private static void priorityQueue() {

        System.out.println("\n--------优先队列练习--------");
        // 参考: https://www.cnblogs.com/Elliott-Su-Faith-change-our-life/p/7472265.html
        PriorityQueue<Person> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.age)));
        // compare 返回的值小的放前面
        queue.offer(new Person("IU", 27));
        queue.offer(new Person("Yoona", 31));
        queue.offer(new Person("Yaeyaon", 30));
        queue.offer(new Person("Yaeyaon", 25));
        queue.forEach(System.out::println);

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) ->
                o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]
//                o1[1] - o2[1] // <==> o1[1]
        );
        priorityQueue.offer(new int[]{1,2});
        priorityQueue.offer(new int[]{2,3});
        priorityQueue.offer(new int[]{0,3});
        priorityQueue.offer(new int[]{-1,3});
        priorityQueue.forEach(ints -> System.out.println(Arrays.toString(ints)));
    }

    static class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

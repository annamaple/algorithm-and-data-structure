package com.annamaple.december.day1217;

public class Day1217 {

    public static void main(String[] args) {
        E e1 = E.A;
        E e2 = E.B;
        E e3 = E.valueOf("A");
        System.out.println(e1 == e3);

        System.out.printf("%d", new Solution().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(null==E.A);
    }

    enum E {
        A,
        B
    }
}

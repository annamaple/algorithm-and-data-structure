package com.annamaple.sort;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xionglei
 * @create 2021-10-21 15:44
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 0; i < list.size() / 10; i++) {
            System.out.println(list.subList(i * 10, i * 10 + 10));
        }
    }
}

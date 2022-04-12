package com.annamaple.test;

import cn.hutool.core.lang.Console;

/**
 * @author xionglei
 * @create 2022-04-12 20:32
 */
public class CoThread {

    public static void main(String[] args) {
        // 第一位表示一个包数据的长度
        byte[] men = {4, 1, 1, 1, 1, 5, 2, 2, 2, 2, 2, 5};
        // 数组下标
        int i = 0;
        // 标记是第一个小包, 可以不要
        int num = 0;
        while (i < men.length) {
            int len = men[i] + i + 1;
            Console.log("第{}组：", num++);
            for (; i < len && i < men.length; i++) {
                System.out.print(men[i] + " ");
            }
            System.out.println();
        }
    }
}

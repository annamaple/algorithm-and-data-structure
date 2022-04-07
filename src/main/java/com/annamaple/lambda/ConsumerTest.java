package com.annamaple.lambda;

import java.util.function.Consumer;

/**
 * @author xionglei
 * @create 2022-04-07 15:08
 */
public class ConsumerTest {

    public static void main(String[] args) {
        int num = 10;
        // Consumer 接收一个参数，无返回值
        Consumer<Integer> consumer = x -> System.out.println("x: " + x);
        // 无返回值，直接输出
        consumer.accept(num);
        System.out.println("---");
        // x: 10
        // xx: 10
        // andThen 添加执行当前consumer的accept之后需要执行的代码。可嵌套
        consumer.andThen(x -> System.out.println("xx: " + x)).accept(num);
        System.out.println("---");
        // x: 10
        // x1: 10
        // x2: 10
        consumer.andThen(x -> System.out.println("x1: " + x))
                .andThen(x -> System.out.println("x2: " + x))
                .accept(num);
    }
}

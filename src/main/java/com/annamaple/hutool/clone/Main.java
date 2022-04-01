package com.annamaple.hutool.clone;

import cn.hutool.core.lang.Console;

/**
 * @author xionglei
 * @create 2022-03-23 12:07
 */
public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("小花", 2);
        Cat cloneCat = cat.clone();
        Console.log("cat == cloneCat = {}", cat == cloneCat);
    }
}

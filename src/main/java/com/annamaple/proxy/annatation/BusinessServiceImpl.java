package com.annamaple.proxy.annatation;

import cn.hutool.core.lang.Console;

/**
 * @author xionglei
 * @create 2022-04-19 10:03
 */
public class BusinessServiceImpl implements BusinessService{
    @Override
    public void method01() {
        Console.log("method01 ...");
    }

    @Override
    public void method02() {
        Console.log("method02 ...");
    }
}

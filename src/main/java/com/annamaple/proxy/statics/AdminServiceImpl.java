package com.annamaple.proxy.statics;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;

/**
 * 真正干活的类
 *
 * @author xionglei
 * @create 2022-04-14 20:15
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public void update(Integer num) {
        Console.log("AdminServiceImpl.update() 【{}】", num);
    }

    @Override
    public Object find() {
        Console.log("AdminServiceImpl.find()");
        return DateTime.now();
    }
}

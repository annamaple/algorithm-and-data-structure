package com.annamaple.proxy.statics;

import cn.hutool.core.lang.Console;

/**
 * @author xionglei
 * @create 2022-04-14 20:21
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        // 真正干活的类
        AdminService adminService = new AdminServiceImpl();
        // 代理类
        AdminService adminProxy = new AdminServiceProxy(adminService);
        adminProxy.update(3);
        Console.log("=====================");
        Console.log("{}", adminProxy.find());
    }
}

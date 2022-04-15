package com.annamaple.proxy.statics;

import cn.hutool.core.lang.Console;

/**
 * 代理类
 *
 * @author xionglei
 * @create 2022-04-14 20:17
 */
public class AdminServiceProxy implements AdminService {

    /**
     * 真正干货的类
     */
    private AdminService adminService;

    /**
     * 构造方法，构造时需要把正在干活的类传递进来
     *
     * @param adminService 真正干货的类
     */
    public AdminServiceProxy(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void update(Integer num) {
        Console.log("AdminServiceProxy.update() start...");
        adminService.update(num);
        Console.log("AdminServiceProxy.update() end...");
    }

    @Override
    public Object find() {
        Console.log("AdminServiceProxy.find() start...");
        Object res = adminService.find();
        Console.log("AdminServiceProxy.find() end...");
        return res;
    }
}

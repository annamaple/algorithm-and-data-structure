package com.annamaple.proxy.danamic;

import cn.hutool.core.lang.Console;
import com.annamaple.proxy.statics.AdminService;
import com.annamaple.proxy.statics.AdminServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author xionglei
 * @create 2022-04-14 20:24
 */
public class DynamicProxyTest {

    public static void main(String[] args) {

        AdminService adminService = new AdminServiceImpl();
        AdminService proxyInstance = (AdminService) Proxy.newProxyInstance(
                adminService.getClass().getClassLoader(),
                new Class[]{AdminService.class},
                new AdminServiceInvocationHandler(adminService));
        proxyInstance.update(5);
        Console.log("=====================");
        proxyInstance.find();
    }
}

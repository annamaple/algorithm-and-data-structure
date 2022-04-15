package com.annamaple.proxy.danamic;

import cn.hutool.core.lang.Console;
import com.annamaple.proxy.statics.AdminService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义代理执行监听器
 *
 * @author xionglei
 * @create 2022-04-14 20:31
 */
public class AdminServiceInvocationHandler implements InvocationHandler {

    /**
     * 被代理的对象，可以是任意类型。这里是今天代理和动态代理的很大区别
     */
    private Object realObject;

    public AdminServiceInvocationHandler(Object realObject) {
        this.realObject = realObject;
    }

    /**
     * 代理监听执行方法
     *
     * @param proxy  代理类
     * @param method 回调方法——被代理代理方法
     * @param args   参数数组
     * @return 此处返回Object，根据被代理的方法可以强转
     * @throws Throwable t
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Console.log("AdminServiceInvocationHandler.invoke() start...");
        Object res = method.invoke(realObject, args);
        Console.log("AdminServiceInvocationHandler.invoke() end.....");
        return res;
    }
}

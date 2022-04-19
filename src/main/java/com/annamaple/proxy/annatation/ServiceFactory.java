package com.annamaple.proxy.annatation;

import cn.hutool.core.lang.Console;

import java.lang.reflect.Proxy;

/**
 * 工厂类产生代理
 *
 * @author xionglei
 * @create 2022-04-19 10:04
 */
public class ServiceFactory<T> {

    private final T relObject;

    public ServiceFactory(T relObject) {
        this.relObject = relObject;
    }

    @SuppressWarnings("unchecked")
    public T createProxy() {
        // 产生代理对象并返回
        return (T) Proxy.newProxyInstance(
                relObject.getClass().getClassLoader(),
                relObject.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    // 获取自定义注解
                    Permission permission = method.getAnnotation(Permission.class);
                    if (permission == null) {
                        Console.log("没有注解");
                        return method.invoke(relObject, args);
                    } else if (permission.value() == 0) {
                        Console.log("permission.value() == 0, 不验证权限");
                        return method.invoke(relObject, args);
                    } else if (permission.value() == 1) {
                        Console.log("permission.value() == 1, 进行验证权限");
                        User user = Operator.operator();
                        if (user.getId() < 10) {
                            Console.log("当前用户id为{}, 验证权限通过", user.getId());
                            return method.invoke(relObject, args);
                        } else {
                            Console.log("当前用户id为{}, 验证权限不通过", user.getId());
                            return null;
                        }
                    } else {
                        throw new IllegalArgumentException("输入的值非法, permission.value()的值只能是0或1");
                    }
                });
    }
}

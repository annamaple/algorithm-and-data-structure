package com.annamaple.proxy.cjlib;

import cn.hutool.core.lang.Console;
import com.annamaple.proxy.annatation.Permission;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author xionglei
 * @create 2022-04-19 11:35
 */
public class TargetInterceptor implements MethodInterceptor {

    /**
     * 增强的方法
     *
     * @param o           目标对象
     * @param method      目标方法
     * @param args        参数
     * @param methodProxy CGlib方法代理对象
     * @return 目标方法返回值
     * @throws Throwable T
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Permission annotation = method.getAnnotation(Permission.class);
        if (annotation != null) {
            Console.log("annotation {} value: {}", "Annotation", annotation.value());
        }
        Console.log("增强...在执行目标方法之前...");
        Object res = methodProxy.invokeSuper(o, args);
        Console.log("增强...在执行目标方法之后...");
        return res;
    }
}

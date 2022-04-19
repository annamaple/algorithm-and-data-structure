package com.annamaple.proxy.cjlib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author xionglei
 * @create 2022-04-19 11:41
 */
public class TestCglib {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor());
        
        TargetObject targetObjectProxy = (TargetObject) enhancer.create();
        System.out.println(targetObjectProxy.method1("annamaple"));
        targetObjectProxy.method2(2);
        targetObjectProxy.method3(3);
    }
}

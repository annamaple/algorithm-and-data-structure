package com.annamaple.reflex;

import cn.hutool.core.util.ClassUtil;

import java.util.Set;

/**
 * @author xionglei
 * @create 2022-05-23 13:59
 */
public class ChildClassObtain {
    
    public static void getChildClass() {
        String aPackage = ClassUtil.getPackage(User.class);
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper("", User.class);
        System.out.println(aPackage);
        classes.forEach(System.out::println);
    }

    public static void main(String[] args) {
        getChildClass();
    }
}

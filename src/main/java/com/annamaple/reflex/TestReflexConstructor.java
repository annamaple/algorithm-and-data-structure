package com.annamaple.reflex;

import cn.hutool.core.lang.Console;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射生成类的方式
 *
 * @author xionglei
 * @create 2022-04-14 17:42
 */
public class TestReflexConstructor {

    public static void main(String[] args) {
        // 1.使用Class对象的newInstance()方法来创建Class对象对应类的实例。 
        Class<User> userClass = User.class;
        User user1 = null;
        try {
            user1 = userClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Console.log("使用反射创建实例方式一：【{}】", user1);
        // 2.先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法来创建实例。
        // （先取到构造函数，在通过构造函数的newInstance()创建）
        Constructor<User> constructor = null;
        try {
            constructor = userClass.getConstructor(String.class, Integer.class);
            User xiaoMei = constructor.newInstance("xiaoMei", 22);
            Console.log("使用反射创建实例方式一：【{}】", xiaoMei);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

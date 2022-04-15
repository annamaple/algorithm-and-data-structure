package com.annamaple.reflex;

import cn.hutool.core.lang.Console;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 反射获取类的属性对泛型类型的处理
 *
 * @author xionglei
 * @create 2022-04-14 18:03
 */
public class TestReflexTypeVariable<K extends Comparable<? super K> & Serializable, V> {
    K key;
    V value;
    Map<String, Integer> map;
    List<String>[] lists;

    // 上限
    private List<? extends Number> a;
    // 下限
    private List<? super String> b;

    public static void main(String[] args) {
        // 获取字段类型
        Class<TestReflexTypeVariable> typeClass = TestReflexTypeVariable.class;
        try {
            Field fk = typeClass.getDeclaredField("key");
            Field fv = typeClass.getDeclaredField("value");
            TypeVariable<?> keyType = (TypeVariable<?>) fk.getGenericType();
            TypeVariable<?> valueType = (TypeVariable<?>) fv.getGenericType();
            // getName()
            Console.log("getName()： keyType: 【{}】; valueType: 【{}】", keyType.getName(), valueType.getName());
            // getGenericDeclaration()
            Console.log("getGenericDeclaration()： keyType: 【{}】; valueType: 【{}】",
                    keyType.getGenericDeclaration(), valueType.getGenericDeclaration());
            // getBounds() 上界存在多个，返回的是数组。没有明确申明上界默认上界为Object
            Console.log("getBounds()： k的上界: 【{}】; v的上界: 【{}】",
                    keyType.getBounds(), valueType.getBounds());
            // 
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        try {
            // 获取名字为map的属性
            Field fMap = typeClass.getDeclaredField("map");
            // getGenericType()
            Console.log("getGenericType(): 【{}】", fMap.getGenericType());
            ParameterizedType pType = (ParameterizedType) fMap.getGenericType();
            Console.log("pType.getRawType(): 【{}】", pType.getRawType());
            Arrays.stream(pType.getActualTypeArguments())
                    .forEach(type -> Console.log("pType.getActualTypeArguments(): 【{}】", type));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            Field fList = typeClass.getDeclaredField("lists");
            GenericArrayType genericArrayType = (GenericArrayType) fList.getGenericType();
            Console.log("genericArrayType.getGenericComponentType(): 【{}】", genericArrayType.getGenericComponentType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // 上限与下限
        try {
            Field fieldA = typeClass.getDeclaredField("a");
            Field fieldB = typeClass.getDeclaredField("b");
            // 先拿到泛型类型
            ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
            ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();
            // 再重泛型类型拿到通配符类型, 就一个泛型值直接去[0]
            WildcardType wildcardTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
            WildcardType wildcardTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];
            // 方法测试
            // class java.lang.Number
            Console.log("wildcardTypeA.getUpperBounds()[0]: 【{}】", wildcardTypeA.getUpperBounds()[0]);
            // class java.lang.String
            Console.log("wildcardTypeB.getLowerBounds()[0]: 【{}】", wildcardTypeB.getLowerBounds()[0]);
            // class java.lang.Object
            Console.log("wildcardTypeB.getUpperBounds()[0]: 【{}】", wildcardTypeB.getUpperBounds()[0]);
            // 查看通配符类型
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        
        // 思考 JSON序列化是传递的new TypeToken<>(){} 为什么需要这个大括号。
        // https://juejin.cn/post/6953116028633088036
    }


}

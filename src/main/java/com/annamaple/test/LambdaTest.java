package com.annamaple.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author xionglei
 * @create 2022-04-24 11:56
 */
public class LambdaTest {

    public static void main(String[] args) {

        Map<Integer, List<String>> map = new HashMap<>(16);
        // 基本类型封装类使用
//        final int size = 10;
//        final String str = "sss";
        final Integer integer = Integer.parseInt("12");
        final Object o = new Object();
        for (int i = 0; i < 10; i++) {
            Function<Integer, List<String>> function = (k) -> {
                
                List<String> list = new ArrayList<>(integer);
//                list.add(str);
                return list;
            };
            System.out.println("address: " + function);
            map.computeIfAbsent(i, function);
        }
            
         
    }
}

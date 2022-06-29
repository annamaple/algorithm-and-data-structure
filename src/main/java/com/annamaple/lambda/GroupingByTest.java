package com.annamaple.lambda;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Console;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Collectors.groupingBy
 *
 * @author xionglei
 * @create 2022-06-29 15:24
 */
public class GroupingByTest {

    public static void main(String[] args) {
        LinkedList<Fruit> fruits = ListUtil.toLinkedList(
                new Fruit("apple", 1.2D),
                new Fruit("apple", 1.5D),
                new Fruit("apple", 2.2D),
                new Fruit("banana", 2.5D),
                new Fruit("banana", 3.2D),
                new Fruit("grape", 5.2D));
        // 统计每种水果的个数
        // 常规写法
        Map<String, Integer> normalMap = new HashMap<>();
        fruits.forEach(fruit -> normalMap.put(fruit.getName(), normalMap.computeIfAbsent(fruit.getName(), k -> 0) + 1));
        System.out.println("normalMap: " + normalMap);
        // Collectors.groupingBy 写法
        Map<String, Long> countMap = fruits.stream().collect(Collectors.groupingBy(Fruit::getName, Collectors.counting()));
        System.out.println("countMap: " + countMap);
        // 复查写法
        Map<String, Long> fzMap = fruits.stream().map(Fruit::getName).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("fzMap: " + fzMap);
        // 排序：按照水果map中value的数量逆序打印每个entry
        Console.log("排序：按照水果map中value的数量逆序打印每个entry");
        fzMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEachOrdered(System.out::println);
        // 累加求和: 计算各个水果的总额
        System.out.println(fruits.stream().collect(Collectors.groupingBy(Fruit::getName, Collectors.summingDouble(Fruit::getPrice))));
        // 分组：根据name分组 
        Map<String, List<Fruit>> groupMap = fruits.stream().collect(Collectors.groupingBy(Fruit::getName));
        System.out.println("groupMap: " + groupMap);
        // 上述代码根据name将list分组，如果name是唯一的，那么上述代码就会显得啰嗦
        // group by price, uses 'mapping' to convert List<Fruit> to List<String>
        Map<String, List<Double>> groupPriceMap = fruits
                .stream()
                .collect(Collectors.groupingBy(Fruit::getName, Collectors.mapping(Fruit::getPrice, Collectors.toList())));
        System.out.println("groupPriceMap： " + groupPriceMap);
    }

}

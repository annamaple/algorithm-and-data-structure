package com.annamaple.utils;

import java.util.Iterator;

public class MyTreeMapTest {

    public static void main(String[] args) {
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(1, "IU");
        map.show();
        map.put(2, "Yoona");
        map.show();
        map.put(3, "zhien");
        map.show();
        map.put(4, "manyue");
        map.show();
        map.put(5, "yaya");
        map.show();
        map.remove(1);
        map.show();
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer e = iterator.next();
            System.out.println(e);
            if (e == 3) {
                iterator.remove();
            }
        }
        for (String v : map.values()) {
            System.out.println(v);
        }
        for (MyTreeMap.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("=============Custom type============");
        MyTreeMap<People, String> treeMap = new MyTreeMap<>();
        treeMap.put(new People("IU", 1), "MyLove");
        treeMap.put(new People("Yoona", 2), "MyLove");
        treeMap.put(new People("Taeyaon", 3), "MyLove");
        treeMap.show();
    }
}

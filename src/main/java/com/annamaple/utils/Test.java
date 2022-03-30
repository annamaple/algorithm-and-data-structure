package com.annamaple.utils;

import java.util.Map;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) {
        TreeNode node = TreeUtils.createBinaryTreeFormArray(new Integer[]{1, 2, 3, null, null, 4});
        TreeUtils.printBFS(node);

        RBTree<Integer, String> rbTree = new RBTree<>();
        rbTree.put(21, "IU");
        rbTree.put(30, "Yoona");
        rbTree.put(28, "Taeyon");
        rbTree.put(20, "zhien");
        rbTree.put(19, "yaya");
        System.out.println(rbTree);
//        rbTree.show();
        rbTree.put(18, "xidao");
        rbTree.show();
        rbTree.remove(30);
        rbTree.show();
        System.out.println(rbTree.getSize());
        System.out.println(rbTree.getModifyCount());

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "IU");
        treeMap.put(2, "manyue");
        treeMap.put(3, "zhien");
        treeMap.put(4, "Yoona");
        for (Map.Entry<Integer, String> e : treeMap.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
        System.out.println("======================================");
        for (RBTree.RBNode<Integer, String> e : rbTree.rbNodeSet()) {
            System.out.println(e.getKey() + "===" + e.getValue());
        }
    }
}

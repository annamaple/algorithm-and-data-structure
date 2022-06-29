package com.annamaple.hutool.tree;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;

import java.util.List;

/**
 * @author xionglei
 * @create 2022-06-20 11:50
 */
public class TreeTest01 {
    public static void main(String[] args) {
        List<TreeNode<String>> nodeList = CollectionUtil.newLinkedList();
        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 1));
        nodeList.add(new TreeNode<>("111", "1", "角色管理", 2));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 2));
        nodeList.add(new TreeNode<>("221", "2", "客户管理", 3));
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        System.out.println(treeList);
    }
}

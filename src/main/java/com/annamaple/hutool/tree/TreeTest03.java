package com.annamaple.hutool.tree;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;

import java.util.List;

/**
 * @author xionglei
 * @create 2022-06-20 11:50
 */
public class TreeTest03 {
    public static void main(String[] args) {
        List<TreeNode<String>> nodeList = CollectionUtil.newLinkedList();
        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 1));
        nodeList.add(new TreeNode<>("111", "1", "角色管理", 2));
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");

//        // 配置
//        TreeNodeConfig config = new TreeNodeConfig();
//        config.setWeightKey("order");
//        config.setIdKey("rid");
//        config.setDeep(3);
//
//        //使用自定义配置构建
//        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", config,
//                (treeNode, tree) -> {
//                    tree.setId(treeNode.getId());
//                    tree.setParentId(treeNode.getParentId());
//                    tree.setWeight(treeNode.getWeight());
//                    tree.setName(treeNode.getName());
//                    // 扩展属性 ...
//                    tree.putExtra("extraField", 666);
//                    tree.putExtra("other", new Object());
//                });

        System.out.println(treeList);
    }
    
}

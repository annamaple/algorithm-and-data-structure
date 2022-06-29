package com.annamaple.utils.tree;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

/**
 * 树够着器
 *
 * @author xionglei
 * @create 2022-06-28 15:15
 */
public class TreeCreator {

    public static <E> TreeNode<E> creat(List<TreeNode<E>> nodeList) {
        // 把每个子节点保存起来，以便后面插入父节点; key 父节点; value当前节点
        Map<String, List<TreeNode<E>>> treeDataByParentId = new HashMap<>();
        // 对每节点循环，把数据放到treeDataByParentId中
        Objects.requireNonNull(nodeList).forEach(eTreeNode -> treeDataByParentId.computeIfAbsent(eTreeNode.pid, K -> new LinkedList<>()).add(eTreeNode));
        // 树的第一层
        List<TreeNode<E>> firstLevel = new LinkedList<>();
        nodeList.forEach(eTreeNode -> {
            // 查询当前节点的所有直接孩子节点
            eTreeNode.children = treeDataByParentId.get(eTreeNode.id);
            // 当前节点不具备父节点, 表示为第一层的数据
            if (StrUtil.isBlank(eTreeNode.pid)) {
                firstLevel.add(eTreeNode);
            }
        });
        TreeNode<E> root = new TreeNode<>();
        root.children = firstLevel;
        return root;
    }


    public static class TreeNode<E> {
        public String id;
        public String pid;
        public E data;
        public List<TreeNode<E>> children;

        public TreeNode() {
        }

        public TreeNode(String id, String pid, E data) {
            this.id = id;
            this.pid = pid;
            this.data = data;
        }
    }


    public static void main(String[] args) {
        List<TreeNode<Integer>> treeNodeList = buildTreeNode();
        TreeNode<Integer> root = creat(treeNodeList);
        System.out.println("构造成功");
        printBfs(root);
    }

    private static List<TreeNode<Integer>> buildTreeNode() {
        List<TreeNode<Integer>> list = new LinkedList<>();
        // 第一层
        for (int i = 0; i < 4; i++) {
            list.add(new TreeNode<>(i + "", null, i));
        }
        // 第二层
        for (int i = 10; i < 20; i++) {
            Random random = new Random();
            list.add(new TreeNode<>(i + "", random.nextInt(4) + "", i));
        }
        // 第三层
        for (int i = 100; i < 120; i++) {
            Random random = new Random();
            list.add(new TreeNode<>(i + "", (random.nextInt(10) + 10) + "", i));
        }
        return list;
    }

    public static <E> void printBfs(TreeNode<E> root) {
        Objects.requireNonNull(root);
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();
            System.out.println(node.data);
            if (node.children != null && !node.children.isEmpty()) {
                queue.addAll(node.children);
            }
        }
        System.out.println();
    }
}

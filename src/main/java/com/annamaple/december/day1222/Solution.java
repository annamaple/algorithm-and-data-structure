package com.annamaple.december.day1222;

import com.annamaple.utils.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeUtils {
 * int val;
 * TreeUtils left;
 * TreeUtils right;
 * TreeUtils(int x) { val = x; }
 * }
 */
class Solution {

    // DFS time o(n)    space: O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        travelDFS(root, res, 0);
        return res;
    }

    private void travelDFS(TreeNode cur, List<List<Integer>> res, int level) {
        if (cur == null)
            return;
        //如果res.size() <= level说明下一层的集合还没创建，所以要先创建下一层的集合
        if (res.size() <= level) {
            List<Integer> newLevel = new LinkedList<>();
            res.add(newLevel);
        }
        //遍历到第几层我们就操作第几层的数据
        List<Integer> list = res.get(level);
        //这里默认根节点是第0层，偶数层相当于从左往右遍历，
        // 所以要添加到集合的末尾，如果是奇数层相当于从右往左遍历，
        // 要把数据添加到集合的开头
        if (level % 2 == 0)
            list.add(cur.val);
        else
            list.add(0, cur.val);
        //分别遍历左右两个子节点，到下一层了，所以层数要加1
        travelDFS(cur.left, res, level + 1);
        travelDFS(cur.right, res, level + 1);
    }


    // BFS time: O(n) space: o(n)
    public List<List<Integer>> zigzagLevelOrderReferenceBFS(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        // 每一层的顺序
        boolean isOrderLeft = true;
        // 辅助队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        nodeQueue.offer(null);
        Deque<Integer> levelList = new LinkedList<>();
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            if (curNode != null) {
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            } else {
                // 为空表示为一行结束
                res.add(new LinkedList<>(levelList));
                isOrderLeft = !isOrderLeft;
                levelList.clear();
                if (nodeQueue.size() > 0) {
                    // 表示节点队列中还存在有效值
                    nodeQueue.offer(null);
                }
            }

        }

        return res;
    }

    // BFS 标准写法
    public List<List<Integer>> zigzagLevelOrderReferenceBFS2(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) return ans;

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            // 和上个方法相比这个方法用size来获取一行데分割，是标志的BFS写法
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }
}
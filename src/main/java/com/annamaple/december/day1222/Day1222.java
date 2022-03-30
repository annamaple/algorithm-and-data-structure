package com.annamaple.december.day1222;


import com.annamaple.utils.TreeNode;
import com.annamaple.utils.TreeUtils;

/**
 * leetcode: 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class Day1222 {

    public static void main(String[] args) throws InterruptedException {
        TreeNode root = TreeUtils.createBinaryTreeFormArray(new Integer[]{1, 2, 3, 4, null, null, 5});
        TreeUtils.printBFS(root);

        System.out.println(new Solution().zigzagLevelOrderReferenceBFS(root));
    }
}

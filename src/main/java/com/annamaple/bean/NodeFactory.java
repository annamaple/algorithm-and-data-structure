package com.annamaple.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * 链表与树生成器(节点值为随机Integer)
 *
 * @author xionglei
 * @create 2022-03-16 10:00
 */
@AllArgsConstructor
@Data
public class NodeFactory {

    /**
     * 节点个数
     */
    private int nodeNumber;
    private static final Random RANDOM = new Random();

    /**
     * 创建一个双向链表
     *
     * @return 双向链表
     */
    public Node<Integer> createLinkedList() {
        // 用于标记链表头, 用于返回
        Node<Integer> head = new Node<>();
        Node<Integer> temp = head;
        for (int i = 0; i < nodeNumber; i++) {
            Node<Integer> node = new TreeNode<>();
            node.setValue(RANDOM.nextInt(100));
            temp.next = node;
            node.pre = temp;
            temp = temp.next;
        }
        return head.next;
    }


    /**
     * 创建一个二叉树
     *
     * @return 双向链表
     */
    public BinaryTreeNode<Integer> createBinaryTree() {
        BinaryTreeNode<Integer> head = new BinaryTreeNode<>();
        head.setValue(Integer.valueOf(RANDOM.nextInt(100)));
        Deque<BinaryTreeNode<Integer>> deque = new LinkedList<>();
        deque.addFirst(head);
        // 记录节点个数
        int number = nodeNumber - 1;
        while (!deque.isEmpty() && number > 0) {
            int i = deque.size();
            while (i > 0 && number > 0) {
                BinaryTreeNode<Integer> parent = deque.removeLast();
                BinaryTreeNode<Integer> left = new BinaryTreeNode<>();
                left.setValue(Integer.valueOf(RANDOM.nextInt(100)));
                parent.left = left;
                left.parent = parent;
                BinaryTreeNode<Integer> right = new BinaryTreeNode<>();
                right.setValue(Integer.valueOf(RANDOM.nextInt(100)));
                parent.right = right;
                right.parent = parent;
                deque.addFirst(left);
                deque.addFirst(right);
                number -= 2;
                i--;
            }
        }
        // 去除多的节点
        if (number < 0) {
            while (number < 0) {
                number++;
                BinaryTreeNode<Integer> treeNode = deque.removeLast();
                BinaryTreeNode<Integer> parent = (BinaryTreeNode<Integer>) treeNode.parent;
                treeNode.parent = null;
                if (parent.right == treeNode) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
            }
        }

        return head;
    }


    public static void main(String[] args) {
        NodeFactory factory = new NodeFactory(10);
        NodeUtil.print(factory.createLinkedList());
        System.out.println();
        System.out.println("binary tree: ");
        BinaryTreeNode<Integer> head = factory.createBinaryTree();
        NodeUtil.print(head);
        System.out.println();
        System.out.println("bfs: ");
        NodeUtil.printBfs(head);
    }
}

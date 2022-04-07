package com.annamaple.bean;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xionglei
 * @create 2022-03-16 10:11
 */
public class NodeUtil {
    
    public static <E extends Comparable<E>> void print(Node<E> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.getValue() + " ");
        print(head.next);
    }
    
    public static <E extends Comparable<E>> void print(BinaryTreeNode<E> head) {
        if (head == null) {
            return;
        }
        System.out.print(head.getValue() + " ");
        print(head.left);
        print(head.right);
    }
    
    public static <E extends Comparable<E>> void printBfs(BinaryTreeNode<E> head) {
        if (head == null) {
            return;
        }
        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0){
                BinaryTreeNode<E> treeNode = queue.poll();
                System.out.print(treeNode.getValue() + " ");
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                size--;
            }
            System.out.println();
        }
    }
}

package com.annamaple.bean;

/**
 * 红黑树节点
 *
 * @author xionglei
 * @create 2022-04-06 14:36
 */
public class RBTreeNode<E extends Comparable<E>> extends BinaryTreeNode<E> {
    
    private static final boolean RED   = false;
    private static final boolean BLACK = true;
    
    /**
     * 红黑树节点的颜色
     */
    boolean color;

    /**
     * 关键值
     */
    E key;

    public RBTreeNode(E key, boolean color, RBTreeNode<E> parent, RBTreeNode<E> left, RBTreeNode<E> right) {
        this.color = color;
        this.key = key;
        super.right = right;
        super.left = left;
        super.parent = parent;
    }
}

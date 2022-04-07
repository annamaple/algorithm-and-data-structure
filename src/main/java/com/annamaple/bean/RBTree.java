package com.annamaple.bean;

/**
 * 红黑树
 * <br>
 * 红黑树的特性:<br>
 * (1) 每个节点或者是黑色，或者是红色。<br>
 * (2) 根节点是黑色。<br>
 * (3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]<br>
 * (4) 如果一个节点是红色的，则它的子节点必须是黑色的。<br>
 * (5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。<br>
 *
 * @author xionglei
 * @create 2022-04-06 14:32
 */
public class RBTree<E extends Comparable<E>> {

    /**
     * 红黑树根节点
     */
    private RBTreeNode<E> root;

    /**
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                             px
     *     /                              /
     *    x                              y
     *   /  \      --(左旋)-.           / \                
     *  lx   y                         x  ry
     *     /   \                     /  \
     *    ly   ry                   lx  ly
     *
     *
     */
    private void leftRotate(RBTreeNode<E> x) {
        
    }
}

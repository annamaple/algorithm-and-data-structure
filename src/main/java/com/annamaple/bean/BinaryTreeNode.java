package com.annamaple.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author xionglei
 * @create 2022-03-16 9:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinaryTreeNode<E> extends TreeNode<E> {

    /**
     * 左子节点
     */
    public BinaryTreeNode<E> left;

    /**
     * 右子节点
     */
    public BinaryTreeNode<E> right;
}

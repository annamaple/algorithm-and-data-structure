package com.annamaple.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author xionglei
 * @create 2022-03-16 9:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<E extends Comparable<E>> extends Node<E> {

    /**
     * 父节点
     */
    public TreeNode<E> parent;

    /**
     * 子节点
     */
    public List<TreeNode<E>> children;
    
}

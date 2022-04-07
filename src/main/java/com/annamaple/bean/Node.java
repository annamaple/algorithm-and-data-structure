package com.annamaple.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xionglei
 * @create 2022-03-16 9:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node<E extends  Comparable<E>> {

    /**
     * 前节点
     */
    public Node<E> pre;
    
    /**
     * 后节点 
     */
    public Node<E> next;

    /**
     * 节点的值
     */
    private E Value;

}

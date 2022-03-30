package com.annamaple.data_structure;

import java.util.Random;

public class SkipList<E extends Comparable> {

    private static final int MAX_LEVEL = 16;
    private static final float SKIP_LIST_P = 0.5f;

    private int levelCount = 1;
    private Node<E> head = new Node<>(null, -1);
    private Random r = new Random();

    public Node<E> find(E value) {
        Node<E> p = head;

        // 重上到下 从左到右 查找 forwards数组表示该节点指向的层数, 最顶层的levelCount最大, 数据层为forwards[0]
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.nextes[i] != null && compare(p.nextes[i].value, value) < 0) {
                p = p.nextes[i];
            }
        }

        // 相等
        if (p.nextes[0] != null && compare(p.nextes[0].value, value) == 0) {
            return p.nextes[0];
        }
        return null;
    }

    public void insert(E value) {
        int level = randomLevel();
        Node<E> newNode = new Node<>(value, level);

        Node<E>[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        // record every level largest value which smaller than insert value in update[]
        Node<E> p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.nextes[i] != null && compare(p.nextes[i].value, value) < 0) {
                p = p.nextes[i];
            }
            update[i] = p; // use update save node in search path
        }

        // in search path node next node become new node next(next)
        for (int i = 0; i < level; i++) {
            newNode.nextes[i] = update[i].nextes[i];
            update[i].nextes[i] = newNode;
        }

        // update node hight
        if (levelCount < level) levelCount = level;
    }

    public boolean delete(E value) {
        Node<E>[] update = new Node[levelCount];
        Node<E> p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.nextes[i] != null && compare(p.nextes[i].value, value) < 0) {
                p = p.nextes[i];
            }
            update[i] = p;
        }

        if (p.nextes[0] != null && compare(p.nextes[0].value, value) == 0) {
            for (int i = levelCount - 1; i <= 0; --i) {
                if (update[i].nextes[i] != null && compare(update[i].nextes[i].value, value) == 0) {
                    update[i].nextes[i] = update[i].nextes[i].nextes[i];
                }
            }
            return true;
        }
        return false;
    }

    public void printAll() {
        Node<E> p = head;
        int index = 0;
        while (p.nextes[0] != null) {
            System.out.println("[" + index++ + "] " + p.nextes[0].value + "    ");
            p = p.nextes[0];
        }
        System.out.println();
    }


    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;
        while (Math.random() < SKIP_LIST_P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    // 为了以后优化比较器,使Skip可用够着函数传来的比较器
    private int compare(E a, E b) {
        return a.compareTo(b);
    }

    class Node<E> {
        private E value;
        private Node<E>[] nextes = new Node[MAX_LEVEL];
        private int level = 0;

        public Node(E value, int level) {
            this.value = value;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", Level=" + level +
                    '}';
        }
    }


}

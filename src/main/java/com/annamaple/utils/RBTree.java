package com.annamaple.utils;

import java.io.Serializable;
import java.util.*;

public class RBTree<K, V> implements Serializable{

    /**
     * 用于接收传入进来的比较器
     */
    private final Comparator<? super K> comparator;

    // Red Black
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private RBNode<K, V> root;
    private int size;
    private int modifyCount;

    private transient RBNodeSet rbNodeSet;

    public int getSize() {
        return size;
    }


    public int getModifyCount() {
        return modifyCount;
    }


    public RBTree() {
        this.comparator = null;
    }
    public RBTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    /**
     * p左旋
     *         p
     *       /  \
     *     pl   pr(r)
     *         / \
     *        rl  rr
     *
     *  pr = p.right;
     *  p.right = pr.left;
     *
     *  p
     * @param p 红黑树节点
     */
    private void rotateLeft(RBNode<K, V> p) {
        if (p != null) {
            RBNode<K, V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null) {
                root = r;
            } else if (p.parent.left == p) {
                p.parent.left = r;
            } else {
                p.parent.right = r;
            }
            p.parent = r;
            r.left = p;
        }
    }

    /**
     * 对红黑树节点p右旋
     *         p
     *       /  \
     *     pl(l)   pr
     *   / \
     *  ll lr
     *
     * @param p 红黑树节点
     */
    private void rotateRight(RBNode<K, V> p) {
        if (p == null) {
            return;
        }
        RBNode<K, V> l = p.left;
        p.left = l.right;
        if (l.right != null) {
            l.right.parent = p;
        }
        l.parent = p.parent;
        if (p.parent == null) {
            root = l;
        } else if (p.parent.left == p) {
            p.parent.left = l;
        } else {
            p.parent.right = l;
        }
        p.parent = l;
        l.right = p;
    }

    private static <K, V> RBNode<K, V> parentOf(RBNode<K, V> p) {
        return p == null ? null : p.parent;
    }

    private static <K, V> RBNode<K, V> leftOf(RBNode<K, V> p) {
        return p == null ? null : p.left;
    }

    private static <K, V> RBNode<K, V> rightOf(RBNode<K, V> p) {
        return p == null ? null : p.right;
    }

    private static <K, V> boolean colorOf(RBNode<K, V> p) {
        return p == null ? BLACK : p.color;
    }

    private static <K, V> void modifyColor(RBNode<K, V> p, boolean color) {
        if (p != null) {
            p.color = color;
        }
    }

    /**
     * 获取一棵树的层数
     * @param root 根节点
     * @return 层数
     */
    public static <K, V> int getTreeDepth(RBNode<K, V> root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    /**
     * 把树的数据写入到列表中 --> 图像化打印树
     * @param currNode 当前节点
     * @param rowIndex 行
     * @param columnIndex 列
     * @param res 存储树中数据的数据
     * @param treeDepth 树的深度
     * @param <K> 键类型
     * @param <V> 值类型
     */
    private static <K, V> void writeArray(RBNode<K, V> currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = "(" + currNode.key + (currNode.color ? "黑" : "红") + ")";

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    /**
     * 图像化打印树
     * @param root 根节点
     * @param <K> 建
     * @param <V> 值
     */
    private static <K, V> void show(RBNode<K, V> root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 图像化打印树
     */
    public void show() {
        System.out.println("==============当前红黑树结构为=================");
        show(root);
        System.out.println("=============================================");
    }

    @Override
    public String toString() {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        visitLNR(root, sb);
        return sb.toString();
    }

    private void visitLNR(RBNode<K, V> node, StringBuilder sb) {
        if (node == null) return;
        visitLNR(node.left, sb);
        sb.append(node.key).append(": ").append(node.value).append("\n");
        visitLNR(node.right, sb);
    }


    /**
     * 红黑树节点的添加
     *
     * @param key 键
     * @param value 值
     */
    public void put(K key, V value) {
        RBNode<K, V> node = new RBNode<>(key, value, null);
        put(node);
    }

    /**
     * 红黑树节点的添加
     * @param node 节点
     */
    public void put(RBNode<K, V> node) {
        if (node.key == null) {
            throw new NullPointerException("不支持键为空的情况");
        }
        // 根节点
        RBNode<K, V> t = root;
        if (t == null) {
            root = node;
            size = 1;
            modifyCount++;
            return;
        }
        // 查找插入位置
        int cmp;
        RBNode<K, V> parent;
        do {
            parent = t;
            cmp = compare(node.key, parent.key);
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                // 这个键在红黑树中已经存在，只需要更换值即可
                parent.value = node.value;
                modifyCount++;
                return;
            }
        } while (t != null);
        // 插入节点的父节点就找到了
        node.parent = parent;
        if (cmp < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        fixAfterPut(node); // 插入之后的调整
        size++;
        modifyCount++;
    }

    /**
     * 红黑树添加之后的调整
     * 1. 根节点              ----------不需要调整
     * 2. 父节点是黑色节点      ----------不需要调整
     * 3. 父节点是红色节点且叔叔节点也是红色节点----------把父节点和叔叔节点改成黑色，祖父节点改成红色，再把祖父节点看成当前节点继续执行
     * 4. 父节点是红色且叔叔节点是黑色或者没有
     *    ---4.1父节点是祖父节点的左字节点
     *          ---4.1.1 插入节点是父节点的左子节点
     *          ---4.2.2 插入节点是父节点的右子节点
     *    ---4.2 父节点是祖父节点的右节点
     *          ---4.2.1 插入节点是父节点的左子节点
     *          ---4.2.2 插入节点是父节点的右子节点
     * @param x
     */
    private void fixAfterPut(RBNode<K, V> x) {

        while (x != root && x.parent.color == RED) {
            // 情况4
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) { // 父 <--- 祖父左
                RBNode<K, V> s = rightOf(parentOf(parentOf(x))); // 叔叔节点
                if (colorOf(s) == RED) { // 叔为红色
                    modifyColor(parentOf(x), BLACK);
                    modifyColor(s, BLACK);
                    modifyColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else { // 叔为黑色
                    if (x == rightOf(parentOf(x))) { // x <--- 父右
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    modifyColor(parentOf(x), BLACK);
                    modifyColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else { // 父 <--- 祖父左或父无祖父
                RBNode<K, V> s = leftOf(parentOf(parentOf(x))); // 叔叔节点
                if (colorOf(s) == RED) { // 叔为红色
                    modifyColor(parentOf(x), BLACK);
                    modifyColor(s, BLACK);
                    modifyColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else { // 叔为黑色
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    modifyColor(parentOf(x), BLACK);
                    modifyColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }


    /**
     * 找到node的前驱节点 前驱节点：小于当前节点值的最大值
     * @param node 当前节点
     */
    private RBNode<K, V> predecessor(RBNode<K, V> node) {
        if (node == null) return null;
        if (node.left != null) {
            RBNode<K, V> t = node.left;
            while (t.right != null) {
                t = t.right;
            }
            return t;
        } else {
            RBNode<K, V> p = node.parent;
            RBNode<K, V> ch = node;
            while (p != null && p.left == ch) {
                p = p.parent;
                ch = ch.parent;
            }
            return p;
        }
    }

    /**
     * 找当前节点的后继节点 后继节点：大于当前节点的最小值
     * @param node 当前节点
     * @return 后继节点
     */
    private RBNode<K, V> successor(RBNode<K, V> node) {
        if (node == null) return null;
        if (node.right == null) {
            RBNode<K, V> p = node.parent;
            RBNode<K, V> ch = node;
            while (p != null && p.right == ch) {
                p = p.parent;
                ch = ch.parent;
            }
            return p;
        } else {
            RBNode<K, V> t = node.right;
            while (t.left != null) {
                t = t.left;
            }
            return t;
        }
    }


    /**
     * 根据键删除节点
     * @param key 键
     * @return 删除节点的值
     */
    public V remove(K key) {
        RBNode<K, V> node = getNode(key); // 查找要删除的节点是否存在
        if (node == null) { // 不存在直接返回
            return null;
        }
        V oldValue = node.value;
        deleteNode(node); // 删除节点
        return oldValue;
    }

    /**
     * 查找键为key 的节点
     * @param key 键
     * @return 要查找的节点
     */
    private RBNode<K, V> getNode(K key) {
        RBNode<K, V> node = this.root;
        while (node != null) {
            int cmp = compare(key, node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                break;
            }
        }
        return node;
    }

    /**
     * 删除
     * <p>
     *     1. 删除节点没有子节点，直接删除
     *     2. 删除节点只有一个子节点，直接用子节点替代
     *     3. 删除节点有两个子节点，需要找前驱节点后者后继节点替代
     * </p>
     * @param node 删除的节点
     */
    private void deleteNode(RBNode<K, V> node) {
        modifyCount++;
        size--;

        // 3. node有两个子节点
        if (node.left != null && node.right != null) {
            RBNode<K, V> successor = successor(node); // 后继节点
            node.key = successor.key;
            node.value = successor.value;
            node = successor;
        }
        // 情况3的后续处理与情况1、2
        RBNode<K, V> replacement = (node.left != null ? node.left : node.right);

        // 替代节点不为空 情况3的后续与情况2
        if (replacement != null) {
            // 替代者的父指针指向原来node的父亲
            replacement.parent = node.parent;
            if (node.parent == null) { // node 为根节点
                root = replacement;
            } else if (node == node.parent.left) {
                // 判断node是其父节点的左孩子还是右孩子
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            // 将node的左右孩子和父指针都指向null, 此时node处于游离状态, 等待垃圾回收
            node.left = node.right = node.parent = null;

            // 替换完之后需要调整平衡
            if (node.color == BLACK) {
                // 需要调整
                // 这种情况一定是红色(替代节点一定是红色, 只需要变色)
                 fixAfterDeletion(replacement);
            }
        } else {
            if (node.parent == null) { // return if we are the only node.
                // 删除节点无子节点且子身为根节点, 即树中只有一个节点
                root = null;
            } else {
                // node为叶子节点 replacement == null
                if (node.color == BLACK) {
                    // 先调整
                     fixAfterDeletion(node);
                }
                // 再删除
                if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }
    }

    /**
     * 删除后的调整
     * @param x 要删除的节点x
     */
    private void fixAfterDeletion(RBNode<K, V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) { // x是父亲的左节点
                RBNode<K, V> b = rightOf(parentOf(x)); // x的兄弟节点

                // 判断此时的兄弟节点是否是真正的兄弟节点(在2-3-4树上)
                if (colorOf(b) == RED) {
                    modifyColor(b, BLACK);
                    modifyColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    // 找到真正的兄弟节点
                    b = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(b)) == BLACK && colorOf(rightOf(b)) == BLACK) { // 找兄弟借， 兄弟没得借
                    modifyColor(b, RED);
                    x = parentOf(x);
                } else { // 找兄弟借, 兄弟有得借
                    // 分两种小情况, 兄弟节点是3节点或4节点
                    if (colorOf(rightOf(b)) == BLACK) { // 兄弟节点的右孩子为空或者黑色
                        // 变色
                        modifyColor(leftOf(b), BLACK);
                        modifyColor(b, RED);
                        //右旋
                        rotateLeft(b);
                        b = rightOf(parentOf(x));
                    }
                    modifyColor(b, colorOf(parentOf(x)));
                    modifyColor(parentOf(x), BLACK);
                    modifyColor(rightOf(b), BLACK);
                    // 对父节点进行左旋
                    rotateLeft(parentOf(x));
                    x = root; // 表示循环结束
                }
            } else {
                // x是父亲的右节点
                RBNode<K, V> b = leftOf(parentOf(x));

                // 判断此时的兄弟节点是否是真正的兄弟节点(2-3-4树的兄弟节点)
                if (colorOf(b) == RED) {
                    modifyColor(parentOf(x), RED);
                    modifyColor(b, BLACK);
                    rotateRight(parentOf(x));
                    b = leftOf(parentOf(x));
                }

                if (colorOf(leftOf(b)) == BLACK && colorOf(rightOf(b)) == BLACK) { // 找兄弟借, 兄弟没得借, 向上递归
                    modifyColor(b, RED);
                    x = parentOf(x);
                } else { // 兄弟有得借
                    if (colorOf(leftOf(b)) == BLACK) {
                        modifyColor(rightOf(b), BLACK);
                        modifyColor(b, RED);
                        rotateLeft(b);
                        b = leftOf(parentOf(x));
                    }
                    modifyColor(b, colorOf(parentOf(x)));
                    modifyColor(parentOf(x), BLACK);
                    modifyColor(leftOf(b), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        // 替代节点是红色直接然后, 补偿删除的黑色节点, 这样红黑树依然保持平衡
        modifyColor(x, BLACK);
    }


    /**
     * 获取传入进来的比较器.
     *<p>
     *     k1 > k2 返回正数
     *     k1 < k2 返回负数
     *     k1 == k2 返回0
     *</p>
     *
     * @return 比较器
     */
    public Comparator<? super K> comparator() {
        return this.comparator;
    }

    final int compare(K k1, K k2) {
        return comparator == null ? ((Comparable<? super K>) k1).compareTo(k2) : comparator.compare(k1, k2);
    }

    final RBNode<K,V> getFirstRBNode() {
        RBNode<K,V> p = root;
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }

    final RBNode<K, V> getLastRBNode() {
        RBNode<K, V> p = root;
        if (p != null)
            while (p.right != null)
                p = p.right;
        return p;
    }

    public Set<RBNode<K,V>> rbNodeSet() {
        return rbNodeSet != null ? rbNodeSet : new RBNodeSet();
    }

    public void clear() {
        root = null;
        modifyCount++;
        size = 0;
    }

    /**
     * RBTree 的一个抽象迭代器
     * <p>Base class for TreeMap Iterators</p>
     * @param <T> 泛型 可为 K, V, RBNode<K, V>
     */
    abstract class PrivateRBNodeIterator<T> implements Iterator<T> {
        RBNode<K, V> next;
        RBNode<K, V> lastReturned;
        int expectedModCount;

        public PrivateRBNodeIterator(RBNode<K, V> first) {
            next = first;
            lastReturned = null;
            expectedModCount = modifyCount;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        final RBNode<K, V> nextRBNode() {
            RBNode<K, V> e = next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            if (modifyCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            next = successor(e);
            lastReturned = e;
            return e;
        }

        final RBNode<K, V> prevRBNode() {
            RBNode<K, V> e = next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            if (modifyCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            next = predecessor(e);
            lastReturned = e;
            return e;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (modifyCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturned.left != null && lastReturned.right != null) {
                next = lastReturned; // 因为真正删除的是lastReturned的后继节点即next
            }
            deleteNode(lastReturned);
            expectedModCount = modifyCount;
            lastReturned = null;
        }
    }

    final class RBNodeIterator extends PrivateRBNodeIterator<RBNode<K, V>> {

        public RBNodeIterator(RBNode<K, V> first) {
            super(first);
        }

        @Override
        public RBNode<K, V> next() {
            return nextRBNode();
        }
    }

    final class ValueIterator extends PrivateRBNodeIterator<V> {

        public ValueIterator(RBNode<K, V> first) {
            super(first);
        }

        @Override
        public V next() {
            return nextRBNode().value;
        }
    }

    final class KeyIterator extends PrivateRBNodeIterator<K> {

        public KeyIterator(RBNode<K, V> first) {
            super(first);
        }

        @Override
        public K next() {
            return nextRBNode().key;
        }
    }

    class RBNodeSet extends AbstractSet<RBNode<K, V>> {

        @Override
        public Iterator<RBNode<K, V>> iterator() {
            return new RBNodeIterator(RBTree.this.getFirstRBNode());
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof RBNode)) {
                return false;
            }
            RBNode<?, ?> rbNode = (RBNode<?, ?>) o;
            Object value = rbNode.getValue();
            RBNode<K, V> p = getNode((K) rbNode.getKey());
            return p != null && Objects.equals(value, p.getValue());
        }

        public int size() {
            return RBTree.this.getSize();
        }
    }




    /**
     * 红黑树节点, 需要用到K值比较，所以传入的K必须为可比较的类型
     * <p>
     * Comparable内部比较器
     *
     * @param <K>
     * @param <V>
     */
    static final class RBNode<K, V> {
        K key;
        V value;
        private RBNode<K, V> left;
        private RBNode<K, V> right;
        private RBNode<K, V> parent;
        private boolean color;

        public RBNode(K key, V value, RBNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.color = RED;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

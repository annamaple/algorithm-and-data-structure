package com.annamaple.utils;

import java.io.Serializable;
import java.util.*;

public class MyTreeMap<K, V> implements Serializable {

    // RED and BLACK
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    // 传入的外部比较器
    private final transient Comparator<K> comparator;
    private transient EntrySet entrySet;
    private transient KeySet keySet;
    private transient Values values;

    private Entry<K, V> root;
    private int size;
    private int modCount;

    public MyTreeMap() {
        this.comparator = null;
    }

    public MyTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return this.size;
    }

    public EntrySet entrySet() {
        return entrySet != null ? entrySet : new EntrySet();
    }

    public KeySet keySet() {
        return keySet != null ? keySet : new KeySet();
    }

    public Values values() {
        return values != null ? values : new Values();
    }

    /**
     * 红黑树的添加
     *
     * @param key   键
     * @param value 值
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalStateException("不支持添加的key为null");
        if (this.root == null) {
            this.root = new Entry<>(key, value);
            this.root.color = BLACK;
            size = 1;
            modCount++;
            return;
        }
        Entry<K, V> t = this.root;
        int cmp;
        Entry<K, V> p;
        do {
            p = t;
            cmp = compare(key, t.key);
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                // 红黑树中已经存在这个键, 即添加变为修改
                t.value = value;
                modCount++;
                return;
            }
        } while (t != null);

        Entry<K, V> node = new Entry<>(key, value);
        node.parent = p;
        if (cmp < 0)
            p.left = node;
        else
            p.right = node;
        fixAfterPut(node);
        size++;
        modCount++;
    }

    /**
     * 红黑树的删除
     *
     * @param key 删除的键
     * @return 删除的键对应值
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalStateException("传入的键不能为null");
        }
        Entry<K, V> entry = get(key);
        if (entry == null) return null;
        deleteEntry(entry);
        return entry.value;
    }

    // 删除节点
    private void deleteEntry(Entry<K, V> x) {
        size--;
        modCount++;

        if (leftOf(x) != null && rightOf(x) != null) {
            // 有两个子节点找后继节点
            Entry<K, V> successor = successor(x);// x的后继节点
            x.key = successor.key;
            x.value = successor.value;
            x = successor;
        }
        Entry<K, V> replacement = x.left != null ? x.left : x.right;
        if (replacement != null) {
            // 有一个子节点
            replacement.parent = x.parent;
            if (x.parent == null) {
                this.root = replacement;
            } else if (x.parent.left == x) {
                x.parent.left = replacement;
            } else {
                x.parent.right = replacement;
            }

            x.parent = x.right = x.left = null;
            if (x.color == BLACK) {
                fixAfterDeletion(replacement);
            }
        } else {
            // 无子节点
            if (x.parent == null) {
                this.root = null; // return if we are the only node.
            } else {
                if (x.color == BLACK) {
                    fixAfterDeletion(x);
                }
                if (x.parent.left == x) {
                    x.parent.left = null;
                } else {
                    x.parent.right = null;
                }
                x.parent = null;
            }
        }
    }


    // 键的比较
    final int compare(K k1, K k2) {
        return this.comparator != null ? this.comparator.compare(k1, k2) : ((Comparable) k1).compareTo(k2);
    }

    private void fixAfterPut(Entry<K, V> x) {
        while (x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) { // 父节点是祖父节点的左子节点
                Entry<K, V> sil = rightOf(parentOf(parentOf(x))); // 叔叔节点
                if (colorOf(sil) == RED) { // 叔叔节点为红色
                    setColor(parentOf(x), BLACK);
                    setColor(sil, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else { // 叔叔节点为黑色或者空
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(parentOf(x));
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry<K, V> sil = leftOf(parentOf(parentOf(x)));
                if (colorOf(sil) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(sil, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(parentOf(x));
                    }
                    setColor(parentOf(parentOf(x)), RED);
                    setColor(parentOf(x), BLACK);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    private void fixAfterDeletion(Entry<K, V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) { // x 是父节点的左子节点
                Entry<K, V> b = rightOf(parentOf(x)); // 兄弟节点
                if (colorOf(b) == RED) {
                    setColor(b, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    b = rightOf(parentOf(x)); // 旋转后重新找到x的兄弟节点
                }
                if (colorOf(leftOf(b)) == BLACK && colorOf(rightOf(b)) == BLACK) {
                    // 兄弟节点没得节点可借, 向上递归
                    setColor(b, RED);
                    x = parentOf(x);
                } else {
                    // 兄弟节点可借
                    if (colorOf(rightOf(b)) == BLACK) {
                        setColor(leftOf(b), BLACK);
                        setColor(b, RED);
                        rotateRight(b);
                        b = rightOf(parentOf(x)); // 旋转后重新找到x的兄弟节点
                    }
                    setColor(b, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(b), BLACK);
                    rotateLeft(parentOf(x));
                    x = this.root;
                }
            } else {
                Entry<K, V> b = leftOf(parentOf(x));
                if (colorOf(b) == RED) {
                    setColor(b, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    b = leftOf(parentOf(x));
                }
                if (colorOf(leftOf(b)) == BLACK && colorOf(rightOf(b)) == BLACK) {
                    setColor(b, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(b)) == BLACK) {
                        setColor(rightOf(b), BLACK);
                        setColor(b, RED);
                        rotateLeft(b);
                        b = leftOf(parentOf(x));
                    }
                    setColor(b, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(b), BLACK);
                    rotateRight(parentOf(x));
                    x = this.root;
                }
            }
        }
        setColor(x, BLACK);
    }

    // 左旋
    private void rotateLeft(Entry<K, V> x) {
        if (x == null) return;
        Entry<K, V> r = x.right;
        x.right = r.left;
        if (r.left != null) {
            r.left.parent = x;
        }
        r.parent = x.parent;
        if (x.parent == null) {
            this.root = r;
        } else if (x.parent.right == x) {
            x.parent.right = r;
        } else {
            x.parent.left = r;
        }
        x.parent = r;
        r.left = x;
    }

    // 右旋
    private void rotateRight(Entry<K, V> x) {
        if (x == null) return;
        Entry<K, V> l = x.left;
        x.left = l.right;
        if (l.right != null) {
            l.right.parent = x;
        }
        l.parent = x.parent;
        if (x.parent == null) {
            this.root = l;
        } else if (x.parent.right == x) {
            x.parent.right = l;
        } else {
            x.parent.left = l;
        }
        x.parent = l;
        l.right = x;
    }

    // 查找节点
    private Entry<K, V> get(K k) {
        if (k == null) throw new IllegalStateException("key can`t allow be null");
        Entry<K, V> p = this.root;
        while (p != null) {
            int cmp = compare(k, p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private Entry<K, V> getFirst() {
        Entry<K, V> t = this.root;
        if (t == null) return null;
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private Entry<K, V> getLast() {
        Entry<K, V> t = this.root;
        if (t == null) return null;
        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    // 查找前驱节点
    private Entry<K, V> predecessor(Entry<K, V> x) {
        if (x == null) return null;
        if (x.left != null) {
            Entry<K, V> node = x.left;
            while (node.right != null)
                node = node.right;
            return node;
        } else {
            Entry<K, V> p = x.parent;
            Entry<K, V> ch = x;
            while (p != null && p.left == ch) {
                p = p.parent;
                ch = ch.parent;
            }
            return p;
        }
    }

    // 查找当前节点的后继节点
    private Entry<K, V> successor(Entry<K, V> x) {
        if (x == null) return null;
        if (x.right != null) {
            Entry<K, V> node = x.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {
            Entry<K, V> p = x.parent;
            Entry<K, V> ch = x;
            while (p != null && p.right == ch) {
                p = p.parent;
                ch = ch.parent;
            }
            return p;
        }
    }

    private Entry<K, V> leftOf(Entry<K, V> x) {
        return x == null ? null : x.left;
    }

    private Entry<K, V> rightOf(Entry<K, V> x) {
        return x == null ? null : x.right;
    }

    private Entry<K, V> parentOf(Entry<K, V> x) {
        return x == null ? null : x.parent;
    }

    private boolean colorOf(Entry<K, V> x) {
        return x == null ? BLACK : x.color;
    }

    private void setColor(Entry<K, V> x, boolean color) {
        if (x != null) {
            x.color = color;
        }
    }


    // 红黑树节点
    static final class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;
        boolean color;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = this.parent = null;
            this.color = RED;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Entry<K, V> getLeft() {
            return left;
        }

        public Entry<K, V> getRight() {
            return right;
        }

        public Entry<K, V> getParent() {
            return parent;
        }

        public boolean isColor() {
            return color;
        }
    }

    // 用于红黑树键值对遍历
    public class EntrySet extends AbstractSet<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator(MyTreeMap.this.getFirst());
        }

        @Override
        public int size() {
            return MyTreeMap.this.size();
        }
    }

    // 用于红黑树键的遍历
    public class KeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator(MyTreeMap.this.getFirst());
        }

        @Override
        public int size() {
            return MyTreeMap.this.size();
        }
    }

    // 用于红黑树值得遍历
    public class Values extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator(MyTreeMap.this.getFirst());
        }

        @Override
        public int size() {
            return MyTreeMap.this.size();
        }
    }


    abstract class PrivateEntryIterator<T> implements Iterator<T> {
        Entry<K, V> next;
        Entry<K, V> lastReturned;
        int expectedModCount;

        public PrivateEntryIterator(Entry<K, V> first) {
            this.next = first;
            lastReturned = null;
            expectedModCount = MyTreeMap.this.modCount;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        public Entry<K, V> nextEntry() {
            Entry<K, V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            next = successor(e);
            lastReturned = e;
            return e;
        }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            if (MyTreeMap.this.modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (lastReturned.left != null && lastReturned.right != null) {
                next = lastReturned;
            }
            deleteEntry(lastReturned);
            expectedModCount = modCount;
            lastReturned = null;
        }
    }

    class EntryIterator extends PrivateEntryIterator<Entry<K, V>> {
        public EntryIterator(Entry<K, V> first) {
            super(first);
        }

        @Override
        public Entry<K, V> next() {
            return nextEntry();
        }
    }

    class KeyIterator extends PrivateEntryIterator<K> {
        public KeyIterator(Entry<K, V> first) {
            super(first);
        }

        @Override
        public K next() {
            Entry<K, V> entry = nextEntry();
            return entry == null ? null : entry.getKey();
        }
    }

    class ValueIterator extends PrivateEntryIterator<V> {
        public ValueIterator(Entry<K, V> first) {
            super(first);
        }

        @Override
        public V next() {
            Entry<K, V> entry = nextEntry();
            return entry == null ? null : entry.getValue();
        }
    }


    /**
     * 获取一棵树的层数
     *
     * @param root 根节点
     * @return 层数
     */
    public static <K, V> int getTreeDepth(Entry<K, V> root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    /**
     * 把树的数据写入到列表中 --> 图像化打印树
     *
     * @param currNode    当前节点
     * @param rowIndex    行
     * @param columnIndex 列
     * @param res         存储树中数据的数据
     * @param treeDepth   树的深度
     * @param <K>         键类型
     * @param <V>         值类型
     */
    private static <K, V> void writeArray(Entry<K, V> currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
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
     *
     * @param root 根节点
     * @param <K>  建
     * @param <V>  值
     */
    private static <K, V> void show(Entry<K, V> root) {
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
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
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
}

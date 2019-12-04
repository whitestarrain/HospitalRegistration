package model;

import java.util.ArrayList;
import java.util.Arrays;

//解决问题： 泛型数组的创建

/* 
哈希表，一个key下面有类似链表结构，用于存储信息，put时会往key下添加信息
 */
class HashRecords<K, V> {
    private class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int h, K k, V v) {
            this.hash = h;
            this.key = k;
            this.value = v;
        }

        public void addNext(Node<K, V> n) {
            next = n;
        }

        @Override
        public boolean equals(Object obj) {
            return key.equals(((Node<K, V>) obj).key);
        }
    }

    int nowcapticy = 0;// 当数组中有一个位置被占用时进行自增操作
    int size = 0;
    public static final int DEFAULT_SIZE = 20;
    private Object[] table;

    public HashRecords() {
        table = new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
    }

    public HashRecords(int size) {
        table = new Object[DEFAULT_SIZE];
        this.size = size;
    }

    public void put(K k, V v) {
        boolean isPut = false;
        Node<K, V> n = new Node(hash(k), k, v);
        int index = indexFor(n.hash, size);
        if (table[index] == null) {// 位置为null直接占用
            nowcapticy++;// 占用位置加一
            table[index] = n;
            return;
        } else if (((Node) table[index]).equals(n)) {// 位置不为null,但key相同，往下加
            Node<K, V> temp = (Node<K, V>) table[index];
            while (temp.next != null) {// 到达最底部
                temp = temp.next;
            }
            temp.addNext(n);
            return;
        } else {// key不同，找key相同的地方
            for (int i = 0; i < size; i++) {
                if (table[(i + index) % (size)] != null && table[(i + index) % (size)].equals(n)) {// 找到key值相同的节点
                    Node<K, V> temp = (Node<K, V>) table[(i + index) % (size)];
                    while (temp != null) {// 到达最底部
                        temp = temp.next;
                    }
                    temp = n;
                    return;
                }
            }
        }
        if (nowcapticy < size) {
            for (int i = 0; i < size; i++) {
                if (isPut == false && table[(index + i) % size] == null) {
                    table[(index + i) % size] = n;
                    isPut = true;
                    nowcapticy++;
                }
                if (isPut) {
                    return;
                }
            }
        } else {
            reSize();
            put(k, v);
        }
    }

    public void delete(K k) {
        for (int i = 0; i < size; i++) {
            if (table[i] != null && table[i].equals(k)) {
                table[i] = null;
                return;
            }
        }
    }

    private void reSize() {
        Object[] old = table;
        Object[] newtable = new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newtable[i] = old[i];
        }
        table = newtable;
        size = size * 2;
    }

    // 仿照HashMap的哈希函数
    static final int hash(Object k) {// 扰动
        int h = k.hashCode();
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int h, int size) {// 算出位置
        return h & (size - 1);
    }

    public String getRecordsToString(K akey) {
        StringBuilder bd = new StringBuilder();
        Node<K, V> temp = null;
        int index = indexFor(hash(akey), size);
        for (int i = 0; i < size - 1; i++) {
            if (((Node<K, V>) table[(index + i) % (size)]) != null
                    && ((Node<K, V>) table[(index + i) % (size)]).key.equals(akey)) {
                temp = ((Node<K, V>) table[(index + i) % (size)]);
                break;
            }
        }

        while (temp != null) {
            bd.append(temp.value.toString() + "\n" + "=============" + "\n");
            temp = temp.next;
        }
        return bd.toString();
    }
}
package model;

import java.io.Serializable;


/* 
哈希表，一个key下面有类似链表结构，用于存储信息，put时会往key下添加信息
 */
class HashRecords<K, V> implements Serializable{
    private static final long serialVersionUID = 2549127321778626242L;
    private static int RESIZE_COUNT = 0;

    private class Node<K, V> implements Serializable{//所有对象将封装到这个类中中
        private static final long serialVersionUID = 3333846326650809393L;
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
    public static int DEFAULT_SIZE = 4;
    private Object[] table;

    public HashRecords() {
        table = new Object[DEFAULT_SIZE];
        size = DEFAULT_SIZE;
    }

    public HashRecords(int size) {
        table = new Object[DEFAULT_SIZE];
        this.size = size;
        DEFAULT_SIZE=size;//以指定的size为基础size
    }

    public void put(K k, V v) {
        if (nowcapticy == size) {
            RESIZE_COUNT++;
            reSize();
            put(k, v);
        }

        Node<K, V> n = new Node(hash(k), k, v);

        int tempsize = DEFAULT_SIZE, tempindex = -1, index = -1;
        for (int i = 0; i <= RESIZE_COUNT; i++) {// 从原始size开始找对应key值位置，找不到tempsize进行扩增
            tempindex = indexFor(n.hash, tempsize);
            if (table[tempindex] == null) {// 位置为null说明这个tempsize对应tempindex并不是
                continue;
            } else if (((Node) table[tempindex]).equals(n)) {// 位置不为null,key相同，说明这个tempsize对应tempindex就是当初装入时的
                index = tempindex;// 找到index
                break;
            } else {// 当key值不相等时，一直往后找，知到到达null。
                for (int z = tempindex; table[z % (size)] != null; z++) {
                    if (((Node) table[z % (size)]).equals(n)) {
                        index = z;// 找到index
                        break;
                    }
                }
            }
            tempsize *= 2;
        }

        if (index == -1) {// 当hash表中没有对应key值时
            index = indexFor(n.hash, size);
            if (table[index] == null) {
                table[index] = n;
                nowcapticy++;
                return;
            } else {
                for (int i = 1; i < size; i++) {
                    if (table[(i + index) % (size)] == null) {
                        table[(i + index) % (size)] = n;
                        nowcapticy++;
                        return;
                    }
                }
            }
        } else {// 当hash表中有对应key值时
            Node temp = (Node) table[index];
            while (temp.next != null) {
                temp = temp.next;// 到达底部
            }
            temp.next = n;
            return;
        }
    }

    public void delete(K k) {//没有使用，需要修改，查找方式通过添加
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
        // 寻找位置
        int tempsize = DEFAULT_SIZE, tempindex = -1, index = -1;
        for (int i = 0; i <= RESIZE_COUNT; i++) {// 从原始size开始找对应key值位置，找不到tempsize进行扩增
            tempindex = indexFor(hash(akey), tempsize);
            if (table[tempindex] == null) {// 位置为null说明这个tempsize对应tempindex并不是
                continue;
            } else if (((Node<K, V>) table[tempindex]).key.equals(akey)) {// 位置不为null,key相同，说明这个tempsize对应tempindex就是当初装入时的
                index = tempindex;// 找到index
                break;
            } else {// 当key值不相等时，一直往后找，知到到达null。
                for (int z = tempindex; table[z % (size)] != null; z++) {
                    if (((Node<K, V>) table[z % (size)]).key.equals(akey)) {
                        index = z;// 找到index
                        break;
                    }
                }
            }
            tempsize *= 2;
        }
        if (index != -1) {
            temp = (Node<K, V>) table[index];
            while (temp != null) {
                bd.append(temp.value.toString() + "\n" + "=============" + "\n");
                temp = temp.next;
            }
        } else {
            bd.append("无");
        }
        return bd.toString();
    }
}
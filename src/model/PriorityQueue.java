package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class PriorityQueue{
    public static int DEFUALT_SIZE=20;
    private Node[] array=null;
    public int size;
    private class Node implements Comparable<Node>{
        private static final int FUZHEN=-5;
        private static final int JIAJI=-20;
        public Patient p;
        public int Priority=50;//这里为小堆，权越大，往后排
        public Node(Patient p){
            this.p=p;
            this.Priority+=size;//越往后，权越大
        }
        public Node(Patient p,int a,int b){//当是否为复诊，急诊，进行对权的修改
            this.p=p;
            this.Priority+=size;
            this.Priority+=a*FUZHEN+b*JIAJI;//当是复诊以及急诊的时候优先级升高
        }

        @Override
        public int compareTo(Node o) {
           return this.Priority-o.Priority;
        }
    }
    public PriorityQueue(){
        this(DEFUALT_SIZE);
    }
    public PriorityQueue(int capacity){
        array=new Node[capacity];
        size=0;
    }
    public PriorityQueue(Patient[] p){
        array=new Node[p.length];
        for(int i=0;i<p.length;i++){
            array[i]=new Node(p[i]);
        }
        buildHeap();
    }
    private void buildHeap() {//整体调整堆结构
        for (int i = size / 2; i > 0; i--)
            percolateDown(i);
    }
    private void percolateDown(int hole) {//调整堆结构
        int child;
        Node tmp = array[hole];
        for (; hole * 2 <= size; hole = child) {
            child = hole * 2;
            if (child != size && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = tmp;
    }
    public void insert(Patient z) {
        Node x=new Node(z);//把病人包装到Node中

        if (size == array.length - 1)//当空间不够时，扩大数组长度
            enlargeArray(array.length * 2 + 1);

        // Percolate up
        int hole = ++size;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }
    public void insert(Patient z,int a,int b){
        Node x=new Node(z,a,b);//把病人包装到Node中

        if (size == array.length - 1)//当空间不够时，扩大数组长度
            enlargeArray(array.length * 2 + 1);

        int hole = ++size;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
            array[hole] = array[hole / 2];
        array[hole] = x;
    }
    private void enlargeArray(int newSize) {//扩大数组长度
        Node[] old = array;
        array = (Node[]) new Node[newSize];
        for (int i = 0; i < old.length; i++)
            array[i] = old[i];
    }
    public Node peekMin() {//弹出最小元素
        if (isEmpty())
            throw new RuntimeException();

        Node minItem = findMin();
        array[1] = array[size--];//将最后一个元素添加到第一个,并且size减一
        percolateDown(1);//然后再重新调整

        return minItem;
    }
    public Node findMin() {//寻找最小元素，也就是第一个
        if (isEmpty())
            throw new RuntimeException();
        return array[1];
    }
    public boolean isEmpty(){
        return size==0;
    }
    @Override
    public String toString() {
        return array.toString();
    }

    public ArrayList<Object> getPriorityQueue(){//上转为Object存储并返回
        ArrayList<Object> a=new ArrayList<Object>();
        for(int i=1;i<=size;i++){
            a.add(array[i].p);
        }
        return a;
    }
}
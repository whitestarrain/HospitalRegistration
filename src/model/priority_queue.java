package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JList;

class priority_queue {// 优先权队列构建
    private ArrayList<Patient> queue;// 按时间顺序过来的所有患者,之后会进行优先级排序
    private ArrayList<Integer> isReview;// 存储0,1，上一个队列处0表示是，1表示否，与queue队列相对应
    private int count = 0;// 记录出队数量
    public boolean ispriority = false;
    public int length;

    public priority_queue() {
        queueload();
        length = queue.size();
        isReview = new ArrayList<Integer>();
    }

    public void queueload() {// 从文件中加载所有患者
        queue = new ArrayList<Patient>();
        BufferedReader read;
        String str;
        String[] arrtemp;
        try {
            read = new BufferedReader(new FileReader("source/waitpatient.txt"));
            while ((str = read.readLine()) != null) {
                arrtemp = str.split(" ");
                queue.add(new Patient(arrtemp[0], arrtemp[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SetToPriorityQueue() {
        // TODO 根据isReview进行排序,使复诊病人均匀插在初诊病人中
        ArrayList<Patient> temp = new ArrayList<Patient>(length);
        ArrayList<Patient> tempNotReviewed = new ArrayList<Patient>();
        ArrayList<Patient> tempHasReviewed = new ArrayList<Patient>();
        for (int i = 0; i < length; i++) {// 将有病与没病的分离
            if (isReview.get(i) == 0) {
                tempHasReviewed.add(queue.get(i));
            } else {
                tempNotReviewed.add(queue.get(i));
            }
        }
        int gap = (int) Math.ceil((double) tempNotReviewed.size() / tempHasReviewed.size());// 获得均匀插入的间隔，最小为1
        for (int i = 0, nj = 0; i < tempHasReviewed.size(); i++) {// i记录加到了哪里
            for (int j = 0; j < gap; nj++, j++) {// j记录加几次，nj记录加到了哪里
                if (nj < tempNotReviewed.size())
                    temp.add(tempNotReviewed.get(nj));
            }
            temp.add(tempHasReviewed.get(i));
        }
        ispriority = true;
        queue = temp;
    }
    // public void addToPriotityQueue(){
    // TODO 也许可以这样实现，在每次如的时候进行排列一次
    // }

    public ArrayList<Patient> getqueue() {
        return queue;
    }

    public ArrayList<Integer> getIsReview() {
        return isReview;
    }

    public Patient pop() {// 得到队列中的下一个病人
        if (count == queue.size())
            return null;
        return queue.remove(count++);
    }

    // public JList getListQueue(){
    // JList l=new JList();
    // ArrayList<String> a=new ArrayList<String>();
    // for(Patient p:queue){
    // a.add(p.getName()+":"+p.getID());
    // }
    // l.setListData(a.toArray());
    // return l;
    // }
    public void reload() {// 当一个队列结束后重新载入
        queueload();
    }

}
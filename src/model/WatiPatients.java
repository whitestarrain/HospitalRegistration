package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//次类用来模拟就诊的患者
class WaitPatients {
    private ArrayList<Patient> queue;

    public WaitPatients() {
        queue = new ArrayList<Patient>();
        queueload();
    }

    public void queueload() {// 从文件中加载所有患者
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

    public int getsize() {
        return queue.size();
    }

    public Patient getIndexOf(int i) {
        return queue.get(i);
    }

    public ArrayList<Patient> getAllPatients() {
        return queue;
    }
}
package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

public class Structure {
    private diseasesTree diseases;
    private HashMap<String, Medicine> medicines;
    private HashMap<String, Patient> patients;
    private HashMap<String, Doctor> doctors;
    private ArrayList<Records> recoders;
    private priority_queue queue;// 排队病人

    public Structure() {
        queue = new priority_queue();
        diseases = new diseasesTree();

        // 其他数据通过HashmMap就行了，不需要重新写新类
        try {
            ObjectInputStream in = null;
            in = new ObjectInputStream(new FileInputStream("objectfiles/doctors.HashMap"));
            doctors = (HashMap<String, Doctor>) in.readObject();
            in.close();
            in = new ObjectInputStream(new FileInputStream("objectfiles/medicines.HashMap"));
            medicines = (HashMap<String, Medicine>) in.readObject();
            in.close();
            in = new ObjectInputStream(new FileInputStream("objectfiles/patients.HashMap"));
            patients = (HashMap<String, Patient>) in.readObject();
            in.close();
            in = new ObjectInputStream(new FileInputStream("objectfiles/records.ArrayList"));
            recoders = (ArrayList<Records>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("structure中成员从序列化文件中初始化失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DefaultMutableTreeNode getJTreeroot() {
        return diseases.getJRoot();
    }

    public void treeTrverse(DefaultMutableTreeNode t, JTextArea a) {// 对外提供病类型遍历方法。
        diseasesTree.Trverse(t, a);
    }

    public void treeTrverse_noPatient(DiseaseType t, JTextArea a) {
        if(t==null){
            JOptionPane.showMessageDialog(a, "不存在该病种", "", 0);
            return;
        }
        diseasesTree.Trverse_noPatient(t, a);
    }

    public void treeTrverse(DiseaseType t, JTextArea a) {
        if(t==null){
            JOptionPane.showMessageDialog(a, "不存在该病种", "", 0);
            return;
        }
        diseasesTree.Trverse(t, a);
    }

    public diseasesTree gDiseasesTree() {
        return diseases;
    }

    public DiseaseType searchDiseasesType(String s) {
        return diseases.getDisease(s);
    }

    public ArrayList<Patient> getNoProQueue() {// 得到未经排序的病人队列
        return queue.getqueue();
    }

    public int getqueuelength() {// 获得总排队病人数量
        return queue.length;
    }

    public ArrayList<Integer> getIsReview() {// 获得是否为复诊的队列
        return queue.getIsReview();
    }

    public void SetToPriorityQueue() {
        queue.SetToPriorityQueue();
    }

}

class diseasesTree {// 病种树类，使用HashMap存储
    private HashMap<String, DiseaseType> diseases;
    DiseaseType whichToSearch = null;
    private DiseaseType root;
    private DefaultMutableTreeNode jRoot;

    public diseasesTree() {// 从默认序列文件中读取数据初始化对象
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("objectfiles/diseases.HashMap"));
            diseases = (HashMap<String, DiseaseType>) in.readObject();
            in.close();
            in = new ObjectInputStream(new FileInputStream("objectfiles/root.DiseaseType"));
            root = (DiseaseType) in.readObject();
        } catch (Exception e) {
            System.out.println("序列化文件加载失败");
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        jRoot = new DefaultMutableTreeNode(root);// 以“病”为JTreeroot节点
        setjRoot(jRoot);// 往jRoot上添加子节点
    }

    public void Trverse() {// 每棵树本身从头遍历
        diseasesTree.Trverse(this.root);
    }

    static void Trverse(DiseaseType t) {// 对外遍历方法，输出到控制台
        if (t.patient != null) {
            // System.out.println(t.name);
            for (String tempString : t.patient) {

            }
            // System.out.println(tempString);
        }

        if (t.subDiseaseTypes != null) {
            // System.out.println(t.name);
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                Trverse(tempDiseaseType);
            }
        }

    }

    static void Trverse(DiseaseType t, JTextArea a) {// 对外遍历方法，输出到指定JTextArea
        if (t.patient != null) {
            a.append(t.name + "\n");
            for (String tempString : t.patient)
                a.append(tempString + "\n");
        }

        if (t.subDiseaseTypes != null) {
            a.append(t.name + "\n");
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                Trverse(tempDiseaseType, a);
            }
        }
    }


    static void Trverse(DefaultMutableTreeNode t, JTextArea a) {// 当参数为DefaultMutableTreeNode时
        DiseaseType temp = (DiseaseType) t.getUserObject();
        diseasesTree.Trverse(temp, a);
    }

    static void Trverse_noPatient(DiseaseType t, JTextArea a) {//不返回病人的遍历方法
        if (t.subDiseaseTypes != null) {
            a.append(t.name + "\n");
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                Trverse_noPatient(tempDiseaseType, a);
            }
        }
    }

    public void setjRoot(DefaultMutableTreeNode t) {// 将HashMap遍历转换为JTree,这里只是在对根节点进行添加
        DiseaseType temp;
        if ((temp = ((DiseaseType) t.getUserObject())).subDiseaseTypes != null) {
            for (DiseaseType tempDiseaseType : temp.subDiseaseTypes) {
                DefaultMutableTreeNode tempDefaultMutableTreeNode = new DefaultMutableTreeNode(tempDiseaseType);
                t.add(tempDefaultMutableTreeNode);
                setjRoot(tempDefaultMutableTreeNode);
            }
        } else {
            return;
        }
    }

    public void dataFlush() {// 当数据有所改变时覆盖重写序列文件

    }

    public DefaultMutableTreeNode getJRoot() {
        return jRoot;
    }

    public DiseaseType getDisease(String s) {//查找病类
        if(whichToSearch!=null&&s!=whichToSearch.name){//当再次查找时如果是同一个，则按步骤查找，否则还原whichtosearch为null
            whichToSearch=null;
        }
        getDisease(root, s);
        return whichToSearch;
    }

    private void getDisease(DiseaseType t, String s) {//查找病类
        if (t.name.equals(s)) {
            whichToSearch = t;
            return;
        }
        if (t.subDiseaseTypes != null) {
            // System.out.println(t.name);
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                getDisease(tempDiseaseType, s);
            }
        }
    }
}

class priority_queue {// 优先权队列构建
    private ArrayList<Patient> queue;// 按时间顺序过来的所有患者,之后会进行优先级排序
    private ArrayList<Integer> isReview;// 存储0,1，上一个队列处0表示是，1表示否，与queue队列相对应
    private boolean ispriority = false;
    public int length;

    public priority_queue() {
        queueload();
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
        length = queue.size();
    }

    public void SetToPriorityQueue() {
        // TODO 根据isReview进行排序

        ispriority = true;
    }

    public ArrayList<Patient> getqueue() {
        return queue;
    }

    public ArrayList<Integer> getIsReview() {
        return isReview;
    }

}
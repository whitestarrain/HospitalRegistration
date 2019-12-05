package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

public class Structure {
    private diseasesTree diseases;// 只在更新文件时用了下，其他时候没动过
    private static ArrayList<Medicine> medicines;// 在创建第一个类成员时初始化
    private static ArrayList<Doctor> doctors;
    private static HashRecords<String, Records> hashrecords;
    private HashMap<String, Patient> patients;
    private PriorityQueue priorityQueue;
    private WaitPatients waitPatients;

    static {
        try {
            ObjectInputStream in = null;
            in = new ObjectInputStream(new FileInputStream("objectfiles/doctors.ArrayList"));
            doctors = (ArrayList<Doctor>) in.readObject();
            in.close();
            in = new ObjectInputStream(new FileInputStream("objectfiles/medicines.ArrayList"));
            medicines = (ArrayList<Medicine>) in.readObject();
            in.close();
            in = new ObjectInputStream(new FileInputStream("objectfiles/HashRecords"));
            hashrecords = (HashRecords<String, Records>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("structure中静态成员从序列化文件中初始化失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Structure() {
        diseases = new diseasesTree();
        waitPatients = new WaitPatients();
        priorityQueue = new PriorityQueue();
        // 其他数据通过HashmMap就行了，不需要重新写新类
        try {
            ObjectInputStream in = null;
            in = new ObjectInputStream(new FileInputStream("objectfiles/patients.HashMap"));
            patients = (HashMap<String, Patient>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("structure中成员从序列化文件中初始化失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getDoctorName(String ID) {
        for (Doctor d : doctors) {
            if (d.getID().equals(ID)) {
                return d.getName();
            }
        }
        return "未知";
    }

    public static String getMdedicineName(String ID) {
        for (Medicine d : medicines) {
            if (d.getID().equals(ID)) {
                return d.getName();
            }
        }
        return "未知";
    }

    public DefaultMutableTreeNode getJTreeroot() {
        return diseases.getJRoot();
    }

    public void treeTrverse(DefaultMutableTreeNode t, JTextArea a) {// 对外提供病类型遍历方法。
        diseasesTree.Trverse(t, a);
    }

    public void treeTrverse_noPatient(DiseaseType t, JTextArea a) {// 仅列出病种
        if (t == null) {
            JOptionPane.showMessageDialog(a, "不存在该病种", "", 0);
            return;
        }
        diseasesTree.Trverse_noPatient(t, a);
    }

    public void treeTrverse(DiseaseType t, JTextArea a) {
        if (t == null) {
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

    public DefaultMutableTreeNode addSubDis(DefaultMutableTreeNode par, String s) {// 返回子病对应节点
        return new DefaultMutableTreeNode(diseases.addSubDis((DiseaseType) par.getUserObject(), s));
    }

    public void writeToFile_Tree() {
        diseases.reWriteFile();
    }

    public boolean hasTreeMoidfy() {
        return diseases.hasModify();
    }

    public void deleNode(DefaultMutableTreeNode a) {
        diseases.deleteNode((DiseaseType) a.getUserObject());
    }

    public String allPatientToString(DiseaseType dis, String Id_or_Name) {// 堆病人进行排序
        ArrayList<String> allPatient = diseases.GetAllPatient(dis);
        if (Id_or_Name.equals("Id")) {// 按ID排序,此处使用简单插入排序
            for (int i = 1; i < allPatient.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (allPatient.get(i).compareTo(allPatient.get(j)) < 0) {
                        allPatient.add(j, allPatient.get(i));
                        allPatient.remove(i + 1);// FIXME已解决 这里要加个1，因为前面有值插入，所以原位置变化
                    }
                }
            }
        }
        if (Id_or_Name.equals("Name")) {// 按Name排序，这里使用希尔排序
            ShellSort(allPatient);
        }
        StringBuffer sb = new StringBuffer();
        for (String s : allPatient) {// 转换为字符串输出
            sb.append(s + ":" + patients.get(s).getName() + "\n");
        }
        return sb.toString();
    }

    private void ShellSort(ArrayList<String> arr) {
        int gap = arr.size() / 2;
        while (gap != 0) {// 循环到gap为0
            ShellSort(gap, arr);// 一直插入排序
            gap = (gap == 2) ? 1 : (int) (gap / 2.2);
        }
    }

    private void ShellSort(int gap, ArrayList<String> arr) {
        // 对一个gap进行冒泡排序
        String temp = null;
        for (int i = 0; i < arr.size(); i += gap) {
            for (int j = i + gap; j < arr.size(); j += gap) {
                if (((patients.get(arr.get(j)).getName()).compareTo((patients.get(arr.get(i)).getName()))) < 0) {
                    temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
        }
    }

    public String allPatientToString(DefaultMutableTreeNode dis, String Id_or_Name) {
        DiseaseType d = (DiseaseType) (dis.getUserObject());
        return allPatientToString(d, Id_or_Name);
    }

    public JComboBox<Object> getDoctorComboBox() {
        JComboBox<Object> j = new JComboBox<Object>();
        j.addItem("-请选择-");
        for (Doctor d : doctors) {
            j.addItem(d);
        }
        return j;
    }

    public JComboBox<Object> getMedicineComboBox() {
        JComboBox<Object> j = new JComboBox<Object>();
        j.addItem("-请选择-");
        for (Medicine m : medicines) {
            j.addItem(m);
        }
        return j;
    }

    public int getWaitPatientsSize() {
        return waitPatients.getsize();
    }

    public void addWaitPatients(Patient p, int a, int b) {
        priorityQueue.insert(p, a, b);
    }

    public String getWaitPatientsIndexOf(int i) {
        return waitPatients.getIndexOf(i).toString();
    }

    public void addWaitPatientstoQueue(int i, int a, int b) {
        priorityQueue.insert(waitPatients.getIndexOf(i), a, b);
    }

    public ArrayList<Object> getWaitPaitent_Priority() {
        return priorityQueue.getPriorityQueue();
    }

    public String getRecordsToStringByPatient(Object pat) {
        return hashrecords.getRecordsToString(((Patient) pat).getID());
    }

    public void queue_peekmin() {
        priorityQueue.peekMin();
    }

    public int getMedicineNumber(Object medicine) {
        return ((Medicine) medicine).number;
    }

    private void flushAlls(Object patient, Object medicine, Object doctor, DefaultMutableTreeNode dis, String memo,
            int number) {

        Date a = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
        String time = s.format(a);

        Records r = new Records(((Patient) patient).getID(), time, ((Doctor) doctor).getID(),
                ((Medicine) medicine).getID(), memo);

        hashrecords.put(r.getPatientID(), r);
        DiseaseType temp = (DiseaseType) (dis.getUserObject());

        boolean ishas = false;
        for (String str : temp.patient) {
            if (str.equals(((Patient) patient).getID()))
                ishas = true;
        }
        if (!ishas)// 当不包括病人ID时再加入
            temp.addpatient(((Patient) patient).getID());

        ((Medicine) medicine).number -= number;// 药品数量减少

        if (patients.get(((Patient) patient).getID()) == null)// 当不存在该病人id时
            patients.put(((Patient) patient).getID(), (Patient) patient);// 因为给定的病种定义下只储存了病人ID，因此刷新为查找对应对象的HashMap
    }

    public void flushAllFile(Object patient, Object medicine, Object doctor, DefaultMutableTreeNode dis, String memo,
            int number) {
        flushAlls(patient, medicine, doctor, dis, memo, number);

        // TODO更新文件
        try {
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/root.DiseaseType"));
            out.writeObject(diseases.getroot());
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/medicines.ArrayList"));
            out.writeObject(medicines);
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/patients.HashMap"));
            out.writeObject(patients);
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/HashRecords"));
            out.writeObject(hashrecords);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException("文件更新失败");
        }
    }
}

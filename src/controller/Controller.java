package controller;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Structure;
import registerview.MainView;

public class Controller {
    private Structure structure;
    private MainView view;

    public Controller() {
        structure = new Structure();
        view = new MainView(this);
    }

    public boolean hasDiseaseTreeModify() {
        return structure.hasTreeMoidfy();
    }

    public void writeToFile_Tree() {//将更改后的树文件重写到硬盘
       structure.writeToFile_Tree();
    }

    public void treeTrverse(DefaultMutableTreeNode t, JTextArea a) {// 对外提供病类型遍历方法。
        structure.treeTrverse(t, a);
    }

    public DefaultMutableTreeNode addSubDis(DefaultMutableTreeNode par, String s) {// 添加节点方法
        return structure.addSubDis(par, s);
    }

    public void deleNode(DefaultMutableTreeNode a) {// 删除节点方法
        structure.deleNode(a);
    }

    public DefaultMutableTreeNode getJTreeRoot() {//获得病树根节点
        return structure.getJTreeroot();
    }

    public String patientToString(DefaultMutableTreeNode dis, String Id_or_Name) {//将病人列表转换为String返回

        return structure.allPatientToString(dis, Id_or_Name);
    }

    public JComboBox<Object> getMedicineComboBox() {//得到药品对应ComboBox
        return structure.getMedicineComboBox();
    }

    public JComboBox<Object> getDoctorComboBox() {//得到医生对应ComboBox
        return structure.getDoctorComboBox();
    }

    public int getMedicineNumber(Object medicine) {//获得药品数量
        return structure.getMedicineNumber(medicine);
    }

    public int getWaitPatientsLength() {//获得等待病人的数量
        return structure.getWaitPatientsSize();
    }

    public String getTheIndexOfPatients(int i) {//根据index获取等待病人
        return structure.getWaitPatientsIndexOf(i);
    }

    public void addWaitPatientsToQueue(int i, int a, int b) {//将病人添加的到优先权队列
        structure.addWaitPatientstoQueue(i, a, b);
    }

    public ArrayList<Object> getWaitPaitent_Priority() {//获得优先权队列的ArrrayList形式（未排序）
        return structure.getWaitPaitent_Priority();
    }

    public String getRecordsToString(Object pat) {//根据病人获得就医记录
        return structure.getRecordsToStringByPatient(pat);
    }

    public void queue_peekmin() {//优先权队列出队
        structure.queue_peekmin();
    }

    public void flushFile(Object patient, Object medicine, Object doctor, DefaultMutableTreeNode dis, String memo,
            int number) {//文件刷新
        structure.flushAllFile(patient, medicine, doctor, dis, memo, number);
    }

    public Structure gStructure() {
        return structure;
    }

    public MainView gMainView() {
        return view;
    }
}
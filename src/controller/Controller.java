package controller;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
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

    public DefaultMutableTreeNode addSubDis(DefaultMutableTreeNode par, String s) {// 添加节点方法
        return structure.addSubDis(par, s);
    }

    public void deleNode(DefaultMutableTreeNode a) {// 删除节点方法
        structure.deleNode(a);
    }

    public DefaultMutableTreeNode getJTreeRoot() {
        return structure.getJTreeroot();
    }

    public String patientToString(DefaultMutableTreeNode dis, String Id_or_Name) {

        return structure.allPatientToString(dis, Id_or_Name);
    }

    public JComboBox getMedicineComboBox() {
        return structure.getMedicineComboBox();
    }

    public JComboBox getDoctorComboBox() {
        return structure.getDoctorComboBox();
    }

    public int getMedicineNumber(Object medicine){
        return structure.getMedicineNumber(medicine);
    }
    public int getWaitPatientsLength() {
        return structure.getWaitPatientsSize();
    }

    public String getTheIndexOfPatients(int i) {
        return structure.getWaitPatientsIndexOf(i);
    }

    public void addWaitPatientsToQueue(int i, int a, int b) {
        structure.addWaitPatientstoQueue(i, a, b);
    }

    public ArrayList<Object> getWaitPaitent_Priority() {
        return structure.getWaitPaitent_Priority();
    }

    public String getRecordsToString(Object pat) {
        return structure.getRecordsToStringByPatient(pat);
    }

    public void queue_peekmin() {
        structure.queue_peekmin();
    }

    public void flushFile(Object patient, Object medicine, Object doctor, DefaultMutableTreeNode dis, String memo,
            int number) {
                structure.flushAllFile(patient, medicine, doctor, dis, memo, number);
    }
    public Structure gStructure() {
        return structure;
    }

    public MainView gMainView() {
        return view;
    }
}
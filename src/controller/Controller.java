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

    public String getRecoders(String PatientID) {
        return structure.getRecoder(PatientID);
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

    public ArrayList<String> getQueue() {
        return structure.getqueue();
    }

    // public void initData(Structure structure,MainView view){//将view中的数据初始化
    // initAdministatorData(structure, view);
    // initTriageData(structure, view);
    // initTreatMentData(structure, view);
    // }
    // public void initAdministatorData(Structure structure,MainView view){

    // }
    // public void initTriageData(Structure structure,MainView view){

    // }
    // public void initTreatMentData(Structure structure,MainView view){

    // }
    public Structure gStructure() {
        return structure;
    }

    public MainView gMainView() {
        return view;
    }
}
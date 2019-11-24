package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JFrame;
import javax.swing.JTree;

public class Structure {
    private diseasesTree diseases;
    private HashMap<String, Medicine> medicines;
    private HashMap<String, Patient> patients;
    private HashMap<String, Doctor> doctors;
    private ArrayList<Records> recoders;
    private JTree jTree;
    // TODO

    public Structure() {
        diseases = new diseasesTree();// 初始化病种树类

        jTree=new JTree(diseases.getJRoot());
        // 这里要new一个JTree（dise.getJRoot）

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public JTree getJTree(){
        return jTree;
    }
    public DefaultMutableTreeNode getJTreeroot(){//FIXME MAYBEDELE
        return diseases.getJRoot();
    }
}

class diseasesTree {// 病种树类，使用HashMap存储
    private HashMap<String, DiseaseType> diseases;
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

        jRoot = new DefaultMutableTreeNode(root);//以“病”为JTreeroot节点
        setjRoot(jRoot);//往jRoot上添加子节点
    }

    public void Trverse(DiseaseType t) {
        if (t.patient != null) {
            System.out.println(t.name);
            for (String tempString : t.patient)
                System.out.println(tempString);
        }

        if (t.subDiseaseTypes != null) {
            System.out.println(t.name);
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                Trverse(tempDiseaseType);
            }
        }

    }

    public void setjRoot(DefaultMutableTreeNode t) {// 将HashMap遍历转换为JTree
        DiseaseType temp;
        if((temp=((DiseaseType)t.getUserObject())).subDiseaseTypes!=null){
            for(DiseaseType tempDiseaseType:temp.subDiseaseTypes){
                DefaultMutableTreeNode tempDefaultMutableTreeNode=new DefaultMutableTreeNode(tempDiseaseType);
                t.add(tempDefaultMutableTreeNode);
                setjRoot(tempDefaultMutableTreeNode);
            }
        }else{
            return;
        }
    }

    public void dataFlush() {// 当数据有所改变时覆盖重写序列文件

    }

    public DefaultMutableTreeNode getJRoot(){
        return jRoot;
    }

    
    public static void main(String[] args) {//FIXME DELETEME
        diseasesTree a = new diseasesTree();
        System.out.println(a.getJRoot());
       JFrame j= new JFrame();
       j.setBounds(200, 300, 600, 500);
       j.setVisible(true);
       j.add(new JTree(a.getJRoot()));
    }
}
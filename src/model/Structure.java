package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

public class Structure {
    private diseasesTree diseases;// 只在更新文件时用了下，其他时候没动过
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
}

class diseasesTree {// 病种树类，使用HashMap存储
    private HashMap<String, DiseaseType> diseasesMap;// 只有在移除和添加元素时使用了HashMap方法，其他情况下都没用
    DiseaseType whichToSearch = null;
    private DiseaseType root;
    private DefaultMutableTreeNode jRoot;
    private int number = 0;// 嗯，，，优化了添加算法似乎不需要这个了
    private boolean hasModify = false;

    public diseasesTree() {// 从默认序列文件中读取数据初始化对象
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("objectfiles/diseases.HashMap"));
            diseasesMap = (HashMap<String, DiseaseType>) in.readObject();
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
        Trverse();// 初始化number数量
        // System.out.println(number);
        // System.out.println(diseasesMap);
        // System.out.println();
    }

    public void Trverse() {// 每棵树本身从头遍历
        Trverse(this.root);
    }

    private void Trverse(DiseaseType t) {// 初始化病的数量，为ID编号方便
        number++;
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
        a.append(t.name + "\n");
        if (t.patient != null) {
            for (String tempString : t.patient)
                a.append(tempString + "\n");
        }

        if (t.subDiseaseTypes != null) {
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                Trverse(tempDiseaseType, a);
            }
        }
    }

    static void Trverse(DefaultMutableTreeNode t, JTextArea a) {// 当参数为DefaultMutableTreeNode时
        DiseaseType temp = (DiseaseType) t.getUserObject();
        diseasesTree.Trverse(temp, a);
    }

    static void Trverse_noPatient(DiseaseType t, JTextArea a) {// 不返回病人的遍历方法
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

    public DefaultMutableTreeNode getJRoot() {
        return jRoot;
    }

    public DiseaseType getDisease(String s) {// 查找病类
        if (whichToSearch != null && s != whichToSearch.name) {// 当再次查找时如果是同一个，则按步骤查找，否则还原whichtosearch为null
            whichToSearch = null;
        }
        getDisease(root, s);
        return whichToSearch;
    }

    private void getDisease(DiseaseType t, String s) {// 查找病类
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

    public String getLdleID() {// 获得闲置ID，可能是中间删除后的，也可能是最后一个
        int i = 201;
        while (true) {
            if (getDiseaseByID(String.valueOf(i)) == null) {
                return i + "";
            }
            i++;
        }
    }

    public DiseaseType addSubDis(DiseaseType parentDis, String subDisNmae) {// 在parentDis节点下面添加subDisName病种,并返回实例化对象的引用
        DiseaseType tempdis = getDisease(parentDis.name);// 得到父病种
        DiseaseType temp = new DiseaseType(getLdleID(), subDisNmae, tempdis.ID);// 实例化子病种
        tempdis.addsubdesease(temp);// 父病种中添加子病种引用 //一开始这里反了
        // diseasesMap.put(tempdis.getID(),tempdis);//FIXME 不知道为什么和tempdis中的不一样
        // 不是这里的错误，是root忘了重写
        diseasesMap.put(temp.getID(), temp);// 更新HashMap
        // System.out.println(diseasesMap);
        hasModify = true;// 将是否修改标记改为true
        return temp;
    }

    public boolean hasModify() {// 返回是否修改树
        return hasModify;
    }

    public void reWriteFile() {// 更新文件
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/diseases.HashMap"));
            out.writeObject(diseasesMap);
            out.close();
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/root.DiseaseType"));
            out.writeObject(root);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件更新异常");
        } finally {
        }
        // System.out.println(diseasesMap);
        hasModify=false;
    }

    public DiseaseType getDiseaseByID(String ID) {// 单参数查找
        return getDiseaseByID(root, ID);
    }

    public DiseaseType getDiseaseByID(DiseaseType t, String s) {// 根据ID查找，比根据name查找的那个算法要优化些
        if (t.getID().equals(s)) {
            return t;
        }
        if (t.subDiseaseTypes != null) {
            // System.out.println(t.name);
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {

                if (getDiseaseByID(tempDiseaseType, s) != null) {
                    return getDiseaseByID(tempDiseaseType, s);
                }
            }
        }
        return null;
    }

    public void deleteNode(DiseaseType dele) {// 文件系统中删除，需要移除父病种对其引用，移除HashMap中的元素
        if(dele.subDiseaseTypes!=null){//当为病种文件夹时，先删除子病，再删除该病
            for(DiseaseType temp:dele.subDiseaseTypes){
                deleteNode(temp);
            }
        }
        DiseaseType parDiseaseType = getDiseaseByID(dele.getParID());
        parDiseaseType.removeSubDis(dele);// 移除父病种对其引用
        diseasesMap.remove(dele.getID());// 没办法,HashMap的移除机制不清楚，应该使用ArrayList来存储的的
        hasModify = true;
    }
}

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
        int gap = (int) Math.floor((double) tempNotReviewed.size() / tempHasReviewed.size());// 获得均匀插入的间隔，最小为1
        for (int i = 0, it = 0; i < tempNotReviewed.size(); it++) {
            for (int j = 0; j < gap; j++) {
                temp.add(tempNotReviewed.get(i));
                i++;
                j++;
            }
            temp.add(tempHasReviewed.get(it));
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
        return queue.get(count++);
    }

    public void reload() {// 当一个队列结束后重新载入
        queueload();
    }

}
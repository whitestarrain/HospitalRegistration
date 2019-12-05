package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

class diseasesTree {// 病种树类，使用HashMap存储
    DiseaseType whichToSearch = null;
    private DiseaseType root;
    private DefaultMutableTreeNode jRoot;
    private boolean hasModify = false;
    private ArrayList<String> AllPatient = null;// 用来临时保存某病种的所有病人ID

    public diseasesTree() {// 从默认序列文件中读取数据初始化对象
        ObjectInputStream in = null;
        try {
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
        a.append(t.name + "\n");
        if (t.subDiseaseTypes != null) {
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

    public DiseaseType getDisease(String s) {// 根据病名称查找病类
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
        // System.out.println(diseasesMap);
        hasModify = true;// 将是否修改标记改为true
        return temp;
    }

    public boolean hasModify() {// 返回是否修改过树
        return hasModify;
    }

    public void reWriteFile() {// 更新文件
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("objectfiles/root.DiseaseType"));
            out.writeObject(root);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件更新异常");
        } finally {
        }
        // System.out.println(diseasesMap);
        hasModify = false;
    }

    public DiseaseType getDiseaseByID(String ID) {// 单参数查找
        return getDiseaseByID(root, ID);
    }

    public DiseaseType getDiseaseByID(DiseaseType t, String s) {// 根据ID查找，比根据name查找的那个算法要优化些，不需要变量，但更费事件
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

    public void deleteNode(DiseaseType dele) {// 文件系统中删除，需要移除父病种对其引用
        if (dele.subDiseaseTypes != null) {// 当为病种文件夹时，先删除子病，再删除该病

            Iterator<DiseaseType> it = dele.subDiseaseTypes.iterator();
            if (dele.subDiseaseTypes.size() > 0 && it.hasNext()) {// 使用迭代器，从而有判断size的地方
                deleteNode(it.next());
            }

            // for(DiseaseType temp:dele.subDiseaseTypes){// for循环，或者迭代时修改容器中元素导致错误
            // deleteNode(temp);
            // }
        }
        DiseaseType parDiseaseType = getDiseaseByID(dele.getParID());
        parDiseaseType.removeSubDis(dele);// 移除父病种对其引用
        hasModify = true;
    }

    private void getAllPatient(DiseaseType t) {
        if (t.patient != null) {
            for (String s : t.patient) {
                AllPatient.add(s);
            }
        }

        if (t.subDiseaseTypes != null) {
            // System.out.println(t.name);
            for (DiseaseType tempDiseaseType : t.subDiseaseTypes) {
                getAllPatient(tempDiseaseType);
            }
        }
    }

    public ArrayList<String> GetAllPatient(DiseaseType t) {
        AllPatient = new ArrayList<String>();
        getAllPatient(t);
        return AllPatient;
    }

    DiseaseType getroot() {
        return root;
    }
}
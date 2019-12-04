package model;

import java.io.Serializable;
import java.util.ArrayList;

class DiseaseType implements Serializable {// 病类
    private static final long serialVersionUID = 5881895164579111309L;
    String ID;
    String name;
    String parentID;// 上一级病的ID
    ArrayList<DiseaseType> subDiseaseTypes;// 子病种类型
    ArrayList<String> patient;// 存储的所有病人ID

    public DiseaseType(String ID, String name, String parentID, ArrayList<DiseaseType> subDieaseTypes,
            ArrayList<String> patient) {// 构造方法
        this.ID = ID;
        this.name = name;
        this.parentID = parentID;
        this.subDiseaseTypes = subDieaseTypes;
        this.patient = patient;
    }

    public DiseaseType(String ID, String name, String parentID) {// 构造方法，初始化ID，name，parentID
        this.ID = ID;
        this.name = name;
        this.parentID = parentID;
        this.subDiseaseTypes = new ArrayList<DiseaseType>();
        this.patient = new ArrayList<String>();
    }

    void addsubdesease(DiseaseType... darr) {// 添加子病
        for (DiseaseType di : darr) {
            subDiseaseTypes.add(di);
        }
        return;
    }

    void addpatient(String... strarr) {// 添加患病病人
        for (String temp : strarr) {
            this.patient.add(temp);
        }
        return;
    }

    public String toString() {
        return name;
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object obj) {// 重写equals，当ID相等就认为相同
        return ID.equals(((DiseaseType) obj).getID());
    }

    public boolean removeSubDis(DiseaseType dele) {// 删除某子病种
        return subDiseaseTypes.remove(dele);
    }

    public String getParID() {
        return parentID;
    }
}
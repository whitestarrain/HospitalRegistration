package model;

import java.io.Serializable;
import java.util.ArrayList;

class Patient implements Serializable {// 病人
    /**
     *
     */
    private static final long serialVersionUID = 7176142781355435457L;
    private String ID;
    private String name;

    public Patient(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "病人 " + ID + " : " + name;
    }

    public int hashCode() {
        return Integer.valueOf(ID);
    }
}

class Doctor implements Serializable {// 医生
    /**
     *
     */
    private static final long serialVersionUID = 8735072010302994784L;
    private String ID;
    private String name;

    public Doctor(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "医生 " + ID + ":" + name;
    }

    public int hashCode() {
        return Integer.valueOf(ID);
    }
}

class DiseaseType implements Serializable{// 病类
    /**
     *
     */
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
        this.subDiseaseTypes = null;
        this.patient = null;
    }

    void addsubdesease(DiseaseType... darr) {// 添加子病
        if (this.subDiseaseTypes == null)
            this.subDiseaseTypes = new ArrayList<DiseaseType>();
        for (DiseaseType di : darr) {
            subDiseaseTypes.add(di);
        }
        return;
    }

    void addpatient(String... strarr) {// 添加患病病人
        if (this.patient == null)
            this.patient = new ArrayList<String>();
        for (String temp : strarr) {
            this.patient.add(temp);
        }
        return;
    }

    public String toString() {
        return name;
    }
}

class Medicine implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8839467524596370199L;
    String ID;
    String name;
    int number;
    float price;// 价格
    String functions;// 功效

    public Medicine(String ID, String name, int number, float price, String functions) {
        this.ID = ID;
        this.name = name;
        this.number = number;
        this.price = price;
        this.functions = functions;
    }

    public String toString() {
        return ID + ":" + name + functions;
    }

    public int hashCode() {
        return Integer.valueOf(ID);
    }
}

class Records implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7404976754191175472L;
    private String patientID;// 患者ID
    private String time;// 患者就诊时间，确定一条就诊记录
    private String doctorID;// 医生ID
    private String medicines;// 所用药品

    public Records(String patientID, String time, String doctorID, String medicines) {
        this.patientID = patientID;
        this.time = time;
        this.doctorID = doctorID;
        this.medicines = medicines;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getMedicines() {
        return medicines;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getTime() {
        return time;
    }

    public String toString() {
        return patientID + ":" + time + medicines;
    }

    public int hashCode() {
        return Integer.valueOf(patientID);
    }
}
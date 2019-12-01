package model;

import java.io.Serializable;

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
        return patientID + ":" + time +":"+doctorID+":"+ medicines;
    }

    public int hashCode() {
        return Integer.valueOf(patientID);
    }
}
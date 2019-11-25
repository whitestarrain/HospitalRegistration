package model;

import java.io.Serializable;

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
package model;

import java.io.Serializable;

class Patient implements Serializable {// 病人
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
        return name;
    }

    public int hashCode() {
        return Integer.valueOf(ID);
    }
}
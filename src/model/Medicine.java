package model;

import java.io.Serializable;

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
package controller;

import model.Structure;
import registerview.Administrator;
import registerview.MainView;

public class Controller {
    private Structure structure;
    private MainView view;

    public Controller() {

    }

    public Controller(Structure structure, MainView view) {
        this.structure = structure;
        this.view = view;
        initData(structure, view);  
    }



    public void initData(Structure structure,MainView view){//将view中的数据初始化
        initAdministatorData(structure, view);
        initTriageData(structure, view);
        initTreatMentData(structure, view);
    }
    public void initAdministatorData(Structure structure,MainView view){
        view.getAdministrator().setJTree(structure.getJTree());//初始化jTree
        
    }
    public void initTriageData(Structure structure,MainView view){

    }
    public void initTreatMentData(Structure structure,MainView view){

    } 
}
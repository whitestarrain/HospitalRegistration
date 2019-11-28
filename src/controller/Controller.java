package controller;

import model.Structure;
import registerview.MainView;

public class Controller {
    private Structure structure;
    private MainView view;

    public Controller() {
        structure=new Structure();
        view=new MainView(this);
    }
    public boolean hasDiseaseTreeModify(){
        return structure.hasTreeMoidfy();
    }

    // public void initData(Structure structure,MainView view){//将view中的数据初始化
    //     initAdministatorData(structure, view);
    //     initTriageData(structure, view);
    //     initTreatMentData(structure, view);
    // }
    // public void initAdministatorData(Structure structure,MainView view){
       
    // }
    // public void initTriageData(Structure structure,MainView view){

    // }
    // public void initTreatMentData(Structure structure,MainView view){

    // }
    public Structure gStructure(){
        return structure;
    } 
    public MainView gMainView(){
        return view;
    }
}
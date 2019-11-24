package registerview;

import javax.swing.JFrame;

import controller.Controller;

public class MainView extends JFrame {
    private StartPanel startPanel;//面板加入不设置bounds的话会无法加载
    private Administrator administrator;
    private TriagePanel triageJPanel;
    private Treatment treatment;
    private MainView mainView = this;
    private Controller controller;

    public MainView() {
        init();
    }
    public MainView(Controller controller){
        this.controller=controller;
        init();
    }

    private void init() {
        startPanel = new StartPanel(mainView);
        administrator =new Administrator(mainView);
        triageJPanel = new TriagePanel(mainView);
        treatment =new Treatment(mainView);

        setBounds(500, 200, 600, 500);
        add(startPanel);
        add(administrator);
        add(triageJPanel);
        add(treatment);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // setLocationRelativeTo(null);
        // setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        startPanel.setVisible(true);
        startPanel.setEnabled(true);
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public TriagePanel getTriPanel() {
        return triageJPanel;
    }
    public Administrator getAdministrator(){
        return administrator;
    }
    public Treatment getTreatment(){
        return treatment;
    }
    public Controller gController(){
        return controller;
    }
    

    public static void main(String[] args) {
        new MainView();
    }
}
package registerview;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
    private static final long serialVersionUID = 5955317136594549001L;
    private MainView MainViewFrame;
    private JButton administrator;
    private JButton triage;
    private JButton treatMent;

    public StartPanel(MainView MainViewFrame) {
        this.MainViewFrame = MainViewFrame;
        init();
        Event();
    }

    public void init() {
        this.setSize(600, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
        administrator = new JButton("管理员");
        triage = new JButton("分诊");
        treatMent = new JButton("医生诊断");
        this.add(administrator);
        this.add(triage);
        this.add(treatMent);
        this.setEnabled(false);
        this.setVisible(false);
        MainViewFrame.repaint();
    }

    private void Event() {
        triage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainViewFrame.getStartPanel().setVisible(false);
                MainViewFrame.getStartPanel().setEnabled(false);
                MainViewFrame.getTriPanel().setVisible(true);
                MainViewFrame.getTriPanel().setEnabled(true);
            }
        });
        administrator.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainViewFrame.getStartPanel().setVisible(false);
                MainViewFrame.getStartPanel().setEnabled(false);
                MainViewFrame.getAdministrator().setVisible(true);
                MainViewFrame.getAdministrator().setEnabled(true);
            }
        });
        treatMent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainViewFrame.getTriPanel().hasReview == false) {
                    JOptionPane.showMessageDialog(MainViewFrame, "请进行分诊","",0);
                    return;
                }
                MainViewFrame.getStartPanel().setVisible(false);
                MainViewFrame.getStartPanel().setEnabled(false);
                MainViewFrame.getTreatment().setVisible(true);
                MainViewFrame.getTreatment().setEnabled(true);
                //TODO 诊断界面要显示优先权队列，这里删了那个函数，之后补上

                ArrayList<String> s=MainViewFrame.gController().getWaitPaitent_Priority();
                MainViewFrame.getTreatment().removeAllListItem();
                for(String str:s){
                    MainViewFrame.getTreatment().addQueueListItem(str);
                }
                MainViewFrame.getTreatment().setSelectedItem();
            }
        });
    }
}
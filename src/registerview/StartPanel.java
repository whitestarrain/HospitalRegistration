package registerview;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class StartPanel extends JPanel {
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
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 60));
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
                    JOptionPane.showMessageDialog(MainViewFrame, "请进行分诊", "", 0);
                    return;
                }

                ReloadListAndRecords();

                MainViewFrame.getStartPanel().setVisible(false);
                MainViewFrame.getStartPanel().setEnabled(false);
                MainViewFrame.getTreatment().setVisible(true);
                MainViewFrame.getTreatment().setEnabled(true);

            }
        });
    }

    void ReloadListAndRecords() {// 刷新TreatMentView
        ArrayList<Object> s = MainViewFrame.gController().getWaitPaitent_Priority();
        MainViewFrame.getTreatment().removeAllListItem();// 清空等待病人List
        MainViewFrame.getTreatment().recordsRemove();
        if (s.isEmpty())// 空的时候直接返回
            return;

        for (Object str : s) {// 添加等待病人list项
            MainViewFrame.getTreatment().addQueueListItem(str);
        }

        MainViewFrame.getTreatment().setSelectedItem();// 选择第一个病人并重置Records
    }
}
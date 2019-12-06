package registerview;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class TriagePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = -6713152977442850155L;
    private MainView mainView;
    private JButton begin;
    private JButton back;
    public boolean hasReview = false;
    TriagePanel triagePanel = this;

    public TriagePanel(MainView MainViewFrame) {
        this.mainView = MainViewFrame;
        init();
        setEvent();
    }

    private void init() {
        begin = new JButton("开始");
        back = new JButton("返回");
        this.add(begin);
        this.add(back);
        setSize(600, 500);
        this.setEnabled(false);
        this.setVisible(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 60));
    }

    private void setEvent() {
        begin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (hasReview == true) {
                    JOptionPane.showMessageDialog(mainView, "已设置完毕，请返回", "", 0);
                    return;
                }
                int i = 0;

                while (i < mainView.gController().getWaitPatientsLength()) {
                    int a = JOptionPane.showConfirmDialog(triagePanel,
                            "请问 " + mainView.gController().getTheIndexOfPatients(i) + " 是否为复诊", "请选择", 0);
                    int b = JOptionPane.showConfirmDialog(triagePanel,
                            "请问 " + mainView.gController().getTheIndexOfPatients(i) + " 是否为加急病人", "请选择", 0);

                    a = 1 - a;
                    b = 1 - b;
                    // 原来为是为0，否为1，转换为是为1，否为0
                    mainView.gController().addWaitPatientsToQueue(i, a, b);
                    ;// 将选择标识加入到数组中
                    i++;
                }
                hasReview = true;
            }
        });
        back.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.setEnabled(false);
        mainView.getStartPanel().setVisible(true);
        mainView.getStartPanel().setEnabled(true);

        // MainViewFrame.setContentPane(MainViewFrame.getStartPanel());
        // MainViewFrame.validate();//必须添加这句，重新部署组件
    }

}
package registerview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TriagePanel extends JPanel implements ActionListener{
    private MainView MainViewFrame;
    private JButton back;

    public TriagePanel(MainView MainViewFrame) {
        this.MainViewFrame = MainViewFrame;
        init();
        setEvent();
    }

    private void init() {
        back = new JButton("返回");
        this.add(back);
        setSize(600,500);
        this.setEnabled(false);
        this.setVisible(false);
    }

    private void setEvent() {
       back.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        this.setVisible(false);
        this.setEnabled(false);
        MainViewFrame.getStartPanel().setVisible(true);
        MainViewFrame.getStartPanel().setEnabled(true);

        // MainViewFrame.setContentPane(MainViewFrame.getStartPanel());
        // MainViewFrame.validate();//必须添加这句，重新部署组件
    }


    private class JCheckBoxAddListener extends JCheckBox {
        public JCheckBoxAddListener() {
            init();
        }

        public JCheckBoxAddListener(String s) {
            super(s);
            // this()只能放在首行，super也是，那么要同时调用两个的时候怎么办？
            init();
        }

        private void init() {
        }
    }
}
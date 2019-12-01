package registerview;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TriagePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = -6713152977442850155L;
    private MainView mainView;
    private JButton begin;
    private JButton back;
    public boolean hasReview=false;
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
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));
    }

    private void setEvent() {
        begin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(hasReview==true){
                    JOptionPane.showMessageDialog(mainView, "已设置完毕，请返回","",0);
                    return;
                }
                int i = 0;
                // System.out.println(mainView.gController().gStructure().getqueue());
                // FIXMENOTE ?成员对象的方法以及变量无法调用，在这里加.size错误,因为无法导入priority_queue包，该包不可见

                while (i < mainView.gController().gStructure().getqueuelength()) {
                    int a = JOptionPane.showConfirmDialog(triagePanel,
                            "请问 " + mainView.gController().gStructure().getNoProQueue().get(i) + " 是否为复诊", "请选择", 0);
                    mainView.gController().gStructure().getIsReview().add(a);// 将选择标识加入到数组中
                    i++;
                    //是为0，否为1
                }
                mainView.gController().gStructure().SetToPriorityQueue();
                hasReview=true;
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
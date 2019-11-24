package registerview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class Administrator extends JPanel {
	private MainView mainView;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JTree tree;
	private JButton button;

	public Administrator(){

	}
	public Administrator(MainView mainView) {
		this.mainView=mainView;
		init();
		Event();
	}

	private void init() {
		this.setSize(600, 500);

		scrollPane = new JScrollPane();

		textArea = new JTextArea();

		button_1 = new JButton("查询病人");

		button_2 = new JButton("查询病种");

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setColumns(10);

		label = new JLabel("\u53F3\u952E\u8FDB\u884C\u4FEE\u6539\u6216\u8005\u67E5\u8BE2");

		button = new JButton("\u8FD4\u56DE");//返回
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(label, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 238,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 247,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(47).addComponent(
														textArea, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
												.addGroup(groupLayout.createSequentialGroup().addGap(68)
														.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 97,
																GroupLayout.PREFERRED_SIZE)
														.addGap(35).addComponent(button_2, GroupLayout.PREFERRED_SIZE,
																97, GroupLayout.PREFERRED_SIZE))))
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()).addGroup(
								Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addContainerGap(378, Short.MAX_VALUE).addComponent(textField,
												GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
										.addGap(50)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addComponent(button)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(button_1)
						.addComponent(button_2).addComponent(label))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		tree=new JTree(mainView.gController().gStructure().getJTreeroot());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//设置仅单选
		scrollPane.setColumnHeaderView(tree);
		setLayout(groupLayout);

		this.setVisible(false);
		this.setEnabled(false);
	}
	private void Event(){
		button.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.getStartPanel().setVisible(true);
                mainView.getStartPanel().setEnabled(true);
                mainView.getAdministrator().setVisible(false);
                mainView.getAdministrator().setEnabled(false);
			}
		});
	}
	public JTree getJTree(){
		return tree;
	}
	
}

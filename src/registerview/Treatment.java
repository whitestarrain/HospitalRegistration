package registerview;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.TreeSelectionModel;

public class Treatment extends JPanel {
	private MainView mainView;
	private JTextArea textArea, textArea_1;
	private JLabel label, label_1, lblNewLabel;
	private JScrollPane scrollPane;
	private JTree tree;
	private JButton btnNewButton;
	private JButton button_1;
	private JTextField textField;
	private JButton button;
	private JComboBox<String> doctorBox;
	private JComboBox<String> medicineBox;
	private JTextField textField_1;
	private JLabel label_3;

	public Treatment() {

	}

	public Treatment(MainView mainView) {
		this.mainView = mainView;
		init();
		Event();

	}

	private void init() {
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);

		label = new JLabel("\u75C5\u5386");// 病历
		label.setForeground(Color.BLACK);
		label.setFont(new Font("宋体", Font.PLAIN, 17));

		JScrollPane scrollPane = new JScrollPane();

		label_1 = new JLabel("\u8BF7\u8BCA\u65AD\uFF1A");// 请诊断
		label_1.setBackground(Color.WHITE);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));

		textArea_1 = new JTextArea();
		textArea_1.setText("\u533B\u751F\u8BCA\u65AD");// 医生诊断
		textArea_1.setBackground(Color.WHITE);

		btnNewButton = new JButton("确认");

		button_1 = new JButton("\u8FD4\u56DE");// 返回
		button_1.setToolTipText("");

		textField = new JTextField();
		textField.setColumns(10);

		doctorBox = new JComboBox();
		medicineBox = new JComboBox();
		JLabel label_2 = new JLabel("\u00D7");

		label_3 = new JLabel("\u6570\u91CF");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel label_4 = new JLabel("\u533B\u751F");

		JLabel label_5 = new JLabel("\u836F\u54C1");

		button = new JButton("\u4E0B\u4E00\u4F4D");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addContainerGap()
												.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE))
										.addGroup(groupLayout
												.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup().addContainerGap()
																.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 84,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(textField, GroupLayout.PREFERRED_SIZE, 91,
																		GroupLayout.PREFERRED_SIZE))
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
																.createSequentialGroup().addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 345,
																		Short.MAX_VALUE))
														.addGroup(groupLayout
																.createSequentialGroup().addGap(112)
																.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE,
																		97, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup().addGap(31)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(label_4,
																				GroupLayout.PREFERRED_SIZE, 58,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(medicineBox,
																				GroupLayout.PREFERRED_SIZE, 72,
																				GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED, 47,
																		Short.MAX_VALUE)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(doctorBox,
																						GroupLayout.PREFERRED_SIZE, 69,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(label_2)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(textField_1,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																		.addComponent(label_5,
																				GroupLayout.PREFERRED_SIZE, 58,
																				GroupLayout.PREFERRED_SIZE))
																.addGap(46)))))
								.addContainerGap())
						.addGroup(
								groupLayout.createSequentialGroup().addContainerGap(493, Short.MAX_VALUE)
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 58,
												GroupLayout.PREFERRED_SIZE)
										.addGap(49))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addGap(142)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(18)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(
										label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_1).addComponent(button))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(medicineBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(doctorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2).addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(label_3).addComponent(label_4).addComponent(label_5))
										.addGap(50).addComponent(btnNewButton).addGap(59)
										.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))));

		tree = new JTree();
		tree.setBackground(Color.WHITE);
		tree.setFont(new Font("宋体", Font.PLAIN, 15));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);// 设置仅单选
		scrollPane.setViewportView(tree);
		setLayout(groupLayout);
		this.setSize(600, 500);
		this.setVisible(false);
		this.setEnabled(false);
	}

	private void Event() {
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.getStartPanel().setVisible(true);
				mainView.getStartPanel().setEnabled(true);
				mainView.getTreatment().setVisible(false);
				mainView.getTreatment().setEnabled(false);
			}
		});
	}
}

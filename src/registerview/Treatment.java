package registerview;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

class Treatment extends JPanel {
	private static final long serialVersionUID = 8864550146329822717L;
	private MainView mainView;
	private JTextArea textArea, textArea_1;
	private JLabel label, label_1;
	private JScrollPane scrollPane;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private JButton btnNewButton;
	private JButton button_1;
	private JTextField textField;
	private JLabel label_3;
	private JTextField textField_1;
	private JButton button;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JList<Object> list;
	private DefaultListModel<Object> lm;
	private JScrollPane scrollPane_2;
	private JLabel label_7;
	private JTextArea textArea_2;
	private JComboBox<Object> doctorBox;
	private JComboBox<Object> medicineBox;
	private String disease;
	private boolean textAreaHasSelected = false;
	private JPopupMenu jm = null;
	private JMenuItem idSort = null;
	private JMenuItem nameSort = null;
	private DefaultMutableTreeNode selectedNode = null;// 记录被选中的节点，默认为root

	public Treatment() {
		disease = null;
		init();
		Event();
	}

	public Treatment(MainView mainView) {
		this.mainView = mainView;
		init();
		Event();

	}

	private void init() {
		jm = new JPopupMenu();
		idSort = new JMenuItem("ID");
		nameSort = new JMenuItem("姓名");
		jm.add(idSort);
		jm.add(nameSort);

		selectedNode = mainView.gController().getJTreeRoot();
		JScrollPane scrollPane = new JScrollPane();

		label_1 = new JLabel("\u8BF7\u8BCA\u65AD\uFF1A");// 请诊断
		label_1.setBackground(Color.WHITE);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));

		textArea_1 = new JTextArea();
		textArea_1.setText("请医生输入详细诊断");// 医生诊断
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setLineWrap(true);

		btnNewButton = new JButton("确认");

		button_1 = new JButton("\u8FD4\u56DE");// 返回
		button_1.setToolTipText("");

		textField = new JTextField();
		textField.setColumns(10);
		textField.setEditable(false);

		doctorBox = mainView.gController().getDoctorComboBox();

		medicineBox = mainView.gController().getMedicineComboBox();

		JLabel label_2 = new JLabel("\u00D7");

		label_3 = new JLabel("\u6570\u91CF");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel label_4 = new JLabel("\u533B\u751F");

		JLabel label_5 = new JLabel("\u836F\u54C1");

		button = new JButton("\u4E0B\u4E00\u4F4D");
		button.setVisible(false);
		button.setEnabled(false);

		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_3 = new JScrollPane();

		scrollPane_4 = new JScrollPane();

		scrollPane_5 = new JScrollPane();

		scrollPane_2 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE).addGap(406)
						.addComponent(
								button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(12).addComponent(scrollPane_1,
								GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addGap(31)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 58,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(doctorBox, GroupLayout.PREFERRED_SIZE, 72,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(52)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 58,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(medicineBox, GroupLayout.PREFERRED_SIZE,
																		69, GroupLayout.PREFERRED_SIZE))
														.addGap(18).addComponent(label_2)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup().addGap(46)
																		.addComponent(
																				label_3, GroupLayout.PREFERRED_SIZE, 58,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup().addGap(18)
																		.addComponent(
																				textField_1, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))))
												.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE,
																		342, GroupLayout.PREFERRED_SIZE)
																.addGroup(
																		groupLayout.createSequentialGroup()
																				.addComponent(scrollPane_2,
																						GroupLayout.PREFERRED_SIZE, 147,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(36)
																				.addComponent(scrollPane_5,
																						GroupLayout.PREFERRED_SIZE, 154,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.RELATED)
																				.addComponent(scrollPane_3,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))))
												.addGroup(groupLayout.createSequentialGroup().addGap(152).addComponent(
														btnNewButton, GroupLayout.PREFERRED_SIZE, 97,
														GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(3, Short.MAX_VALUE))
								.addGroup(
										groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(206)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(
						groupLayout.createParallelGroup(Alignment.LEADING).addComponent(button_1).addComponent(button))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(2)
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane_3,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(medicineBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(doctorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_3)
										.addComponent(label_4).addComponent(label_5, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton)
								.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE).addComponent(
										textArea_1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))));

		label_7 = new JLabel("\u75C5\u79CD\u5BF9\u5E94\u75C5\u4EBA(\u53F3\u952E\u6392\u5E8F)");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(label_7);

		textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);

		lm = new DefaultListModel<Object>();
		list = new JList<Object>(lm);
		list.setEnabled(false);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.enableInputMethods(false);
		list.setFont(new Font("宋体", Font.PLAIN, 13));
		scrollPane_5.setViewportView(list);

		JLabel label_6 = new JLabel("\u75C5\u4EBA\u961F\u5217");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_5.setColumnHeaderView(label_6);

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);

		label = new JLabel("\u75C5\u5386");// 病历
		label.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("宋体", Font.PLAIN, 17));

		treeModel = new DefaultTreeModel(mainView.gController().getJTreeRoot());
		tree = new JTree(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);// 设置仅单选
		tree.setSelectionInterval(0, 0);
		scrollPane.setViewportView(tree);
		setLayout(groupLayout);
		this.setSize(600, 500);
		this.setVisible(false);
		this.setEnabled(false);
	}

	public void treeReload() {
		treeModel.reload();
	}

	public void removeAllListItem() {
		lm.removeAllElements();
	}

	public void recordsRemove() {
		textArea.setText("");
	}

	public void addQueueListItem(Object s) {
		lm.addElement(s);
	}

	public void setSelectedItem() {
		list.setSelectedIndex(0);
		textArea.setText(mainView.gController().getRecordsToString(list.getSelectedValue()));
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
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode temp = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				selectedNode = temp;// 记录被选中的节点
				textField.setText((disease = temp.toString()));
				textArea_2.setText(mainView.gController().patientToString(temp, ""));// 默认无序
			}
		});
		textArea_1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// 无动作
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (textAreaHasSelected == false) {
					textArea_1.setText("");
					textArea_1.revalidate();
					textAreaHasSelected = true;
				}
			}
		});
		textArea_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					jm.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		idSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea_2.setText(mainView.gController().patientToString(selectedNode, "Id"));
			}
		});
		nameSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea_2.setText(mainView.gController().patientToString(selectedNode, "Name"));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {// 确认按钮

			@Override
			public void actionPerformed(ActionEvent e) {

				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(mainView, "病人已经诊断完成，请退出", "", 0);
					return;
				}

				if (!((DefaultMutableTreeNode) (tree.getSelectionPath().getLastPathComponent())).isLeaf()) {
					JOptionPane.showMessageDialog(mainView, "请选择具体病种", "", 0);
					return;
				}

				if (!textAreaHasSelected || medicineBox.getSelectedIndex() == 0 || doctorBox.getSelectedIndex() == 0
						|| textField.getText().equals("")) {
					JOptionPane.showMessageDialog(mainView, "请把选项及内容补充完整", "", 0);
					return;
				}

				String memo = textArea_1.getText();// 记录医生诊断
				Object medicine = medicineBox.getSelectedItem();// 记录所选药品
				Object doctor = doctorBox.getSelectedItem();// 记录就诊医生
				Object patient = list.getSelectedValue();
				DefaultMutableTreeNode dis = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();// 记录所选病种
				int number = Integer.parseInt(textField_1.getText());
				if (memo.equals("") || memo == null)
					memo = "无";
				if (number > mainView.gController().getMedicineNumber(medicine)) {
					JOptionPane.showMessageDialog(mainView,
							"药品数量不足，仅剩" + mainView.gController().getMedicineNumber(medicine) + ",仅给剩余数量的药品", "", 0);

					number = mainView.gController().getMedicineNumber(medicine);
				}

				mainView.gController().queue_peekmin();// 弹出最小的
				mainView.getStartPanel().ReloadListAndRecords();// 重新加载list和records

				mainView.gController().flushFile(patient, medicine, doctor, dis, memo, number);// 文件刷新

				textArea_2.setText(mainView.gController().patientToString(dis, ""));// 更新病人列表
				textArea_1.setText("请输入医生诊断");
				textAreaHasSelected = false;
			}
		});
	}
}

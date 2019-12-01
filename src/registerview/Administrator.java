package registerview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class Administrator extends JPanel {

	private static final long serialVersionUID = 7029198858336373726L;

	private MainView mainView;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JButton button;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private boolean jTextField_has_clicked = false;// 记录是否为第一次点击，是的话清空内容
	TreePath nowPath = null;// 指向路径在右键点击时初始化

	// 右键菜单
	private JPopupMenu jp = new JPopupMenu();
	private JMenuItem inquire = new JMenuItem("查询");
	private JMenuItem delete = new JMenuItem("删除");
	private JMenuItem additem = new JMenuItem("添加");

	public Administrator() {

	}

	public Administrator(MainView mainView) {
		this.mainView = mainView;
		init();
		Event();
	}

	private void init() {
		this.setSize(600, 500);

		scrollPane = new JScrollPane();
		textArea = new JTextArea();
		scrollPane.setBorder(new LineBorder(Color.gray, 2, true));
		button_1 = new JButton("查询病人");

		button_2 = new JButton("查询病种");

		textField = new JTextField("请输入病类");

		label = new JLabel("\u53F3\u952E\u8FDB\u884C\u4FEE\u6539\u6216\u8005\u67E5\u8BE2");

		button = new JButton("\u8FD4\u56DE");// 返回
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(Color.gray, 1, true));

		treeModel = new DefaultTreeModel(mainView.gController().gStructure().getJTreeroot());
		tree = new JTree(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);// 设置仅单选

		// 初始化右键菜单
		jp.add(inquire);
		jp.add(delete);
		jp.add(additem);

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
														scrollPane_1, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
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
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));

		scrollPane.setColumnHeaderView(tree);
		scrollPane_1.setViewportView(textArea);
		setLayout(groupLayout);

		this.setVisible(false);
		this.setEnabled(false);
	}

	private void Event() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainView.gController().hasDiseaseTreeModify()) {
					int i = JOptionPane.showConfirmDialog(mainView, "一旦离开修改将记录？请确定", "warning", 0);
					if (i == 0) {
						try {
							mainView.gController().gStructure().writeToFile_Tree();
						} catch (Exception a) {
							a.printStackTrace();
							throw new RuntimeException("文件重写失败");
						}
					} else {
						return;
					}
				}

				mainView.getStartPanel().setVisible(true);
				mainView.getStartPanel().setEnabled(true);
				mainView.getAdministrator().setVisible(false);
				mainView.getAdministrator().setEnabled(false);
			}
		});
		// TODO 右键小菜单进行查询和修改
		// TODO*2文本框中输入信息进行查询

		inquire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				DefaultMutableTreeNode temp = (DefaultMutableTreeNode) nowPath.getLastPathComponent();
				mainView.gController().gStructure().treeTrverse(temp, textArea);
			}
		});
		delete.addActionListener(new ActionListener() {// 删除选项

			@Override
			public void actionPerformed(ActionEvent e) {
				// 未修改文件
				DefaultMutableTreeNode temp = (DefaultMutableTreeNode) nowPath.getLastPathComponent();
				treeModel.removeNodeFromParent(temp);// 从树中删除
				mainView.gController().deleNode(temp);// 从文件中删除
			}
		});
		additem.addActionListener(new ActionListener() {// 添加选项
			@Override
			public void actionPerformed(ActionEvent e) {
				String tempdis = JOptionPane.showInputDialog(mainView, "请输入病种名称");
				DefaultMutableTreeNode tempnode = (DefaultMutableTreeNode) nowPath.getLastPathComponent();
				tempnode.add(mainView.gController().addSubDis(tempnode, tempdis));// 修改对应HashMap，并返回子病节点引用包装后的DefaultMutableTreeNode,然后添加到当前选中的那个节点中
				treeModel.reload();
				// TODO刷新文件

			}
		});
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				textArea.setText("");
				DefaultMutableTreeNode temp = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				mainView.gController().gStructure().treeTrverse(temp, textArea);
			}
		});

		tree.addMouseListener(new MouseAdapter() {// 右键弹出菜单
			public void mouseClicked(MouseEvent e) {
				TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				if (e.getButton() == MouseEvent.BUTTON3 && path != null) {
					jp.show(e.getComponent(), e.getX(), e.getY());
					nowPath = path;// 更新指向路径
				}
			}
		});

		textField.addMouseListener(new MouseAdapter() {// 实现第一次点击后清空文本
			public void mouseReleased(MouseEvent e) {
				if (jTextField_has_clicked == false) {
					textField.setText("");
					textField.revalidate();
					jTextField_has_clicked = true;
				}
			}
		});

		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = textField.getText();
				if (s == null || s.matches("[ ]*")) {
					JOptionPane.showMessageDialog(mainView, "请输入文本", "", 0);
					return;
				}
				textArea.setText("");
				mainView.gController().gStructure()
						.treeTrverse(mainView.gController().gStructure().searchDiseasesType(s), textArea);
			}

		});
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = textField.getText();
				if (s == null || s.matches("[ ]*")) {
					JOptionPane.showMessageDialog(mainView, "请输入文本", "", 0);
					return;
				}
				textArea.setText("");
				mainView.gController().gStructure()
						.treeTrverse_noPatient(mainView.gController().gStructure().searchDiseasesType(s), textArea);
			}
		});
	}

	public JTree getJTree() {
		return tree;
	}

}

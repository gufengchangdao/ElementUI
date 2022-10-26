package com.component.data.tree.checkboxtree.examples;

import com.component.data.tree.checkboxtree.CheckboxTree;
import com.component.data.tree.checkboxtree.CheckboxTreeCellRenderer;
import com.component.data.tree.checkboxtree.TreeCheckingModel;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 文件选择数的函数使用
 * <p>
 * 增删改查、自定义节点外观
 */
public class CheckboxTreeDemo extends JPanel implements KeyListener, CheckboxTreeCellRenderer {
	private static CheckboxTree checkboxTree;
	protected JRadioButton button = new JRadioButton();
	protected JLabel label = new JLabel();

	public CheckboxTreeDemo() {
		// 实现CheckboxTreeCellRenderer接口，每一个main对象表示一个节点
		label.setFocusable(true);
		label.setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(button);
		add(label);
		button.setBackground(UIManager.getColor("Tree.textBackground"));
		setBackground(UIManager.getColor("Tree.textBackground"));
	}

	public static void main(String[] args) {
		SwingTestUtil.loadSkin();
		checkboxTree = new CheckboxTree();
		// 设置节点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode aChild = new DefaultMutableTreeNode("child A");
		DefaultMutableTreeNode bChild = new DefaultMutableTreeNode("child B");
		DefaultMutableTreeNode cChild = new DefaultMutableTreeNode("child C");
		root.add(aChild);
		root.add(bChild);
		bChild.add(cChild);
		MyDefaultTreeModel model = new MyDefaultTreeModel(root);
		checkboxTree.setModel(model);


		// 负责监听器和节点外观
		CheckboxTreeDemo main = new CheckboxTreeDemo();
		checkboxTree.addKeyListener(main);
		// 设置节点的渲染样式，这里是单选按钮的样式
		// checkboxTree.setCellRenderer(main);

		// 设置关联选择
		checkboxTree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE);

		checkboxTree.setRootVisible(true);
		// 初始展开所有目录
		checkboxTree.expandAll();

		// 获取到根节点
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) checkboxTree.getModel().getRoot();
		// 当前节点的第三个孩子，getChildAt可以链式调用
		rootNode = (DefaultMutableTreeNode) rootNode.getChildAt(0);

		System.out.println("row number: " + checkboxTree.getRowForPath(new TreePath(rootNode.getPath())));
		// 设置已选中节点
		checkboxTree.addCheckingPath(new TreePath(rootNode.getPath()));

		checkboxTree.addTreeCheckingListener(e -> {
			System.out.println("点击的节点名: " + e.getPath().getLastPathComponent());
			System.out.println("已选节点: ");
			// 获取已选节点，如果是目录则表示该目录下所有文件已选，否则就是文件
			TreePath[] cr = checkboxTree.getCheckingRoots();
			for (TreePath path : cr) {
				System.out.println(path.getLastPathComponent());
			}
		});

		JScrollPane scrollPane = new JScrollPane(checkboxTree);

		SwingTestUtil.test(scrollPane);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 获取选中节点的路径，该节点不一定是勾选的
		TreePath p = checkboxTree.getSelectionPath();
		if (p == null) return;
		System.out.println("选择的路径: " + p);

		// 选中的节点
		DefaultMutableTreeNode resource = (DefaultMutableTreeNode) p.getLastPathComponent();

		if (e.getKeyChar() == 'r') {
			TreeNode parent = resource.getParent();
			int index = parent.getIndex(resource);
			System.out.println("移除 " + resource);
			// 从父节点移除该节点
			resource.removeFromParent();
			DefaultTreeModel dtm = (DefaultTreeModel) checkboxTree.getModel();
			// 移除节点后需要调用这个方法
			// dtm.nodeStructureChanged(parent);
			dtm.nodesWereRemoved(parent, new int[]{index}, new TreeNode[]{resource});
		}

		if (e.getKeyChar() == 'a') {
			System.out.println("添加到 " + resource);
			DefaultTreeModel dtm = (DefaultTreeModel) checkboxTree.getModel();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("test");
			resource.add(node);
			// 插入节点后调用
			dtm.nodesWereInserted(resource, new int[]{resource.getIndex(node)});
			// 如果修改了子节点，也修改了子节点的子节点，可以使用这个方法，他可以递归地进行处理
			// ((DefaultTreeModel) checkboxTree.getModel()).nodeStructureChanged(resource);
			System.out.println("字节点索引: " + resource.getIndex(node));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
	                                              boolean leaf, int row, boolean hasFocus) {
		label.setText(value.toString());
		if (selected)
			label.setBackground(UIManager.getColor("Tree.selectionBackground"));
		else
			label.setBackground(UIManager.getColor("Tree.textBackground"));
		TreeCheckingModel checkingModel = ((CheckboxTree) tree).getCheckingModel();
		TreePath path = tree.getPathForRow(row);
		boolean enabled = checkingModel.isPathEnabled(path);
		boolean checked = checkingModel.isPathChecked(path);
		button.setEnabled(enabled);
		label.setForeground(Color.black);
		button.setSelected(checked);
		return this;
	}

	@Override
	public boolean isOnHotspot(int x, int y) {
		return button.getBounds().contains(x, y);
	}

	public static class MyDefaultTreeModel extends DefaultTreeModel {
		public MyDefaultTreeModel(TreeNode root) {
			super(root);
		}

	}
}

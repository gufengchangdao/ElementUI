package lab.component.tree;// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Optional;

/**
 * 对JTree的节点增删改查
 */
public final class AddNode extends JPanel {
	private AddNode() {
		super(new BorderLayout());
		JTree tree = new JTree();
		tree.setComponentPopupMenu(new TreePopupMenu());
		add(new JScrollPane(tree));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new FlowLayout());
			p.add(new AddNode());
			SwingTestUtil.test();
		});
	}
}

class TreePopupMenu extends JPopupMenu {
	/** 操作的节点 */
	private TreePath path;

	protected TreePopupMenu() {
		super();
		JTextField textField = new JTextField(24) {
			private transient AncestorListener listener;

			// 重写 updateUI，使得输入框更新时获取焦点
			@Override
			public void updateUI() {
				// 每次调用父类方法后重新添加
				removeAncestorListener(listener);
				super.updateUI();
				listener = new FocusAncestorListener();
				addAncestorListener(listener);
			}
		};

		add("add").addActionListener(e -> {
			JTree tree = (JTree) getInvoker();
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();
			DefaultMutableTreeNode child = new DefaultMutableTreeNode("New node");
			model.insertNodeInto(child, parent, parent.getChildCount());
			// 展开新添加节点所在父节点
			tree.scrollPathToVisible(new TreePath(child.getPath()));
		});
		add("add & reload").addActionListener(e -> {
			JTree tree = (JTree) getInvoker();
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path.getLastPathComponent();
			DefaultMutableTreeNode child = new DefaultMutableTreeNode("New node");
			parent.add(child);
			model.reload(parent); // = model.nodeStructureChanged(parent);
			tree.scrollPathToVisible(new TreePath(child.getPath()));
		});
		add("edit").addActionListener(e -> {
			Object node = path.getLastPathComponent();
			if (!(node instanceof DefaultMutableTreeNode)) {
				return;
			}
			DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) node;
			textField.setText(leaf.getUserObject().toString()); //获取到节点的值设置给输入框
			JTree tree = (JTree) getInvoker();
			// 弹窗编辑的对话框
			int ret = JOptionPane.showConfirmDialog(
					tree, textField, "edit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (ret == JOptionPane.OK_OPTION) {
				tree.getModel().valueForPathChanged(path, textField.getText());
				// leaf.setUserObject(textField.getText());
			}
		});
		addSeparator();
		add("remove").addActionListener(e -> {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (!node.isRoot()) {
				JTree tree = (JTree) getInvoker();
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.removeNodeFromParent(node);
			}
		});
	}

	@Override
	public void show(Component c, int x, int y) {
		if (c instanceof JTree) {
			JTree tree = (JTree) c;
			path = tree.getPathForLocation(x, y);
			// if (Objects.nonNull(path) && Arrays.asList(tsp).contains(path)) {
			Optional.ofNullable(path).ifPresent(treePath -> {
				tree.setSelectionPath(treePath); //设置选中该节点
				super.show(c, x, y);
			});
		}
	}
}

class FocusAncestorListener implements AncestorListener {
	@Override
	public void ancestorAdded(AncestorEvent e) {
		e.getComponent().requestFocusInWindow();
	}

	@Override
	public void ancestorMoved(AncestorEvent e) {
	}

	@Override
	public void ancestorRemoved(AncestorEvent e) {
	}
}

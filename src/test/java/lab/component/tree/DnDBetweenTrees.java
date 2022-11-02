package lab.component.tree;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 文件树之间的穿梭框
 */
public class DnDBetweenTrees extends JPanel {
	private DnDBetweenTrees() {
		super(new GridLayout(1, 2));
		TreeTransferHandler handler = new TreeTransferHandler();
		add(new JScrollPane(makeTree(handler)));
		add(new JScrollPane(makeTree(handler)));
		setPreferredSize(new Dimension(320, 240));
	}

	private static JTree makeTree(TransferHandler handler) {
		JTree tree = new JTree();
		tree.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tree.setRootVisible(false);
		tree.setDragEnabled(true);
		tree.setTransferHandler(handler);
		tree.setDropMode(DropMode.INSERT);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Disable node Cut action
		Action dummy = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Dummy action */
			}
		};
		tree.getActionMap().put(TransferHandler.getCutAction().getValue(Action.NAME), dummy);

		expandTree(tree);
		return tree;
	}

	private static void expandTree(JTree tree) {
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new FlowLayout());
			p.add(new DnDBetweenTrees());
			SwingTestUtil.test();
		});
	}
}

class TreeTransferHandler extends TransferHandler {
	// protected static final DataFlavor FLAVOR = new ActivationDataFlavor(
	//   DefaultMutableTreeNode[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of TreeNode");
	private static final String NAME = "Array of DefaultMutableTreeNode";
	protected static final DataFlavor FLAVOR = new DataFlavor(DefaultMutableTreeNode[].class, NAME);
	private JTree source;

	@Override
	protected Transferable createTransferable(JComponent c) {
		source = (JTree) c;
		TreePath[] paths = Objects.requireNonNull(source.getSelectionPaths(), "SelectionPaths is null");
		DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[paths.length];
		for (int i = 0; i < paths.length; i++) {
			nodes[i] = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
		}
		// return new DataHandler(nodes, FLAVOR.getMimeType());
		return new Transferable() {
			@Override
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[]{FLAVOR};
			}

			@Override
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return Objects.equals(FLAVOR, flavor);
			}

			@Override
			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
				if (isDataFlavorSupported(flavor)) {
					return nodes;
				} else {
					throw new UnsupportedFlavorException(flavor);
				}
			}
		};
	}

	@Override
	public int getSourceActions(JComponent c) {
		return TransferHandler.MOVE;
	}

	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {
		Component c = support.getComponent();
		return support.isDrop() && support.isDataFlavorSupported(FLAVOR) && !Objects.equals(source, c);
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {
		DefaultMutableTreeNode[] nodes;
		try {
			Transferable t = support.getTransferable();
			nodes = (DefaultMutableTreeNode[]) t.getTransferData(FLAVOR);
		} catch (UnsupportedFlavorException | IOException ex) {
			return false;
		}
		TransferHandler.DropLocation tdl = support.getDropLocation();
		if (tdl instanceof JTree.DropLocation) {
			JTree.DropLocation dl = (JTree.DropLocation) tdl;
			int childIndex = dl.getChildIndex();
			TreePath dest = dl.getPath();
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) dest.getLastPathComponent();
			JTree tree = (JTree) support.getComponent();
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			// int idx = childIndex < 0 ? parent.getChildCount() : childIndex;
			// // DefaultTreeModel sm = (DefaultTreeModel) source.getModel();
			// for (DefaultMutableTreeNode node : nodes) {
			//   // sm.removeNodeFromParent(node);
			//   // model.insertNodeInto(node, parent, idx++);
			//   DefaultMutableTreeNode clone = new DefaultMutableTreeNode(node.getUserObject());
			//   model.insertNodeInto(deepCopy(node, clone), parent, idx++);
			// }
			AtomicInteger idx = new AtomicInteger(childIndex < 0 ? parent.getChildCount() : childIndex);
			Stream.of(nodes).forEach(node -> {
				DefaultMutableTreeNode clone = new DefaultMutableTreeNode(node.getUserObject());
				model.insertNodeInto(deepCopy(node, clone), parent, idx.incrementAndGet());
			});
			return true;
		}
		return false;
	}

	private static DefaultMutableTreeNode deepCopy(MutableTreeNode src, DefaultMutableTreeNode tgt) {
		// Java 9: Collections.list(src.children()).stream()
		Collections.list((Enumeration<?>) src.children()).stream()
				.filter(DefaultMutableTreeNode.class::isInstance)
				.map(DefaultMutableTreeNode.class::cast)
				.forEach(node -> {
					DefaultMutableTreeNode clone = new DefaultMutableTreeNode(node.getUserObject());
					tgt.add(clone);
					if (!node.isLeaf()) {
						deepCopy(node, clone);
					}
				});
		// for (int i = 0; i < src.getChildCount(); i++) {
		//   DefaultMutableTreeNode node = (DefaultMutableTreeNode) src.getChildAt(i);
		//   DefaultMutableTreeNode clone = new DefaultMutableTreeNode(node.getUserObject());
		//   // DefaultMutableTreeNode clone = (DefaultMutableTreeNode) node.clone();
		//   tgt.add(clone);
		//   if (!node.isLeaf()) {
		//     deepCopyTree(node, clone);
		//   }
		// }
		return tgt;
	}

	@Override
	protected void exportDone(JComponent src, Transferable data, int action) {
		if (action == TransferHandler.MOVE && src instanceof JTree) {
			JTree tree = (JTree) src;
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			TreePath[] selectionPaths = tree.getSelectionPaths();
			if (selectionPaths != null) {
				for (TreePath path : selectionPaths) {
					model.removeNodeFromParent((MutableTreeNode) path.getLastPathComponent());
				}
			}
		}
	}
}


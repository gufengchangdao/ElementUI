/*
 * Copyright 2007-2022 Enrico Boldrini, Lorenzo Bigagli This file is part of
 * CheckboxTree. CheckboxTree is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version. CheckboxTree is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU
 * General Public License along with CheckboxTree; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA
 */
package com.component.data.tree.checkboxtree.examples;


import com.component.data.tree.checkboxtree.CheckboxTree;
import com.component.data.tree.checkboxtree.CheckboxTreeCellRenderer;
import com.component.data.tree.checkboxtree.TreeCheckingModel;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * Example showing the implementation of a CheckboxTree with RadioButton-style
 * checkboxes.
 * <p>
 * 使用自定义组件绘制文件选择数的单元格
 *
 * @author bigagli
 */
public class RadioButtonTree extends JPanel implements CheckboxTreeCellRenderer {

	public static void main(String[] args) {
		CheckboxTree tree = new CheckboxTree();
		// 单选模式
		tree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.SINGLE);
		tree.setCellRenderer(new RadioButtonTree());
		JFrame frame = new JFrame("RadioButton tree");
		frame.add(tree);
		tree.expandAll();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	protected JRadioButton button = new JRadioButton();

	protected JLabel label = new JLabel();

	public RadioButtonTree() {
		super();
		label.setFocusable(true);
		label.setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(button);
		add(label);
		button.setBackground(UIManager.getColor("Tree.textBackground"));
		setBackground(UIManager.getColor("Tree.textBackground"));
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row,
	                                              boolean hasFocus) {
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

	public boolean isOnHotspot(int x, int y) {
		return (button.getBounds().contains(x, y));
	}
}
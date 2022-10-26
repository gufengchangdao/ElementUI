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
package com.component.data.tree.checkboxtree;


import com.component.data.tree.checkboxtree.QuadristateButtonModel.State;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * A renderer for the CheckboxTree. This implementation decorates a
 * DefaultTreeCellRenderer (i.e. a JLabel) with a checkbox, by adding a
 * QuadristateCheckbox to the former onto a JPanel. Both can be overridden by
 * subclasses. Note that double-clicking the label/icon of this renderer does
 * not toggle the checkbox.
 *
 * @author boldrini
 * @author bigagli
 */
public class DefaultCheckboxTreeCellRenderer extends JPanel implements CheckboxTreeCellRenderer {

	/**
	 * Loads an ImageIcon from the file iconFile, searching it in the classpath.
	 */
	protected static ImageIcon loadIcon(String iconFile) {
		try {
			return new ImageIcon(DefaultCheckboxTreeCellRenderer.class.getClassLoader().getResource(iconFile));
		} catch (NullPointerException npe) {// did not find the resource
			return null;
		}
	}

	protected QuadristateCheckbox checkBox = new QuadristateCheckbox();

	protected DefaultTreeCellRenderer label = new DefaultTreeCellRenderer();

	// @Override
	// public void doLayout() {
	// Dimension d_check = this.checkBox.getPreferredSize();
	// Dimension d_label = this.label.getPreferredSize();
	// int y_check = 0;
	// int y_label = 0;
	// if (d_check.height < d_label.height) {
	// y_check = (d_label.height - d_check.height) / 2;
	// } else {
	// y_label = (d_check.height - d_label.height) / 2;
	// }
	// this.checkBox.setLocation(0, y_check);
	// this.checkBox.setBounds(0, y_check, d_check.width, d_check.height);
	// this.label.setLocation(d_check.width, y_label);
	// this.label.setBounds(d_check.width, y_label, d_label.width,
	// d_label.height);
	// }

	public DefaultCheckboxTreeCellRenderer() {
		/* this method was as follows (see ticket #6):
		 *
		 * this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		 * add(this.checkBox);
		 * add(this.label);
		 * this.checkBox.setBackground(UIManager.getColor("Tree.textBackground"));
		 * this.setBackground(UIManager.getColor("Tree.textBackground"));
		 */

		// CHECK: a user suggested BorderLayout appears to work better than FlowLayout with most L&Fs
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setOpaque(false);
		add(this.checkBox);
		add(this.label);

		/*
		 * label.setOpaque(false); seems not work...
		 */
		label.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d_check = this.checkBox.getPreferredSize();
		Dimension d_label = this.label.getPreferredSize();
		return new Dimension(d_check.width + d_label.width, (d_check.height < d_label.height ? d_label.height : d_check.height));
	}

	/**
	 * Decorates this renderer based on the passed in components.
	 */
	public Component getTreeCellRendererComponent(JTree tree, Object object, boolean selected, boolean expanded, boolean leaf, int row,
	                                              boolean hasFocus) {
		/*
		 * most of the rendering is delegated to the wrapped
		 * DefaultTreeCellRenderer, the rest depends on the TreeCheckingModel
		 */
		this.label.getTreeCellRendererComponent(tree, object, selected, expanded, leaf, row, hasFocus);
		if (tree instanceof CheckboxTree) {
			TreePath path = tree.getPathForRow(row);
			TreeCheckingModel checkingModel = ((CheckboxTree) tree).getCheckingModel();
			this.checkBox.setEnabled(checkingModel.isPathEnabled(path) && tree.isEnabled());
			boolean checked = checkingModel.isPathChecked(path);
			boolean greyed = checkingModel.isPathGreyed(path);
			if (checked && !greyed) {
				this.checkBox.setState(State.CHECKED);
			}
			if (!checked && greyed) {
				this.checkBox.setState(State.GREY_UNCHECKED);
			}
			if (checked && greyed) {
				this.checkBox.setState(State.GREY_CHECKED);
			}
			if (!checked && !greyed) {
				this.checkBox.setState(State.UNCHECKED);
			}
		}
		return this;
	}

	/**
	 * Checks if the (x,y) coordinates are on the Checkbox.
	 *
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isOnHotspot(int x, int y) {
		return this.checkBox.contains(x, y);
	}

	@Override
	public void setBackground(Color color) {
		if (color instanceof ColorUIResource) {
			color = null;
		}
		super.setBackground(color);
	}

	/**
	 * Sets the icon used to represent non-leaf nodes that are not expanded.
	 */
	public void setClosedIcon(Icon newIcon) {
		this.label.setClosedIcon(newIcon);
	}

	/**
	 * Sets the icon used to represent leaf nodes.
	 */
	public void setLeafIcon(Icon newIcon) {
		this.label.setLeafIcon(newIcon);
	}

	/**
	 * Sets the icon used to represent non-leaf nodes that are expanded.
	 */
	public void setOpenIcon(Icon newIcon) {
		this.label.setOpenIcon(newIcon);
	}

}
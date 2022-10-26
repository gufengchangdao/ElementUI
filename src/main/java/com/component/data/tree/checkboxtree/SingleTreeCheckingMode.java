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

import javax.swing.tree.TreePath;

/**
 * SingleTreeCheckingMode defines a TreeCheckingMode without recursion. In this
 * simple mode the check state always changes only the current node: no
 * recursion. Also, only a single node of the tree is allowed to have a check at
 * a given time.
 *
 * @author Boldrini
 */
public class SingleTreeCheckingMode extends TreeCheckingMode {
	SingleTreeCheckingMode(DefaultTreeCheckingModel model) {
		super(model);
	}

	@Override
	public void checkPath(TreePath path) {
		this.model.clearChecking();
		this.model.addToCheckedPathsSet(path);
		this.model.updatePathGreyness(path);
		this.model.updateAncestorsGreyness(path);
	}

	@Override
	public void uncheckPath(TreePath path) {
		this.model.removeFromCheckedPathsSet(path);
		this.model.updatePathGreyness(path);
		this.model.updateAncestorsGreyness(path);
	}

	/**
	 * @see TreeCheckingMode#updateCheckAfterChildrenInserted(TreePath)
	 */
	@Override
	public void updateCheckAfterChildrenInserted(TreePath parent) {
		this.model.updatePathGreyness(parent);
		this.model.updateAncestorsGreyness(parent);
	}

	/**
	 * @see TreeCheckingMode#updateCheckAfterChildrenRemoved(TreePath)
	 */
	@Override
	public void updateCheckAfterChildrenRemoved(TreePath parent) {
		this.model.updatePathGreyness(parent);
		this.model.updateAncestorsGreyness(parent);
	}

	/**
	 * @see TreeCheckingMode#updateCheckAfterStructureChanged(TreePath)
	 */
	@Override
	public void updateCheckAfterStructureChanged(TreePath parent) {
		this.model.updatePathGreyness(parent);
		this.model.updateAncestorsGreyness(parent);
	}

}
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
 * PropagateTreeCheckingMode define a TreeCheckingMode with down recursion of
 * the check when nodes are clicked. It toggles the just-clicked checkbox and
 * propagates the change down. In other words, if the clicked checkbox is
 * checked all the descendants will be checked; otherwise all the descendants
 * will be unchecked.
 *
 * @author Boldrini
 */
public class PropagateTreeCheckingMode extends TreeCheckingMode {

	PropagateTreeCheckingMode(DefaultTreeCheckingModel model) {
		super(model);
	}

	@Override
	public void checkPath(TreePath path) {
		this.model.checkSubTree(path);
		this.model.updatePathGreyness(path);
		this.model.updateAncestorsGreyness(path);
	}

	@Override
	public void uncheckPath(TreePath path) {
		this.model.uncheckSubTree(path);
		this.model.updatePathGreyness(path);
		this.model.updateAncestorsGreyness(path);
	}

	@Override
	public void updateCheckAfterChildrenInserted(TreePath parent) {
		if (this.model.isPathChecked(parent)) {
			this.model.checkSubTree(parent);
		} else {
			this.model.uncheckSubTree(parent);
		}
	}

	@Override
	public void updateCheckAfterChildrenRemoved(TreePath parent) {
		this.model.updatePathGreyness(parent);
		this.model.updateAncestorsGreyness(parent);
	}

	@Override
	public void updateCheckAfterStructureChanged(TreePath parent) {
		if (this.model.isPathChecked(parent)) {
			this.model.checkSubTree(parent);
		} else {
			this.model.uncheckSubTree(parent);
		}
	}

}
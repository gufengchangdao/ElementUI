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
 * Convenience class representing an empty tree checking model (cf. the Null
 * Object pattern), whose paths are always enabled, unchecked and ungreyed. This
 * class is a singleton.
 *
 * @author Lorenzo Bigagli
 */
public class NullTreeCheckingModel implements TreeCheckingModel {

	private final static NullTreeCheckingModel singleton;

	static {
		singleton = new NullTreeCheckingModel();
	}

	private NullTreeCheckingModel() {
	}

	public static NullTreeCheckingModel getInstance() {
		return singleton;
	}

	public void addCheckingPath(TreePath path) {
	}

	public void addCheckingPaths(TreePath[] paths) {
	}

	public void addTreeCheckingListener(TreeCheckingListener tcl) {
	}

	public void clearChecking() {
	}

	public CheckingMode getCheckingMode() {
		return null;
	}

	public TreePath[] getCheckingPaths() {
		return null;
	}

	public TreePath[] getCheckingRoots() {
		return null;
	}

	public TreePath[] getGreyingPaths() {
		return null;
	}

	public boolean isPathChecked(TreePath path) {
		return false;
	}

	public boolean isPathEnabled(TreePath path) {
		return true;
	}

	public boolean isPathGreyed(TreePath path) {
		return false;
	}

	public void removeCheckingPath(TreePath path) {
	}

	public void removeCheckingPaths(TreePath[] paths) {
	}

	public void removeTreeCheckingListener(TreeCheckingListener tcl) {
	}

	public void setCheckingMode(CheckingMode mode) {
	}

	public void setCheckingPath(TreePath path) {
	}

	public void setCheckingPaths(TreePath[] paths) {
	}

	public void setPathEnabled(TreePath path, boolean enable) {
	}

	public void setPathsEnabled(TreePath[] paths, boolean enable) {
	}

	public void toggleCheckingPath(TreePath pathForRow) {
	}

}

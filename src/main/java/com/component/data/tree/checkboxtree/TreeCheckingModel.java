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
 * The interface of a model for checking/unchecking the nodes of a CheckboxTree.
 * Alterations of a node state may propagate to descendants/ancestors, according
 * to the behaviour of the checking model. See CheckingMode for the available
 * behaviours.
 *
 * @author bigagli
 * @author boldrini
 */
public interface TreeCheckingModel {

	/**
	 * The checking behaviors supported by this class.
	 */
	enum CheckingMode {
		/*
		 * TODO: this should be moved to DefaultTreeCheckingModel, together with
		 * TreeCheckingMode
		 */

		/**
		 * Toggles the clicked checkbox and propagates the change down. In other
		 * words, if the clicked checkbox becomes checked, all the descendants
		 * will be checked; otherwise, all the descendants will be unchecked.
		 */
		PROPAGATE,

		/**
		 * Propagates the change not only to descendants but also to ancestors.
		 * With regard to descendants this mode behaves exactly like the
		 * Propagate mode. With regard to ancestors it checks/unchecks them as
		 * needed so that a node is checked if and only if all of its children
		 * are checked.
		 */
		PROPAGATE_PRESERVING_CHECK,

		/**
		 * Propagates the change not only to descendants but also to ancestors.
		 * With regard to descendants this mode behaves exactly like the
		 * Propagate mode. With regard to ancestors it checks/unchecks them as
		 * needed so that a node is unchecked if and only if all of its children
		 * are unchecked.
		 */
		PROPAGATE_PRESERVING_UNCHECK,

		/**
		 * The change is propagated to descendants like in the PROPAGATE mode.
		 * Moreover, if the checkbox becomes unchecked, all the ancestors will
		 * be unchecked.
		 */
		PROPAGATE_UP_UNCHECK,

		/**
		 * The check is not propagated at all, toggles the clicked checkbox
		 * only.
		 */
		SIMPLE,

		/**
		 * The check is not propagated at all, toggles the clicked checkbox
		 * only. Only one checkbox is allowed to be checked at any given time.
		 */
		SINGLE

	}

	/**
	 * add a path to the checking set.
	 *
	 * @param path the path to be added.
	 */
	void addCheckingPath(TreePath path);

	/**
	 * add paths to the checking set.
	 *
	 * @param paths the paths to be added.
	 */
	void addCheckingPaths(TreePath[] paths);

	/**
	 * Adds the specified listener to the list of those being notified upon
	 * changes in the the checking set.
	 *
	 * @param tcl the new listener to be added.
	 */
	void addTreeCheckingListener(TreeCheckingListener tcl);

	/**
	 * Clears the checking.
	 */
	void clearChecking();

	/**
	 * @return Returns the CheckingMode.
	 */
	CheckingMode getCheckingMode();

	/**
	 * @return Returns the paths that are in the checking set.
	 */
	TreePath[] getCheckingPaths();

	/**
	 * @return Returns the paths that are in the checking set and are the
	 * (upper) roots of checked trees.
	 */
	TreePath[] getCheckingRoots();

	/**
	 * @return Returns the paths that are in the greying set.
	 */
	TreePath[] getGreyingPaths();

	/**
	 * Returns true if the item identified by the path is currently checked.
	 *
	 * @param path a <code>TreePath</code> identifying a node
	 * @return true if the node is checked
	 */
	boolean isPathChecked(TreePath path);

	/**
	 * Returns whether the specified path checking state can be toggled.
	 */
	boolean isPathEnabled(TreePath path);

	/**
	 * Returns whether the specified path is greyed.
	 */
	boolean isPathGreyed(TreePath path);

	/**
	 * Removes a path from the checking set.
	 *
	 * @param path the path to be removed.
	 */
	void removeCheckingPath(TreePath path);

	/**
	 * Remove the specified paths from the checking set.
	 *
	 * @param paths the paths to be added.
	 */
	void removeCheckingPaths(TreePath[] paths);

	/**
	 * Removes the specified listener from the list of those being notified upon
	 * changes in the checking set.
	 *
	 * @param tcl the listener to remove.
	 */
	void removeTreeCheckingListener(TreeCheckingListener tcl);

	/**
	 * Sets the specified checking mode.
	 *
	 * @param mode the checking mode to set.
	 */
	void setCheckingMode(CheckingMode mode);

	/**
	 * (Re)sets the checking to the specified path.
	 */
	void setCheckingPath(TreePath path);

	/**
	 * (Re)sets the checking to the specified paths.
	 */
	void setCheckingPaths(TreePath[] paths);

	/**
	 * Sets whether or not the specified path can be toggled.
	 *
	 * @param path the path to enable/disable
	 */
	void setPathEnabled(TreePath path, boolean enable);

	/**
	 * Sets whether or not the specified paths can be toggled.
	 *
	 * @param paths the paths to enable/disable
	 */
	void setPathsEnabled(TreePath[] paths, boolean enable);

	/**
	 * Toggles (check/uncheck) the checking state of the specified path, if this
	 * is enabled, and possibly propagate the change, according to the checking
	 * mode.
	 */
	void toggleCheckingPath(TreePath pathForRow);

}

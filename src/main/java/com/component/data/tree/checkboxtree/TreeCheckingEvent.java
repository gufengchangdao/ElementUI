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
import java.util.EventObject;

/**
 * An event that characterizes a change in the current checking. The change is
 * related to a single checked/unchecked path. TreeCheckingListeners will
 * generally query the source of the event for the new checked status of each
 * potentially changed row.
 *
 * @author Lorenzo Bigagli
 * @see TreeCheckingListener
 * @see TreeCheckingModel
 */
public class TreeCheckingEvent extends EventObject {
	/** The path related to this event */
	protected TreePath changedPath;

	private boolean checked;

	/**
	 * Represents a change in the checking of a TreeCheckingModel. The specified
	 * path identifies the path that have been either checked or unchecked.
	 *
	 * @param source  source of event
	 * @param path    the path that has changed in the checking
	 * @param checked whether or not the path is checked, false means that path was
	 *                removed from the checking.
	 */
	public TreeCheckingEvent(Object source, TreePath path, boolean checked) {
		super(source);
		this.changedPath = path;
		this.checked = checked;
	}

	/**
	 * @return the path that was added or removed from the checking.
	 */
	public TreePath getPath() {
		return this.changedPath;
	}

	/**
	 * @return true if the path related to the event is checked. A return value
	 * of false means that the path has been removed from the checking.
	 */
	public boolean isCheckedPath() {
		return checked;
	}

}

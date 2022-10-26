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

import java.util.EventListener;

/**
 * The listener notified when the checking in a TreeCheckingModel changes.
 *
 * @author Enrico Boldrini
 * @see TreeCheckingModel
 * @see CheckboxTree
 */
public interface TreeCheckingListener extends EventListener {
	/**
	 * Called whenever the value of the checking changes.
	 *
	 * @param e the event that characterizes the change.
	 */
	void valueChanged(TreeCheckingEvent e);
}
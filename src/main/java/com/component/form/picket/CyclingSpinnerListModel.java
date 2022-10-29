/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.component.form.picket;

import javax.swing.*;

/**
 * 该SpinnerListModel只与Object数组一起工作，并且实现了循环(下一个值和上一个值从不为空)。
 * 它还允许您可选地关联一个链接到此模型的旋转器模型，以便在发生循环时更新链接的旋转器模型。
 */
public class CyclingSpinnerListModel extends SpinnerListModel {
	Object firstValue, lastValue;
	SpinnerModel linkedModel = null;

	public CyclingSpinnerListModel(Object[] values) {
		super(values);
		firstValue = values[0];
		lastValue = values[values.length - 1];
	}

	public void setLinkedModel(SpinnerModel linkedModel) {
		this.linkedModel = linkedModel;
	}

	public Object getNextValue() {
		Object value = super.getNextValue();
		if (value == null) {
			value = firstValue;
			if (linkedModel != null) {
				linkedModel.setValue(linkedModel.getNextValue());
			}
		}
		return value;
	}

	public Object getPreviousValue() {
		Object value = super.getPreviousValue();
		if (value == null) {
			value = lastValue;
			if (linkedModel != null) {
				linkedModel.setValue(linkedModel.getPreviousValue());
			}
		}
		return value;
	}
}

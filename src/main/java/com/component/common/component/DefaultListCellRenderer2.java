package com.component.common.component;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 基础JList渲染器
 *
 * <ul>
 *     <li>支持占位符，需要设置不可编辑，当未选择时会显示占位符</li>
 * </ul>
 */
public class DefaultListCellRenderer2 extends DefaultListCellRenderer {
	/** 占位符 */
	private String placeholder = "";

	public DefaultListCellRenderer2() {
	}

	public DefaultListCellRenderer2(String placeholder) {
		this.placeholder = placeholder;
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		value = Objects.toString(value, placeholder);
		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
}

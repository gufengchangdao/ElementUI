package com.component.form.list.renderer;

import com.component.basic.color.ColorUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 复选框单元格样式的渲染器
 */
public class CheckBoxCellRenderer implements ListCellRenderer<String> {
	private JCheckBox c = new JCheckBox();
	private Color oldForeground = c.getForeground(); // UIManager.getColor("CheckBox.foreground")

	public CheckBoxCellRenderer() {
		// c.setOpaque(false);
		c.setBackground(null);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
		c.setText(value);
		c.setSelected(isSelected);
		if (isSelected) {
			c.setForeground(ColorUtil.PRIMARY);
		} else {
			c.setForeground(oldForeground);
		}
		return c;
	}
}

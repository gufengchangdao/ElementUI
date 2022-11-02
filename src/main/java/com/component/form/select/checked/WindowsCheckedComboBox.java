package com.component.form.select.checked;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * 带有复选框的下拉列表
 *
 * @param <E>
 */
public class WindowsCheckedComboBox<E extends CheckableItem> extends CheckedComboBox<E> {
	private transient ActionListener listener;

	public WindowsCheckedComboBox(ComboBoxModel<E> model) {
		super(model);
	}

	@Override
	public void updateUI() {
		setRenderer(null);
		removeActionListener(listener);
		super.updateUI();
		listener = e -> {
			if ((e.getModifiers() & AWTEvent.MOUSE_EVENT_MASK) != 0) {
				keepOpen = true;
				updateItem(getSelectedIndex());
			}
		};
		addActionListener(listener);

		JLabel label = new JLabel(" ");
		JCheckBox check = new JCheckBox(" ");
		setRenderer((list, value, index, isSelected, cellHasFocus) -> {
			if (index < 0) {
				// 输入框内容，获取列表中所有选择内容
				String txt = getCheckedItemString(list.getModel());
				label.setText(txt.isEmpty() ? " " : txt);
				return label;
			} else {
				check.setText(Objects.toString(value, ""));
				check.setSelected(value.isSelected());
				if (isSelected) {
					check.setBackground(list.getSelectionBackground());
					check.setForeground(list.getSelectionForeground());
				} else {
					check.setBackground(list.getBackground());
					check.setForeground(list.getForeground());
				}
				return check;
			}
		});
		initActionMap();
	}
}
package com.component.form.input.compound;

import com.component.common.component.BaseComponent;

import javax.swing.*;
import java.awt.*;

/**
 * label和下拉列表的简单组合
 *
 * @param <T> 下拉列表中元素的类型
 */
public class LabelComboBox<T> extends BaseComponent {
	private String title;
	private JComboBox<T> comboBox;
	private ComboBoxModel<T> comboBoxModel;

	public LabelComboBox(String title, T[] data) {
		this.title = title;
		comboBoxModel = new DefaultComboBoxModel<>(data);
		comboBox = new JComboBox<>(comboBoxModel);

		init();
	}

	private void init() {
		setLayout(new BorderLayout());
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		label.setFont(label.getFont().deriveFont(15f));
		add(label, BorderLayout.WEST);
		add(comboBox);
	}

	public T getSelected() {
		return (T) comboBoxModel.getSelectedItem();
	}
}

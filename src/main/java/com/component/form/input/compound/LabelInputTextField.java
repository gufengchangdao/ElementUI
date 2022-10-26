package com.component.form.input.compound;

import com.component.common.component.BaseComponent;

import javax.swing.*;
import java.awt.*;

/**
 * label和Input的简单组合
 */
public class LabelInputTextField extends BaseComponent {
	private String title;
	private JTextField textField;

	public LabelInputTextField(String title) {
		this.title = title;
		textField = new JTextField();
		init();
	}

	public LabelInputTextField(String title, int columns) {
		this.title = title;
		textField = new JTextField(columns);
		init();
	}

	private void init() {
		setLayout(new BorderLayout());
		JLabel label = new JLabel(title);

		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		add(label, BorderLayout.WEST);
		add(textField);
	}

	public JTextField getTextField() {
		return textField;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return textField.getText();
	}

	public void setText(String t) {
		textField.setText(t);
	}
}

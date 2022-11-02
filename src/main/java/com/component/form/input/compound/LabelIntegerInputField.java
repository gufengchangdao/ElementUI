package com.component.form.input.compound;

import com.component.common.component.BaseComponent;
import com.component.form.input.spinner.InputNumberField;

import javax.swing.*;
import java.awt.*;

/**
 * label和数字输入框的简单组合
 */
public class LabelIntegerInputField extends BaseComponent {
	private String title;
	private InputNumberField inputNumberField;

	public LabelIntegerInputField(String title) {
		this.title = title;
		inputNumberField = new InputNumberField();
		init();
	}

	private void init() {
		setLayout(new BorderLayout());
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		label.setFont(label.getFont().deriveFont(15f));
		add(label, BorderLayout.WEST);
		add(inputNumberField);
	}

	public int getNum() {
		return inputNumberField.getNum();
	}

	public void setNum(int num) {
		inputNumberField.setNum(num);
	}

	public String getText() {
		return inputNumberField.getText();
	}

	public String getTitle() {
		return title;
	}

	public InputNumberField getIntegerInputField() {
		return inputNumberField;
	}

}

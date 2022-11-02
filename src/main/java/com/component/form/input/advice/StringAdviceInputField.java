package com.component.form.input.advice;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 带输入建议的输入框，用 JComboBox 直接实现
 */
public class StringAdviceInputField extends JComboBox<String>
		implements KeyListener, FocusListener, MouseListener {
	private List<String> data = new ArrayList<>();

	public StringAdviceInputField(ComboBoxModel<String> aModel) {
		super(aModel);
		init();
	}

	public StringAdviceInputField(String[] items) {
		super(items);
		init();
	}

	public StringAdviceInputField(Vector<String> items) {
		super(items);
		init();
	}

	public StringAdviceInputField() {
		init();
	}

	private void init() {
		setEditable(true);
		setSelectedIndex(-1);
		JTextField field = (JTextField) getEditor().getEditorComponent();

		// 鼠标点击输入框弹出和隐藏下拉列表
		field.addFocusListener(this);
		field.addMouseListener(this);
		field.addKeyListener(this);
	}

	public void showPopup() {
		MutableComboBoxModel<String> model = (MutableComboBoxModel<String>) getModel();

		// 临时存储数据
		data.clear();
		for (int i = 0, len = model.getSize(); i < len; i++)
			data.add(model.getElementAt(i));

		JTextField field = (JTextField) getEditor().getEditorComponent();
		String keyword = field.getText();
		removeAllItems();
		data.stream()
				.filter(t -> t.contains(keyword))
				.forEach(model::addElement);
		// setSelectedIndex(-1);
		field.setText(keyword);

		super.showPopup();
	}

	@Override
	public void hidePopup() {
		// 数据还回
		if (data.size() != 0) {
			MutableComboBoxModel<String> model = (MutableComboBoxModel<String>) getModel();
			JTextField field = (JTextField) getEditor().getEditorComponent();
			String keyword = field.getText();

			removeAllItems();
			data.forEach(model::addElement);
			// setSelectedIndex(-1);
			field.setText(keyword);
		}
		super.hidePopup();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		MutableComboBoxModel<String> model = (MutableComboBoxModel<String>) getModel();
		JTextField field = (JTextField) e.getComponent();
		String keyword = field.getText();
		removeAllItems();
		data.stream()
				.filter(t -> t.contains(keyword))
				.forEach(model::addElement);
		field.setText(keyword);

		super.hidePopup();
		super.showPopup();
	}

	@Override
	public void focusGained(FocusEvent e) {
		showPopup();
	}

	@Override
	public void focusLost(FocusEvent e) {
		hidePopup();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isPopupVisible()) showPopup();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public String getText() {
		Object item = getSelectedItem();
		return item == null ? null : item.toString();
	}
}

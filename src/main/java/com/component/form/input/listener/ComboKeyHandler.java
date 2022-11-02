package com.component.form.input.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 为给定JComboBox添加键盘监听器，实现带有输入建议的JComboBox
 * <p>
 * 注意，该类并没有实现监听列表元素变化的事件，建议列表元素不会改变，如果需要变化，可以获取到list手动更新
 * <p>
 * 快捷键
 * <ul>
 *     <li>右：补全</li>
 *     <li>enter：输入框内容添加到建议列表中</li>
 *     <li>esc：因此建议列表</li>
 * </ul>
 */
public class ComboKeyHandler extends KeyAdapter {
	private final JComboBox<String> comboBox;
	private final List<String> list = new ArrayList<>();
	private boolean shouldHide;

	/**
	 * 简单封装
	 * <p>
	 * 设置 JComboBox 为可编辑、有建议列表
	 */
	public static void addAdviceListener(JComboBox<String> combo) {
		combo.setEditable(true);
		combo.setSelectedIndex(-1);
		JTextField field = (JTextField) combo.getEditor().getEditorComponent();
		field.setText("");
		field.addKeyListener(new ComboKeyHandler(combo));
	}

	protected ComboKeyHandler(JComboBox<String> combo) {
		super();
		this.comboBox = combo;
		for (int i = 0, len = comboBox.getModel().getSize(); i < len; i++) {
			list.add(comboBox.getItemAt(i));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		EventQueue.invokeLater(() -> { //一定要放在事件队列中，不然下拉列表会延迟弹出
			String text = ((JTextField) e.getComponent()).getText();
			// 直接换一个新的model
			ComboBoxModel<String> m;
			if (text.isEmpty()) {
				m = new DefaultComboBoxModel<>(list.toArray(new String[0]));
				setSuggestionModel(comboBox, m, "");
				comboBox.hidePopup();
			} else {
				m = getSuggestedModel(list, text);
				if (m.getSize() == 0 || shouldHide) {
					comboBox.hidePopup();
				} else {
					setSuggestionModel(comboBox, m, text);
					comboBox.showPopup();
				}
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		JTextField textField = (JTextField) e.getComponent();
		String text = textField.getText();
		shouldHide = false;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: //补全
				for (String s : list) {
					if (s.startsWith(text)) {
						textField.setText(s);
						return;
					}
				}
				break;
			case KeyEvent.VK_ENTER: //添加到建议列表中
				if (!list.contains(text)) {
					list.add(text);
					list.sort(Comparator.naturalOrder());
					setSuggestionModel(comboBox, getSuggestedModel(list, text), text);
				}
				shouldHide = true;
				break;
			case KeyEvent.VK_ESCAPE:
				shouldHide = true;
				break;
			default:
				break;
		}
	}

	/**
	 * 为JComboBox设置数据模型，并设置输入框的值为指定值
	 *
	 * @param combo JComboBox
	 * @param mdl   新数据模型
	 * @param value 输入框的值
	 */
	private static <E> void setSuggestionModel(JComboBox<E> combo, ComboBoxModel<E> mdl, E value) {
		combo.setModel(mdl);
		combo.setSelectedIndex(-1);
		((JTextField) combo.getEditor().getEditorComponent()).setText(Objects.toString(value));
	}

	/**
	 * 由给定列表数据创建新的model并设置给JComboBox
	 *
	 * @param list 新model中的数据
	 * @param text 输入框文本值
	 */
	private static ComboBoxModel<String> getSuggestedModel(List<String> list, String text) {
		DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
		for (String s : list) {
			if (s.startsWith(text)) {
				m.addElement(s);
			}
		}
		return m;
	}

	public List<String> getList() {
		return list;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}
}

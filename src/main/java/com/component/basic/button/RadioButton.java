package com.component.basic.button;

import com.component.common.component.BaseComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 按钮样式的单选组合，也可以作为复选框
 */
public class RadioButton extends BaseComponent {
	private List<JToggleButton> buttons = new ArrayList<>();
	/** 是否是单选 */
	private boolean isRadio = true;
	// private Consumer<Integer> selectedOper; //选中之后做什么

	public RadioButton(List<String> data) {
		// ButtonGroup buttonGroup = new ButtonGroup(); //这个只能单选
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		for (String d : data) createButton(d);
	}

	/** 处理新增数据，返回值可用可不用 */
	private JToggleButton createButton(String d) {
		JToggleButton button = new JToggleButton(d);
		button.addActionListener(e -> {
			if (button.isSelected()) {
				if (isRadio)
					buttons.stream()
							.filter(jToggleButton -> jToggleButton != button)
							.forEach(jToggleButton -> jToggleButton.setSelected(false));
			}
			// if (!button.isSelected()) { //取消选中
			// }
		});

		buttons.add(button);
		add(button);
		return button;
	}

	public synchronized void addData(String data) {
		createButton(data);
	}

	public synchronized void removeData(String data) {
		buttons = buttons.stream()
				.filter(d -> !d.getText().equals(data))
				.collect(Collectors.toList());
	}

	public List<String> getAllData() {
		return buttons.stream()
				.map(AbstractButton::getText)
				.collect(Collectors.toList());
	}

	public List<String> getSelectedList() {
		return buttons.stream()
				.filter(AbstractButton::isSelected)
				.map(AbstractButton::getText)
				.collect(Collectors.toList());
	}

	/** 获取单选下选中的按钮文本，多选时选取第一个 */
	public String getSelected() {
		return buttons.stream()
				.filter(AbstractButton::isSelected)
				.findFirst()
				.map(AbstractButton::getText)
				.orElse(null);
	}

	public List<String> getUnSelectedList() {
		return buttons.stream()
				.filter(b -> !b.isSelected())
				.map(AbstractButton::getText)
				.collect(Collectors.toList());
	}

	public void setButtonPreferredSize(Dimension preferredSize) {
		for (JToggleButton b : buttons) {
			b.setPreferredSize(preferredSize);
		}
	}

	public boolean isRadio() {
		return isRadio;
	}

	public void setRadio(boolean radio) {
		isRadio = radio;
	}

	public List<JToggleButton> getButtons() {
		return buttons;
	}

	@Override
	public String toString() {
		return "RadioButton{" +
				"buttons=" + buttons +
				", isRadio=" + isRadio +
				'}';
	}
}

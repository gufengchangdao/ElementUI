package com.component.form.select;

import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/** 节点选择器，每个节点是一个列表 */
public class ItemSelector extends JWindow implements ActionListener {
	/** 节点相对于组件(com)的位置 */
	public enum Position {
		LEFT,
		RIGHT,
		TOP,
		BOTTOM;
	}

	/** 选项之间的分割符 */
	private String separator;
	/** 当前节点所选项 */
	private JButton selectItem;
	private Map<JButton, ItemSelector> buttons;
	/** 用于把选择写入进去 */
	private JTextField textField;
	/** 该组件用于定位 */
	private Component com;
	/** 上一个节点 */
	private ItemSelector lastNode;

	private Position position;

	public ItemSelector(Map<String, ?> data, JTextField textField,
	                    Component com, Position position, String separator) {
		this.textField = textField;
		this.com = com;
		this.lastNode = this;
		this.position = position;
		this.separator = separator;
		setLayout(new VerticalLayout());

		buttons = new HashMap<>();
		for (Map.Entry<String, ?> entry : data.entrySet()) {
			String key = entry.getKey();
			Object v = entry.getValue();
			JButton b = new JButton(key);

			b.addActionListener(this);

			if (v == null) {
				buttons.put(b, null);
			} else if (v instanceof Map) {
				// 创建子节点，子节点位于当前节点的右边
				ItemSelector childNode = new ItemSelector((Map<String, ?>) v,
						textField, this, Position.RIGHT, separator);
				childNode.setLastNode(this);
				buttons.put(b, childNode);
			} else {
				throw new IllegalArgumentException("不支持的类型");
			}

			getContentPane().add(b);
		}

		pack();
	}

	public void adjustPosition() {
		Point location = com.getLocationOnScreen();
		switch (position) {
			case LEFT:
				setLocation(location.x - com.getPreferredSize().width, location.y);
				break;
			case RIGHT:
				setLocation(location.x + com.getPreferredSize().width, location.y);
				break;
			case TOP:
				setLocation(location.x, location.y - com.getPreferredSize().height);
				break;
			case BOTTOM:
				setLocation(location.x, location.y + com.getPreferredSize().height);
				break;
		}
	}

	public void adjustSize() {
		Dimension size = getLayout().preferredLayoutSize(getContentPane());
		setSize(size);
	}

	/** 根据组件位置设置窗口的位置，每次显示窗口之前调用，即同setVisible(true)一起调用 */
	public void showSelector() {
		adjustPosition();
		setVisible(true);
	}

	/** 使用递归的方式返回从当前节点所选值，要获取所有值建议使用textField.getText() */
	public String getVal() {
		if (selectItem == null) return "";
		String val = selectItem.getText();
		ItemSelector childNode = buttons.get(selectItem);
		return val + (childNode == null ? "" : (separator + childNode.getVal()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ItemSelector root = getRootNode();
		if (textField != null) {
			if (selectItem != null) {
				selectItem = (JButton) e.getSource();
				// 当前已有选中项，说明展开了更高级别按钮，但是点的却不是高级别按钮
				textField.setText(getSelectedFromRoot(root, selectItem, separator));
			} else {
				selectItem = (JButton) e.getSource();

				String s = textField.getText();
				// 如果是根节点，表示从头开始选择
				if (root == this)
					textField.setText(selectItem.getText());
				else
					textField.setText("".equals(s) ? selectItem.getText() : (s + separator + selectItem.getText()));
			}
		}

		ItemSelector childNode = buttons.get(selectItem);
		if (childNode == null) {
			// 表示是叶子节点，此时要隐藏所有节点
			setAllInVisible(root);
			return;
		}
		childNode.showSelector();
	}


	public void addChild(String val) {
		JButton b = new JButton(val);
		b.addActionListener(this);
		if (buttons == null)
			buttons = new HashMap<>();
		buttons.put(b, null);
		getContentPane().add(b);
		adjustSize();
	}

	public void removeChild(String val) {
		if (buttons == null) return;
		buttons.keySet().stream()
				.filter(button -> button.getText().equals(val))
				.findAny().ifPresent(b -> {
					if (selectItem == b) selectItem = null;
					buttons.remove(b);
					getContentPane().remove(b);
				});
		if (buttons.size() == 0) { //使用父节点删除该节点
			lastNode.buttons.entrySet().stream()
					.filter(entry -> entry.getValue().equals(this))
					.findAny()
					.ifPresent(entry -> lastNode.removeChild(entry.getKey().getText()));
		}
		adjustSize();
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setLastNode(ItemSelector lastNode) {
		this.lastNode = lastNode;
	}

	public String getSeparator() {
		return separator;
	}

	public JButton getSelectItem() {
		return selectItem;
	}


	public Map<JButton, ItemSelector> getButtons() {
		return buttons;
	}

	public JTextField getTextField() {
		return textField;
	}

	public Component getCom() {
		return com;
	}

	/** 递归获取根节点 */
	public ItemSelector getRootNode() {
		ItemSelector root = this;
		while (root != root.lastNode) {
			root = root.getLastNode();
		}
		return root;
	}

	public ItemSelector getLastNode() {
		return lastNode;
	}

	public Position getPosition() {
		return position;
	}

	/** 使当前节点以及子节点不可见 */
	public static void setAllInVisible(ItemSelector node) {
		node.setVisible(false);
		for (ItemSelector n : node.getButtons().values()) {
			if (n != null) setAllInVisible(n);
		}
	}

	/** 获取从跟节点出来到指定节点的选择 */
	public static String getSelectedFromRoot(ItemSelector root, JButton selectItem, String separator) {
		StringBuilder res = new StringBuilder();
		boolean isFirst = true;
		while (root != null && root.getSelectItem() != null) {
			if (isFirst) isFirst = false;
			else res.append(separator);
			JButton b = root.getSelectItem();
			res.append(b.getText());
			if (b == selectItem) break;
			root = root.getButtons().get(b);
		}
		return res.toString();
	}
}

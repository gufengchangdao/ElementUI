package com.component.form.select.removable;

import com.component.basic.color.ColorUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 负责按钮的绘制、删除
 */
class ButtonsRenderer<E> implements ListCellRenderer<E> {
	private static final Color EVEN_COLOR = ColorUtil.BORDER_LEVEL4;
	protected int targetIndex;
	/** 悬停的按钮索引，如果鼠标没有悬停在任何按钮上，则为 -1 */
	protected int rolloverIndex = -1;
	private final JPanel panel = new JPanel(new BorderLayout()) { // *1
		// 重写方法，使列表项宽度为 0，防止当 JComboBox 设置了大小后，列表项文本过长导致按钮不可见情况
		@Override
		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			d.width = 0;
			return d;
		}
	};
	private final ListCellRenderer<? super E> renderer = new DefaultListCellRenderer();
	/** 关闭按钮 */
	private final JButton deleteButton = new JButton("x") {
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(16, 16);
		}

		@Override
		public void updateUI() {
			super.updateUI();
			setBorder(BorderFactory.createEmptyBorder());
			setFocusable(false);
			setRolloverEnabled(false);
			setContentAreaFilled(false);
		}
	};

	protected ButtonsRenderer(RemoveButtonComboBox<E> comboBox) {
		deleteButton.addActionListener(e -> {
			ComboBoxModel<E> m = comboBox.getModel();
			boolean oneOrMore = m.getSize() > 1;
			//删除列表项并继续展示下拉列表
			if (oneOrMore && m instanceof MutableComboBoxModel) {
				((MutableComboBoxModel<?>) m).removeElementAt(targetIndex);
				comboBox.setSelectedIndex(-1);
				comboBox.showPopup();
			}
		});
		panel.setOpaque(true);
		panel.add(deleteButton, BorderLayout.EAST);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
		Component c = renderer.getListCellRendererComponent(
				list, value, index, isSelected, cellHasFocus);
		if (index < 0) {
			return c;
		}
		// 让panel的背景在label上显示出来
		if (c instanceof JComponent) {
			((JComponent) c).setOpaque(false);
		}
		this.targetIndex = index;
		if (isSelected) {
			panel.setBackground(list.getSelectionBackground());
		} else {
			// 斑马条纹
			panel.setBackground(index % 2 == 0 ? EVEN_COLOR : list.getBackground());
		}
		boolean showDeleteButton = list.getModel().getSize() > 1;
		deleteButton.setVisible(showDeleteButton);
		if (showDeleteButton) {
			boolean isRollover = index == rolloverIndex;
			deleteButton.getModel().setRollover(isRollover);
			deleteButton.setForeground(isRollover ? Color.WHITE : list.getForeground());
		}
		panel.add(c);
		return panel;
	}
}

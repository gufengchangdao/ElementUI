package com.component.form.select.checked;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 带有复选框的下拉列表
 */
public class CheckedComboBox<E extends CheckableItem> extends JComboBox<E> {
	protected boolean keepOpen;
	private final JPanel panel = new JPanel(new BorderLayout());

	public CheckedComboBox(ComboBoxModel<E> model) {
		super(model);
	}

	@Override
	public void updateUI() {
		setRenderer(null);
		super.updateUI();

		Accessible a = getAccessibleContext().getAccessibleChild(0);
		if (a instanceof ComboPopup) {
			// 获取到下拉列表
			((ComboPopup) a).getList().addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JList<?> list = (JList<?>) e.getComponent();
					if (SwingUtilities.isLeftMouseButton(e)) {
						keepOpen = true;
						updateItem(list.locationToIndex(e.getPoint()));
					}
				}
			});
		}

		DefaultListCellRenderer renderer = new DefaultListCellRenderer();
		JCheckBox check = new JCheckBox();
		check.setOpaque(false);

		setRenderer((list, value, index, isSelected, cellHasFocus) -> {
			panel.removeAll();
			Component c = renderer.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);
			if (index < 0) {
				String txt = getCheckedItemString(list.getModel());
				JLabel l = (JLabel) c;
				l.setText(txt.isEmpty() ? " " : txt);
				l.setOpaque(false);
				l.setForeground(list.getForeground());
				panel.setOpaque(false);
			} else {
				check.setSelected(value.isSelected());
				panel.add(check, BorderLayout.WEST);
				panel.setOpaque(true);
				panel.setBackground(c.getBackground());
			}
			panel.add(c);
			return panel;
		});
		initActionMap();
	}

	protected void initActionMap() {
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
		getInputMap(JComponent.WHEN_FOCUSED).put(ks, "checkbox-select");
		getActionMap().put("checkbox-select", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Accessible a = getAccessibleContext().getAccessibleChild(0);
				if (a instanceof ComboPopup) {
					updateItem(((ComboPopup) a).getList().getSelectedIndex());
				}
			}
		});
	}

	protected void updateItem(int index) {
		if (isPopupVisible() && index >= 0) {
			E item = getItemAt(index);
			item.setSelected(!item.isSelected());
			// 没有这两句，复选框的更新会慢一步
			setSelectedIndex(-1);
			setSelectedItem(item);
		}
	}

	@Override
	public void setPopupVisible(boolean v) {
		if (keepOpen) {
			keepOpen = false;
		} else {
			super.setPopupVisible(v);
		}
	}

	protected static <E extends CheckableItem> String getCheckedItemString(ListModel<E> model) {
		return IntStream.range(0, model.getSize())
				.mapToObj(model::getElementAt)
				.filter(CheckableItem::isSelected)
				.map(Objects::toString)
				.sorted()
				.collect(Collectors.joining(", "));
	}
}

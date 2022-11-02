package com.component.form.select.removable;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;
import java.util.Objects;
import java.util.Optional;

/**
 * 下拉列表项含有可移除按钮的JComboBox
 *
 * @param <E>
 */
public class RemoveButtonComboBox<E> extends JComboBox<E> {
	private transient CellButtonsMouseListener listener;

	public RemoveButtonComboBox(ComboBoxModel<E> model) {
		super(model);
	}

	@Override
	public void updateUI() {
		if (Objects.nonNull(listener)) {
			getList().ifPresent(list -> {
				list.removeMouseListener(listener);
				list.removeMouseMotionListener(listener);
			});
		}
		super.updateUI();
		// 渲染器直接在list上设置，不然在有些外观下会失效
		// setRenderer(new ButtonsRenderer<>(this));
		getList().ifPresent(list -> {
			((JList<E>) list).setCellRenderer(new ButtonsRenderer<>(this));
			listener = new CellButtonsMouseListener();
			list.addMouseListener(listener);
			list.addMouseMotionListener(listener);
		});
	}

	protected Optional<JComponent> getList() {
		Accessible a = getAccessibleContext().getAccessibleChild(0);
		if (a instanceof ComboPopup) {
			return Optional.of(((ComboPopup) a).getList());
		}
		return Optional.empty();
	}
}


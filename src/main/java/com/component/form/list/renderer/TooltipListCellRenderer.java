package com.component.form.list.renderer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Optional;

/**
 * 列表提示渲染器，鼠标悬停时弹出提示文本
 */
public class TooltipListCellRenderer<E> implements ListCellRenderer<E> {
	private final ListCellRenderer<? super E> renderer = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends E> list, E value, int index,
	                                              boolean isSelected, boolean cellHasFocus) {
		Component c = renderer.getListCellRendererComponent(
				list, value, index, isSelected, cellHasFocus);
		if (c instanceof JComponent) {
			Class<JViewport> clz = JViewport.class;
			Rectangle rect = Optional.ofNullable(SwingUtilities.getAncestorOfClass(clz, list))
					.filter(clz::isInstance)
					.map(clz::cast)
					.map(v -> SwingUtilities.calculateInnerArea(v, null))
					.orElseGet(Rectangle::new);

			FontMetrics fm = c.getFontMetrics(c.getFont());
			String str = Objects.toString(value, "");
			((JComponent) c).setToolTipText(fm.stringWidth(str) > rect.width ? str : null);
		}
		return c;
	}
}
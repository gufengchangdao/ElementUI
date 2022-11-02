package com.component.form.select.removable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Optional;

/**
 * 下拉列表的监听器，监听鼠标在下拉列表上的移动，并通过 ButtonsRenderer 来模拟按钮的悬停、点击等事件
 */
class CellButtonsMouseListener extends MouseAdapter {
	@Override
	public void mouseMoved(MouseEvent e) {
		JList<?> list = (JList<?>) e.getComponent();
		Point pt = e.getPoint();
		int index = list.locationToIndex(pt);

		ButtonsRenderer<?> renderer = (ButtonsRenderer<?>) list.getCellRenderer();
		renderer.rolloverIndex = Objects.nonNull(getButton(list, pt, index)) ? index : -1;
		list.repaint(); // repaint all cells
	}

	@Override
	public void mousePressed(MouseEvent e) {
		e.getComponent().repaint(); // repaint all cells
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JList<?> list = (JList<?>) e.getComponent();
		Point pt = e.getPoint();
		int index = list.locationToIndex(pt);
		if (index >= 0) {
			JButton button = getButton(list, pt, index);
			if (Objects.nonNull(button)) {
				button.doClick();
				// Rectangle r = list.getCellBounds(index, index);
				// rectRepaint(list, r);
			}
		}
		((ButtonsRenderer<?>) list.getCellRenderer()).rolloverIndex = -1;
		list.repaint(); // repaint all cells
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JList<?> list = (JList<?>) e.getComponent();
		((ButtonsRenderer<?>) list.getCellRenderer()).rolloverIndex = -1;
	}

	/**
	 * 获取到鼠标悬停的按钮
	 *
	 * @param list  下拉列表
	 * @param pt    鼠标坐标
	 * @param index 鼠标所在列表索引值
	 * @param <E>   下拉列表元素
	 * @return 如果鼠标悬停在按钮上返回该按钮，否则返回null
	 */
	private static <E> JButton getButton(JList<E> list, Point pt, int index) {
		E proto = list.getPrototypeCellValue();
		ListCellRenderer<? super E> renderer = list.getCellRenderer();
		Component c = renderer.getListCellRendererComponent(list, proto, index, false, false);
		Rectangle r = list.getCellBounds(index, index);
		c.setBounds(r);
		// c.doLayout(); // may be needed for other layout managers (eg. FlowLayout) // *1
		pt.translate(-r.x, -r.y);
		return Optional.ofNullable(SwingUtilities.getDeepestComponentAt(c, pt.x, pt.y))
				.filter(JButton.class::isInstance)
				.map(JButton.class::cast)
				.orElse(null);
	}
}
package com.component.common.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

/**
 * 基础列表，支持获取鼠标是否悬停、悬停所在单元格索引值。
 *
 * @param <E> 数据类型
 */
public class BaseList<E> extends JList<E> {
	private boolean isEnter;
	private int hoverIndex = -1;

	public BaseList(ListModel<E> dataModel) {
		super(dataModel);
		init();
	}

	public BaseList(E[] listData) {
		super(listData);
		init();
	}

	public BaseList(Vector<? extends E> listData) {
		super(listData);
		init();
	}

	public BaseList() {
		init();
	}

	private void init() {
		addMouseMotionListener(new MML());
		addMouseListener(new ML());
	}

	public boolean isEnter() {
		return isEnter;
	}

	public int getHoverIndex() {
		return hoverIndex;
	}

	private class ML extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			isEnter = true;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			isEnter = false;
			hoverIndex = -1;
		}
	}

	private class MML extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			// 通过鼠标坐标找到单元格索引
			Dimension size = getPreferredSize();
			Rectangle s = getCellBounds(0, 0);
			int row = size.height / s.height;
			int col = size.width / s.width;

			if (getLayoutOrientation() == HORIZONTAL_WRAP) { //水平
				int r = (e.getY() + 1) / s.height;
				hoverIndex = r * col + e.getX() / s.width;
			} else {
				int c = (e.getX() + 1) / s.width; //满的有几列
				hoverIndex = c * row + e.getY() / s.height;
			}
			if (hoverIndex > getModel().getSize()) hoverIndex = -1;
		}
	}
}

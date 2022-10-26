package com.component.data.pagination;

import com.component.data.pagination.model.PageListModel;
import com.component.data.pagination.renderer.PageListCellRenderer;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 分页列表
 * <p>
 * 支持的功能有
 * <ul>
 *     <li>根据数据量自动分页</li>
 *     <li>渲染器设置选中、悬停单元格的背景色、字体色、是否绘制背景等</li>
 *     <li>模型设置数据总量、触发折叠的阈值、每页数据数量等</li>
 * </ul>
 */
public class PaginationList extends JList<Integer>
		implements MouseListener, MouseMotionListener {
	private PageListCellRenderer cellRenderer;
	private PageListModel model;

	public PaginationList(int count) {
		super(new PageListModel(count));
		init();
	}

	private void init() {
		// 拿到传入的model
		model = (PageListModel) super.getModel();
		// 水平排布
		setVisibleRowCount(1);
		setLayoutOrientation(HORIZONTAL_WRAP);

		setSelectedIndex(0);
		// 本来使用该监听器监听点击事件，结果修改model后调用updateUI第一次会抛空指针异常
		// 虽然后续不会再报错但仍没有解决空指针异常的问题，改用MouseMotionListener
		// addListSelectionListener(this);

		// 设置单元格渲染
		cellRenderer = new PageListCellRenderer();
		setCellRenderer(cellRenderer);

		addMouseMotionListener(this);
		addMouseListener(this);
		updateUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getSelectedIndex() != -1) {
			int selectedIndex = model.setSelectedIndex(getSelectedIndex());
			cellRenderer.setSelectedIndex(selectedIndex);
			setSelectedIndex(selectedIndex);
			updateUI();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		cellRenderer.setEnter(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		cellRenderer.setEnter(false);
		repaint(); //清除遗留的样式
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// 通过鼠标坐标找到单元格索引
		int w = getCellBounds(0, 0).width;
		cellRenderer.setHoverIndex(e.getX() / w);
		repaint();
	}

	@Override
	public PageListModel getModel() {
		return model;
	}

	public void setModel(PageListModel model) {
		this.model = model;
	}

	@Override
	public PageListCellRenderer getCellRenderer() {
		return cellRenderer;
	}

	public void setCellRenderer(PageListCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
	}
}

package com.component.data.pagination.renderer;

import com.component.basic.color.ColorUtil;
import com.component.data.pagination.model.PageListModel;

import javax.swing.*;
import java.awt.*;

/**
 * 分页列表的渲染器
 */
public class PageListCellRenderer extends DefaultListCellRenderer {
	/** 省略符 */
	private String omitStr = "...";
	// private String leftOmitStr = "》";
	// private String rightOmitStr = "《";
	private int hoverIndex = -1;
	private int selectedIndex = 0;
	private boolean isEnter = false;

	private Color selectedFG = Color.WHITE;
	private Color selectedBG = ColorUtil.PRIMARY;
	private Color hoverFG = selectedBG;
	private Color hoverBG = null;
	private boolean isBackgroundPainted = false;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                                              boolean isSelected, boolean cellHasFocus) {
		// 省略号
		setText(PageListModel.OMIT == (int) value ? omitStr : value.toString());

		Color fg, bg;
		// 鼠标悬停或选中
		if (selectedIndex == index) {
			fg = selectedFG;
			bg = selectedBG;
		} else if ((isEnter && hoverIndex == index)) {
			fg = hoverFG;
			bg = hoverBG;
		} else {
			fg = bg = null;
		}
		setForeground(fg);
		if (isBackgroundPainted) setBackground(bg);

		return this;
	}

	/**
	 * 获取鼠标悬停单元格所在索引
	 */
	public int getHoverIndex() {
		return hoverIndex;
	}

	/**
	 * 设置鼠标悬停单元格所在索引
	 */
	public void setHoverIndex(int hoverIndex) {
		this.hoverIndex = hoverIndex;
	}

	/**
	 * 鼠标当前是否在组件范围内
	 */
	public boolean isEnter() {
		return isEnter;
	}

	/**
	 * 设置鼠标当前是否在组件范围内
	 */
	public void setEnter(boolean enter) {
		isEnter = enter;
	}

	/**
	 * 获取所选单元格索引
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * 设置所选单元格索引
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	/**
	 * 获取省略符
	 */
	public String getOmitStr() {
		return omitStr;
	}

	/**
	 * 设置新的省略符
	 */
	public void setOmitStr(String omitStr) {
		this.omitStr = omitStr;
	}

	/**
	 * 获取所选单元格字体色
	 */
	public Color getSelectedFG() {
		return selectedFG;
	}

	/**
	 * 设置所选单元格字体色
	 */
	public void setSelectedFG(Color selectedFG) {
		this.selectedFG = selectedFG;
	}

	/**
	 * 获取所选单元格背景色
	 */
	public Color getSelectedBG() {
		return selectedBG;
	}

	/**
	 * 设置所选单元格背景色
	 */
	public void setSelectedBG(Color selectedBG) {
		this.selectedBG = selectedBG;
	}

	/**
	 * 获取悬停单元格字体色
	 */
	public Color getHoverFG() {
		return hoverFG;
	}

	/**
	 * 设置悬停单元格字体色
	 */
	public void setHoverFG(Color hoverFG) {
		this.hoverFG = hoverFG;
	}

	/**
	 * 获取悬停单元格背景色
	 */
	public Color getHoverBG() {
		return hoverBG;
	}

	/**
	 * 设置悬停单元格背景色
	 */
	public void setHoverBG(Color hoverBG) {
		this.hoverBG = hoverBG;
	}

	/**
	 * 是否在悬停或选择时绘制背景
	 */
	public boolean isBackgroundPainted() {
		return isBackgroundPainted;
	}

	/** 设置是否在悬停或选择时绘制背景 */
	public void setBackgroundPainted(boolean backgroundPainted) {
		isBackgroundPainted = backgroundPainted;
	}
}

package com.component.basic.layout;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ColumnsLayout implements LayoutManager2 {
	/** 约束数据 */
	private HashMap<Component, Integer> cons = new HashMap<>();
	/** 每行的组件数 */
	private ArrayList<Integer> rowNum = new ArrayList<>();
	/** 每行最大高度 */
	private ArrayList<Integer> heights = new ArrayList<>();
	/** 垂直间隙 */
	private int vgap;
	/** 水平间隙 */
	private int hgap;
	/** 每行分栏数，默认24 */
	private int columnNum = 24;
	/** 布局宽度，不包含面板的边距 */
	private int width;

	public ColumnsLayout(int width) {
		this(width, 0, 0);
	}

	public ColumnsLayout(int width, int vgap, int hgap) {
		this.width = width;
		this.vgap = vgap;
		this.hgap = hgap;
	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		synchronized (comp.getTreeLock()) {
			if ((constraints == null) || (constraints instanceof Integer)) {
				addLayoutComponent((Integer) constraints, comp);
			} else {
				throw new IllegalArgumentException("不能添加到布局中: 约束必须为整数或 null");
			}
		}
	}

	private void addLayoutComponent(Integer w, Component comp) {
		synchronized (comp.getTreeLock()) {
			if (w == null) {
				w = 24;
			}
			if (w < 1 || w > 24)
				throw new IllegalArgumentException("不能添加到布局中: 宽度必须在[1,24]范围");
			cons.put(comp, w);
		}
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container target) {

	}

	/**
	 * @deprecated 不要使用这个
	 */
	@Override
	@Deprecated
	public void addLayoutComponent(String name, Component comp) {
		throw new IllegalArgumentException("请使用addLayoutComponent(Integer w, Component comp)方法");
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		cons.remove(comp);
	}

	@Override
	public Dimension preferredLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
			rowNum.clear();
			heights.clear();

			Dimension dim = new Dimension(0, 0);
			Component[] components = target.getComponents();

			int row = 0;
			int rowHeight = 0;
			int num = 0;

			for (Component c : components) {
				Integer w = cons.get(c);
				Dimension cSize = c.getPreferredSize();
				// 高度不进行缩放
				// int cHeight = w * cSize.height / cSize.width;
				if (row + w > columnNum) { //换行
					row = w;
					dim.height += rowHeight + vgap;
					// 记录该行组件数
					rowNum.add(num);
					num = 1;
					// 记录这一行的高度
					heights.add(rowHeight);
					rowHeight = cSize.height;
				} else {
					num++;
					row += w;
					rowHeight = Math.max(rowHeight, cSize.height);
				}
			}
			if (components.length != 0) {
				dim.height += rowHeight;
				heights.add(rowHeight);
				rowNum.add(num);
			}

			Insets insets = target.getInsets();
			dim.width = width + insets.left + insets.right;
			dim.height += insets.top + insets.bottom;

			return dim;
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return null;
	}

	@Override
	public void layoutContainer(Container target) {
		synchronized (target.getTreeLock()) {
			Component[] components = target.getComponents();
			Insets insets = target.getInsets();
			float x = 0;
			int y = insets.top;
			int num = 0; //行数
			// 计算当前行所有组件宽度之和与栏目数的比，不能使用整除，因为栏目数越多精确度越小，会出现像10,13,1在同一行的情况
			float col = (width - (rowNum.get(num) - 1) * hgap) * 1f / columnNum;
			// Integer colHeight = heightsIter.next();
			for (Component c : components) {
				Integer n = cons.get(c);
				Dimension cSize = c.getPreferredSize();
				if (x + n * col > width) { //换行
					y += heights.get(num) + vgap;
					num++;
					col = (width - (rowNum.get(num) - 1) * hgap) * 1f / columnNum;
					// 左上角对齐
					c.setBounds(insets.left, y, (int) (col * n), cSize.height);
					x = n * col + hgap;
				} else {
					c.setBounds((int) (x + insets.left), y, (int) (col * n), cSize.height);
					x += n * col + hgap;
				}
			}
		}
	}

	public int getVgap() {
		return vgap;
	}

	public void setVgap(int vgap) {
		this.vgap = vgap;
	}

	public int getHgap() {
		return hgap;
	}

	public void setHgap(int hgap) {
		this.hgap = hgap;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}

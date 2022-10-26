package com.component.basic.layout;

import java.awt.*;
import java.util.Arrays;

/**
 * 用于商品详情页图片的布局管理器
 * <p>
 * 该布局管理器是专门用于商品详情页的图片展示，图片宽度为 800,400,200，面板大小应该为 800 + 5*3 = 815
 * <p>
 * 最好安装图片尺寸由大到小的顺序添加进面板，这样更美观
 */
public class ImageDisplayLayout implements LayoutManager2 {
	// 这三个size都是宽度的枚举值
	public static final int SIZE01 = 200;
	public static final int SIZE02 = 400;
	public static final int SIZE03 = 800;
	/**
	 * 水平间隔
	 */
	private int hgap = 5;
	/**
	 * 垂直间隔
	 */
	private int vgap = 5;
	private int[] heights = new int[4];

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
	}

	@Override
	public Dimension maximumLayoutSize(Container parent) {
		return null;
	}

	@Override
	public float getLayoutAlignmentX(Container parent) {
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container parent) {
		return 0;
	}

	@Override
	public void invalidateLayout(Container parent) {
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public void removeLayoutComponent(Component comp) {
	}

	@Override
	public Dimension preferredLayoutSize(Container target) {
		synchronized (target.getTreeLock()) {
			Insets is = target.getInsets();
			Dimension dim = new Dimension(0, 0);
			int x = is.left;
			Arrays.fill(heights, 0);
			int currentIndex = 0;
			int colMaxHeight;
			int count = target.getComponentCount();
			for (int i = 0; i < count; i++) {
				Component c = target.getComponent(i);
				Dimension d = c.getPreferredSize();
				if (d.width >= SIZE03) {
					colMaxHeight = 0;
					if (currentIndex == 0) {
						for (int height : heights) colMaxHeight = Math.max(colMaxHeight, height);
						Arrays.fill(heights, colMaxHeight + d.height + vgap);
						dim.width = Math.max(dim.width, SIZE03);
						x = is.left;
					} else { //换行
						for (int j = 0; j < currentIndex; j++)
							colMaxHeight = Math.max(colMaxHeight, heights[colMaxHeight]);
						Arrays.fill(heights, colMaxHeight + d.height + vgap);
						currentIndex = 0;
						dim.width = Math.max(dim.width, x);
						x = is.left + hgap + SIZE03;
					}
				} else if (d.width >= SIZE02) {
					colMaxHeight = 0;
					if (currentIndex < 3) {
						for (int j = currentIndex; j < currentIndex + 2; j++)
							colMaxHeight = Math.max(colMaxHeight, heights[j]);
						for (int j = currentIndex; j < currentIndex + 2; j++)
							heights[j] = colMaxHeight + d.height + vgap;
						currentIndex += 2;
						if (currentIndex < 3) {
							x += SIZE02 + hgap;
							dim.width = Math.max(dim.width, x);
						} else { //另起一行
							dim.width = Math.max(dim.width, x + SIZE02);
							x = is.left;
						}
					} else { //换行
						for (int j = 0; j < 2; j++)
							colMaxHeight = Math.max(colMaxHeight, heights[colMaxHeight]);
						for (int j = 0; j < 2; j++) heights[j] = colMaxHeight + d.height + vgap;
						currentIndex = 2;
						dim.width = Math.max(dim.width, x);
						x = is.left + hgap + SIZE02;
					}
				} else {
					heights[currentIndex] += d.height + vgap;

					if (currentIndex < 3) {
						currentIndex++;
						x += SIZE01 + hgap;
						dim.width = Math.max(dim.width, x);
					} else { //换行
						currentIndex = 0;
						dim.width = Math.max(dim.width, x + SIZE01);
						x = is.left;
					}
				}
			}
			dim.width = Math.max(dim.width, x);
			for (int height : heights) dim.height = Math.max(dim.height, height);
			return dim;
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return null;
	}

	@Override
	public void layoutContainer(Container target) {
		Insets is = target.getInsets();
		Dimension size = target.getPreferredSize();

		Arrays.fill(heights, 0);
		int currentIndex = 0;
		int maxHeight;
		int x = is.left;
		int count = target.getComponentCount();
		for (int i = 0; i < count; i++) {
			Component c = target.getComponent(i);
			Dimension d = c.getPreferredSize();
			if (d.width >= SIZE03) {
				maxHeight = 0;
				if (currentIndex == 0) {
					for (int height : heights) maxHeight = Math.max(maxHeight, height);
					// 水平居中
					c.setBounds((size.width - SIZE03) / 2, maxHeight, SIZE03, d.height);
					Arrays.fill(heights, maxHeight + d.height + vgap);
					x = is.left;
				} else { //换行
					for (int j = 0; j < currentIndex; j++) maxHeight = Math.max(maxHeight, heights[maxHeight]);
					c.setBounds((size.width - SIZE03) / 2, maxHeight, SIZE03, d.height);
					Arrays.fill(heights, maxHeight + d.height + vgap);
					currentIndex = 0;
					x = is.left + hgap + SIZE03;
				}
			} else if (d.width >= SIZE02) {
				maxHeight = 0;
				if (currentIndex < 3) {
					for (int j = currentIndex; j < currentIndex + 2; j++)
						maxHeight = Math.max(maxHeight, heights[j]);
					c.setBounds(x, maxHeight, SIZE02, d.height);
					for (int j = currentIndex; j < currentIndex + 2; j++)
						heights[j] = maxHeight + d.height + vgap;
					currentIndex += 2;
					if (currentIndex < 3) {
						x += SIZE02 + hgap;
					} else { //另起一行
						x = is.left;
					}
				} else { //换行
					for (int j = 0; j < 2; j++)
						maxHeight = Math.max(maxHeight, heights[maxHeight]);
					c.setBounds(is.left, maxHeight, SIZE02, d.height);
					for (int j = 0; j < 2; j++) heights[j] = maxHeight + d.height + vgap;
					currentIndex = 2;
					x = is.left + hgap + SIZE02;
				}
			} else {
				c.setBounds(x, heights[currentIndex],
						SIZE01, d.height);
				heights[currentIndex] += d.height + vgap;

				if (currentIndex < 3) {
					currentIndex++;
					x += SIZE01 + hgap;
				} else { //换行
					currentIndex = 0;
					x = is.left;
				}
			}
		}
	}
}

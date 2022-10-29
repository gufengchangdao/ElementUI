package com.component.others.collapse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.max;

/**
 * 手风琴面板
 */
public class AccordionPanel extends JPanel {
	/**
	 * 每一个子面板
	 */
	public static class Item {
		String title;
		/** 可被收起的面板 */
		JPanel panel;
		JLabel titlePanel;

		public Item(String title, JPanel panel) {
			this.title = title;
			this.panel = panel;
		}

		public String getTitle() {
			return title;
		}

		public JPanel getPanel() {
			return panel;
		}

		public JLabel getTitlePanel() {
			return titlePanel;
		}

		@Override
		public String toString() {
			return "Item{" +
					"title='" + title + '\'' +
					", panel=" + panel +
					'}';
		}
	}

	/** 子面板列表 */
	private List<Item> items;
	/** 标题背景渐变色开始颜色 */
	private Color titleBeginBG;
	/** 标题背景渐变色结束颜色 */
	private Color titleEndBG;

	/**
	 * 使用默认图标色的折叠面板
	 *
	 * @see #AccordionPanel(List, List, Color, Color)
	 */
	public AccordionPanel(List<JPanel> panels, List<String> titles,
	                      Color titleBeginBG, Color titleEndBG) {
		this(panels, titles, titleBeginBG, titleEndBG, null, true);
	}

	/**
	 * @param panels       可被收起的内容面板
	 * @param titles       标题列表，和上面的面板相对应
	 * @param titleBeginBG 标题背景渐变色开始颜色
	 * @param titleEndBG   标题背景渐变色结束颜色
	 * @param iconColor    标题左侧箭头颜色，为null时使用默认颜色
	 * @param iconFill     为true时使用填充图标，为false使用常规图标
	 */
	public AccordionPanel(List<JPanel> panels, List<String> titles,
	                      Color titleBeginBG, Color titleEndBG, Color iconColor, boolean iconFill) {
		ArrayList<Item> list = new ArrayList<>(panels.size());
		Iterator<String> it = titles.iterator();
		for (JPanel p : panels)
			list.add(new Item(it.next(), p));
		this.items = list;
		this.titleBeginBG = titleBeginBG;
		this.titleEndBG = titleEndBG;
		init(iconColor, iconFill);
	}

	/**
	 * @param items        子列表面板列表
	 * @param titleBeginBG 标题背景渐变色开始颜色
	 * @param titleEndBG   标题背景渐变色结束颜色
	 * @param iconColor    标题左侧箭头颜色，为null时使用默认颜色
	 * @param iconFill     为true时使用填充图标，为false使用常规图标
	 */
	public AccordionPanel(List<Item> items, Color titleBeginBG, Color titleEndBG, Color iconColor, boolean iconFill) {
		this.items = items;
		this.titleBeginBG = titleBeginBG;
		this.titleEndBG = titleEndBG;
		init(iconColor, iconFill);
	}

	private void init(Color iconColor, boolean iconFill) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(true);
		setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

		Iterator<Item> it = items.iterator();
		for (Item p : items) {
			AccordionContentPanel c = new AccordionContentPanel(p.title, p.panel, titleBeginBG, titleEndBG,
					iconColor, iconFill);
			it.next().titlePanel = c.getLabel();
			add(c);
			add(Box.createVerticalStrut(5));
		}
		add(Box.createVerticalGlue());
	}

	/**
	 * 将该面板放入滚动面板并返回，滚动面板的宽度默认，高度取最高一个子面板高度，也就是说在只打开一个面板时可以容纳任何一个面板而不出现滚动条
	 *
	 * @return 创建好的滚动面板
	 */
	public JScrollPane getScrollPane() {
		//防止展开的单元格溢出面板
		JScrollPane scroll = new JScrollPane(this);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getVerticalScrollBar().setUnitIncrement(25);
		Dimension s = getPreferredSize();
		int maxWidth = s.width;
		int maxHeight = 0;

		for (Item i : items) {
			Dimension size = i.panel.getPreferredSize();
			maxWidth = max(maxWidth, size.width + 24); //加24是计算上子面板的边框
			maxHeight = max(maxHeight, size.height);
		}
		s.width = maxWidth;
		s.height += maxHeight;
		setPreferredSize(s);
		return scroll;
	}

	public List<Item> getItems() {
		return items;
	}

	public Color getTitleBeginBG() {
		return titleBeginBG;
	}

	public Color getTitleEndBG() {
		return titleEndBG;
	}
}


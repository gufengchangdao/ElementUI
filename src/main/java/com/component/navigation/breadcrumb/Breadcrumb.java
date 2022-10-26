package com.component.navigation.breadcrumb;

import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 面包屑
 * <p>
 * 显示当前页面的路径，快速返回之前的任意页面。
 */
public class Breadcrumb extends BaseComponent implements MouseListener {
	/** 标签列表 */
	private List<JButton> item = new ArrayList<>();
	/** 当前加粗的标签索引值 */
	private int currentIndex = -1;
	/** 分割符，同时设置了字符串分割符和图标分割符时，以字符串分割符为准 */
	private String separator;
	/** 图标分割符 */
	private Icon separatorIcon;
	private Font buttonFont = UIManager.getFont("Button.font");
	private Color buttonFG = UIManager.getColor("Button.foreground");
	private Insets margin = new Insets(0, 0, 0, 0);

	public Breadcrumb(List<String> item, String separator) {
		this.separator = separator;
		init(item);
	}

	/**
	 * @param item         标签列表
	 * @param currentIndex 当前加粗的标签索引值
	 * @param separator    分割符
	 */
	public Breadcrumb(List<String> item, int currentIndex, String separator) {
		this.currentIndex = currentIndex;
		this.separator = separator;
		init(item);
	}

	public Breadcrumb(List<String> item, Icon separatorIcon) {
		this.separatorIcon = separatorIcon;

		init(item);
	}

	/**
	 * @param item          标签列表
	 * @param currentIndex  当前加粗的标签索引值
	 * @param separatorIcon 图标分割符
	 */
	public Breadcrumb(List<String> item, int currentIndex, Icon separatorIcon) {
		this.currentIndex = currentIndex;
		this.separatorIcon = separatorIcon;

		init(item);
	}

	private void init(List<String> item) {
		for (String s : item) {
			this.item.add(createItem(s));
		}

		// 设置当前页面，字体加粗
		if (currentIndex != -1) {
			JButton b = this.item.get(currentIndex);
			b.setFont(buttonFont.deriveFont(Font.BOLD));
		}

		// 布局
		// setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(new FlowLayout());
		for (int i = 0, len = item.size(); i < len; i++) {
			add(this.item.get(i));
			if (i != len - 1) {
				if (separator != null) add(new JLabel(separator));
				else if (separatorIcon != null) {
					add(new JLabel(separatorIcon));
				}
			}
		}
	}

	private JButton createItem(String s) {
		JButton b = new JButton(s);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setBackground(null);
		b.setMargin(margin);
		b.addMouseListener(this);
		return b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setForeground(ColorUtil.PRIMARY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setForeground(buttonFG);
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		JButton b = item.get(this.currentIndex);
		if (b != null) b.setFont(buttonFont);
		this.currentIndex = currentIndex;
		item.get(currentIndex).setFont(buttonFont.deriveFont(Font.BOLD));
	}

	public void addItem(String s) {
		int count = getComponentCount();
		addItemAt(s, min(count / 2 + 1, count));
	}

	public void addItemAt(String s, int index) {
		JButton b = createItem(s);
		if (getComponentCount() != 0) {
			if (separator != null) add(new JLabel(separator), max(index * 2 - 1, 0));
			else add(new JLabel(separatorIcon), max(index * 2 - 1, 0));
			add(b, index * 2);
		} else {
			add(b, index); //index不为0会抛异常
		}
		item.add(index, b);
	}

	public void removeItem(String s) {
		Component[] c = getComponents();
		for (int i = 0; i < c.length; i++) {
			if (c[i] instanceof JButton) {
				JButton b = (JButton) c[i];
				if (b.getText().equals(s)) {
					remove(i);
					if (i != 0) //移除图标
						remove(i - 1);
					if (i / 2 == currentIndex) { //移动索引
						if (currentIndex != 0) setCurrentIndex(currentIndex - 1);
						else currentIndex = -1;
					}
					item.remove(i / 2);
					break;
				}
			}
		}
	}

	public void removeItemAt(int index) {
		Component[] c = getComponents();
		int i = index * 2;
		if (c.length < i)
			throw new ArrayIndexOutOfBoundsException("数据越界，共" + c.length / 2 + "个标签，可 index 为" + index);
		remove(i);
		if (index == 0 && c.length != 1) //移除分割符
			remove(0);
		else if (c.length != 1)
			remove(i - 1);
		if (index == currentIndex) {
			if (currentIndex != 0) setCurrentIndex(currentIndex - 1);
			else currentIndex = -1;
		}
		item.remove(index);
	}

	public void removeLastItem() {
		removeItemAt(getComponentCount() / 2);
	}

	public List<JButton> getItem() {
		return item;
	}

	public String getSeparator() {
		return separator;
	}

	public Icon getSeparatorIcon() {
		return separatorIcon;
	}

}

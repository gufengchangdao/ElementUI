package com.component.data.tag;

import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.common.component.RoundComponent;
import com.component.radiance.common.api.icon.RadianceIcon;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;


/**
 * Tag标签
 */
public class ETag extends RoundComponent {
	private JLabel label;
	/** 字体色和图标色 */
	private Color fg;
	/** 背景色 */
	private Color bg;
	/** 图标 */
	private IconButton button;

	/**
	 * @param text           标签文本
	 * @param fg             字体和图标色
	 * @param bg             背景色
	 * @param button         按钮图标。为 null 时表示没有图标
	 * @param buttonPosition 按钮图标的位置，值范围为BorderLayout的约束常量。为null时默认图标在右侧
	 */
	public ETag(String text, Color fg, Color bg,
	            IconButton button, String buttonPosition) {

		super(7);
		label = new JLabel(text);
		this.fg = fg;
		this.bg = bg;
		this.button = button;
		init(buttonPosition);
	}

	private void init(String buttonPosition) {
		// setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); //这个不会计算组件的insets
		setLayout(new BorderLayout());
		label.setForeground(fg);
		if (buttonPosition != null)
			switch (buttonPosition) {
				case NORTH: {
					add(label, SOUTH);
					break;
				}
				case SOUTH: {
					add(label, NORTH);
					break;
				}
				case EAST: {
					add(label, WEST);
					break;
				}
				case WEST: {
					add(label, EAST);
					break;
				}
			}
		else add(label, EAST);

		// 设置图标
		if (button != null) {
			if (NORTH.equals(buttonPosition) || SOUTH.equals(buttonPosition))
				add(Box.createVerticalStrut(5));
			else
				add(Box.createHorizontalStrut(5));
			add(button, buttonPosition);
		}
		setInsets(new Insets(3, 7, 3, 7)); //父类方法

		// 去除圆角边框
		// getRoundBorder().setColor(bg);
		setPaintedBorder(false);
		// setOpaque(true); //设置了会导致按钮背景绘制异常
		// label.getFont()
	}

	@Override
	public Font getFont() {
		return label.getFont();
	}

	/**
	 * 设置label字体的同时设置tag大小，并且按钮大小也会设置为字体大小
	 */
	@Override
	public void setFont(Font font) {
		if (button != null) {
			Dimension size = button.getPreferredSize();
			int rate = font.getSize() / label.getFont().getSize();
			size.width *= rate;
			size.height *= rate;
			button.setPreferredSize(size);
		}

		label.setFont(font);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(bg);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public String getText() {
		return label.getText();
	}

	public void setText(String text) {
		label.setText(text);
	}

	public JLabel getLabel() {
		return label;
	}

	public Color getFg() {
		return fg;
	}

	public Color getBg() {
		return bg;
	}

	public IconButton getButton() {
		return button;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setFg(Color fg) {
		this.fg = fg;
		label.setForeground(fg);
		if (button != null) {
			RadianceIcon.ColorFilter filter1 = color -> fg;
			button.getBeginIcon().setColorFilter(filter1);
			button.getEndIcon().setColorFilter(filter1);
			RadianceIcon.ColorFilter filter2 = color -> ColorUtil.blend(fg, Color.WHITE, 0.3f);
			button.setFilter(filter2);
		}
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}
}

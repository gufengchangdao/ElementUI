package com.component.common.component;

import com.component.basic.border.AngleBorder;
import com.component.common.SwingPosition;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * 带有切角的组件
 */
public class AngleComponent extends BaseComponent {
	private JComponent c;

	/**
	 * 用组件背景色作为三角形背景色
	 */
	public AngleComponent(JComponent c, SwingPosition position) {
		this.c = c;

		setLayout(null);
		add(c);
		AngleBorder border = new AngleBorder(position, c.getBackground());
		adjustBoundsAndSize(border, null);
		setBorder(border);
	}

	public AngleComponent(JComponent c, SwingPosition position, Color color) {
		this.c = c;

		setLayout(null);
		add(c);
		AngleBorder border = new AngleBorder(position, color);
		adjustBoundsAndSize(border, null);
		setBorder(border);
	}

	public AngleComponent(JComponent c, SwingPosition position, Color color, int size, int angle) {
		this(c, position, color, size, angle, new Point(0, 0));
	}

	public AngleComponent(JComponent c, SwingPosition position, Color color, int size, int angle, Point offset) {
		this.c = c;

		setLayout(null);
		add(c);
		AngleBorder border = new AngleBorder(position, color, size, angle, offset);
		adjustBoundsAndSize(border, null);
		setBorder(border);
	}

	/**
	 * 重新设置边框，边框为 AngleBorder 时自动更新内部组件的位置、组件大小
	 */
	@Override
	public void setBorder(Border border) {
		super.setBorder(border);
		if (border instanceof AngleBorder) {
			adjustBoundsAndSize(border, null);
		}
	}

	/**
	 * 设置子组件大小，调用方可以不用考虑组件的边框大小
	 */
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		adjustBoundsAndSize(null, preferredSize);
	}

	/**
	 * 根据边框和新大小改变子组件位置、组件大小
	 *
	 * @param border 组件应用的边框，如果边框不变可填 null
	 * @param s      组件新大小，如果大小不变可填 null
	 */
	private void adjustBoundsAndSize(Border border, Dimension s) {
		if (border == null) border = getBorder();
		Insets insets = border.getBorderInsets(this);
		if (s == null) s = c.getPreferredSize();
		c.setBounds(insets.left, insets.top, s.width, s.height);
		s.width += insets.left + insets.right;
		s.height += insets.top + insets.bottom;
		super.setPreferredSize(s);
	}

	public JComponent getC() {
		return c;
	}
}

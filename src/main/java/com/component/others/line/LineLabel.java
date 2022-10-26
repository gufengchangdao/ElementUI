package com.component.others.line;

import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseComponent;
import com.component.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * 一条直线
 */
public class LineLabel extends BaseComponent {
	public static final int HORIZONTAL = SwingConstants.HORIZONTAL;
	public static final int VERTICAL = SwingConstants.VERTICAL;

	/** 线宽，默认为 2 */
	private int stroke = 2;
	/** 线条颜色，默认为浅灰色 */
	public static final Color DEFAULT_COLOR = ColorUtil.PLACEHOLDER_TEXT;
	/** 直线方向，有HORIZONTAL和VERTICAL两种方式 */
	private int direction = HORIZONTAL;
	/** 是否末端为圆角，默认为 true */
	private boolean isRound = true;

	public LineLabel(int stroke) {
		this.stroke = stroke;
		setForeground(DEFAULT_COLOR);
	}

	public LineLabel(int stroke, boolean isRound) {
		this.stroke = stroke;
		this.isRound = isRound;
		setForeground(DEFAULT_COLOR);
	}

	/**
	 * @param stroke    线条宽度
	 * @param direction 线条方向，可选值有：
	 *                  <code>HORIZONTAL</code>
	 *                  <code>VERTICAL</code>
	 */
	public LineLabel(int stroke, int direction) {
		this.stroke = stroke;
		this.direction = direction;
		setForeground(DEFAULT_COLOR);
	}

	public LineLabel(int stroke, int direction, boolean isRound) {
		this.stroke = stroke;
		this.direction = direction;
		this.isRound = isRound;
		setForeground(DEFAULT_COLOR);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Object[] oldRender = UIUtil.setRenderingHints(g2);

		int w = getWidth();
		int h = getHeight();
		float len = (stroke + 1) / 2f;
		if (isRound)
			g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		else
			g2.setStroke(new BasicStroke(stroke));
		if (direction == VERTICAL) {
			g2.draw(new Line2D.Float((w - stroke) / 2f, len, (w - stroke) / 2f, h - len));
		} else {
			g2.draw(new Line2D.Float(len, (h - stroke) / 2f, w - len, (h - stroke) / 2f));
		}

		UIUtil.resetRenderingHints(g2, oldRender);
	}
}

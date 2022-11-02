package com.component.data.label.badge;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import static com.component.util.UIUtil.resetRenderingHints;
import static com.component.util.UIUtil.setRenderingHints;

/**
 * 标记
 */
public class BadgeLabel extends JLabel {
	public BadgeLabel(String text, Color bg, Color fg) {
		super(text, CENTER);
		setBackground(bg);
		setForeground(fg);
		setOpaque(true);

		if (text == null)
			setPreferredSize(new Dimension(10, 10)); //算上重写后的getPreferredSize()的结果
		else {
			Dimension s = getPreferredSize();
			s.width += 10;
			s.height += 6;
			setPreferredSize(s);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getText() == null) { //绘制一个圆点
			Graphics2D g2 = (Graphics2D) g;
			Object[] oldRender = setRenderingHints(g2);
			g2.setColor(getBackground());
			g2.fillOval(0, 0, getWidth(), getHeight());
			resetRenderingHints(g, oldRender);
		} else {
			Graphics2D g2 = (Graphics2D) g;
			g2.setClip(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2,
					getHeight() - 2, getHeight() - 2));
			super.paintComponent(g2);
			g2.setColor(getBackground());
			g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			Object[] oldRender = setRenderingHints(g2);
			g2.setClip(0, 0, getWidth(), getHeight());
			g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2,
					getHeight() - 2, getHeight() - 2);
			resetRenderingHints(g2, oldRender);
		}
	}
}

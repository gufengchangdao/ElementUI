package com.component.data.label;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 带有删除线的Label
 */
public class LineThroughLabel extends JLabel {
	private Color lineColor;

	public LineThroughLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		init();
	}

	public LineThroughLabel(String text) {
		super(text);
		init();
	}

	public LineThroughLabel() {
		init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = getFont().getStringBounds(getText(), context);
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		g2.setColor(lineColor);
		g2.drawLine(0, h / 2 + 1, w, h / 2 + 1);
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
		repaint();
	}

	private void init() {
		lineColor = getForeground();
	}
}

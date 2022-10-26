package com.component.data.label;

import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;

/**
 * 带有描边的标签类
 * <p>
 * 虽然继承JLabel就可以了，但为了省事，这里直接继承JLabel
 */
public class StrokeLabel extends JLabel {
	private String text;
	private int strokeWidth = 1;
	private Color strokeColor = Color.RED;
	private Shape shape;

	public StrokeLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
	}

	public StrokeLabel(String text) {
		super(text);
	}

	/**
	 * 创建描边字体
	 *
	 * @param text        文本
	 * @param font        文本字体
	 * @param fontColor   字体色
	 * @param strokeWidth 描边宽度
	 * @param strokeColor 描边色
	 */
	public StrokeLabel(String text, Font font, Color fontColor, int strokeWidth, Color strokeColor) {
		super(text);
		this.text = text;
		this.strokeWidth = strokeWidth;
		this.strokeColor = strokeColor;
		setFont(font);
		setForeground(fontColor);
	}

	public StrokeLabel() {
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (shape == null) {
			Font f = getFont();
			GlyphVector v = f.createGlyphVector(getFontMetrics(f).getFontRenderContext(), text);
			shape = v.getOutline();
		}
		Rectangle bounds = shape.getBounds();
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(
				(getWidth() - bounds.width) / 2 - bounds.x,
				(getHeight() - bounds.height) / 2 - bounds.y
		);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 文字
		g2.setColor(Color.WHITE);
		g2.fill(shape);
		// 描边
		g2.setColor(strokeColor);
		g2.setStroke(new BasicStroke(strokeWidth));
		g2.draw(shape);
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
		repaint();
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
		repaint();
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
		repaint();
	}
}
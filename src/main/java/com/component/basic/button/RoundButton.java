package com.component.basic.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 圆角按钮
 */
public class RoundButton extends JButton implements MouseListener {

	private Color borderColor;
	/** 背景色 */
	private Color bGColor;
	/** 鼠标悬停时背景色 */
	private Color brighterBGColor;
	/** 点击时背景 */
	private Color darkerBGColor;
	private Color currentBGColor;
	/** 圆角，-1时表示为两边为半圆 */
	private int radius;
	private boolean isEnable = true;

	public RoundButton(String text, Icon icon, Color bGColor, int radius) {
		this(text, icon, bGColor, null, radius, null);
	}

	/**
	 * 圆角按钮
	 *
	 * @param text        文本
	 * @param icon        图标
	 * @param bGColor     背景色
	 * @param borderColor 边框色
	 * @param radius      圆角，-1时表示为两边为半圆
	 * @param listener    鼠标移动的动画，使用默认就填null
	 */
	public RoundButton(String text, Icon icon, Color bGColor, Color borderColor, int radius, MouseListener listener) {
		super(text, icon);
		this.currentBGColor = bGColor;
		brighterBGColor = new Color(Math.min(currentBGColor.getRed() + 8, 255), Math.min(currentBGColor.getGreen() + 8, 255), Math.min(currentBGColor.getBlue() + 8, 255));
		darkerBGColor = new Color(Math.max(currentBGColor.getRed() - 8, 0), Math.max(currentBGColor.getGreen() - 8, 0), Math.max(currentBGColor.getBlue() - 8, 0));
		this.bGColor = bGColor;
		this.radius = radius;
		super.setContentAreaFilled(false);

		setBorderPainted(false);
		if (listener != null) addMouseListener(listener);
		else addMouseListener(this);
		this.borderColor = borderColor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(currentBGColor);

		if (radius != -1) {
			g2.fillRoundRect(1, 1, super.getSize().width - 2, super.getSize().height - 2, radius, radius);
		} else {
			// 90圆角
			int r = super.getSize().height - 2;

			g2.fillArc(1, 1, r, r, 90, 180);
			g2.fillArc(super.getSize().width - 2 - r, 1, r + 1, r, -90, 180);
			g2.fillRect((r + 1) / 2, 1, super.getSize().width - 1 - r, r);
		}


		// 绘制边框
		if (borderColor != null) {
			g2.setColor(borderColor);
			g2.drawRoundRect(1, 1, super.getSize().width - 2, super.getSize().height - 2, radius, radius);
		}
		g2.dispose();
		super.paintComponent(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!isEnable) return;
		currentBGColor = darkerBGColor;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!isEnable) return;
		currentBGColor = brighterBGColor;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isEnable) return;
		currentBGColor = brighterBGColor;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isEnable) return;
		currentBGColor = bGColor;
		repaint();
	}

	public Color getbGColor() {
		return bGColor;
	}

	public Color getBrighterBGColor() {
		return brighterBGColor;
	}

	public Color getDarkerBGColor() {
		return darkerBGColor;
	}

	public Color getCurrentBGColor() {
		return currentBGColor;
	}

	public int getRadius() {
		return radius;
	}

	@Override
	public void setBackground(Color bg) {
		bGColor = bg;
		this.currentBGColor = bGColor;
		brighterBGColor = currentBGColor.brighter();
		darkerBGColor = currentBGColor.darker();
		repaint();
	}

	@Override
	public void setEnabled(boolean b) {
		isEnable = b;
		super.setEnabled(b);
		if (b) {
			currentBGColor = bGColor;
		} else {
			currentBGColor = brighterBGColor;
		}
		repaint();
	}

	public void setRadius(int radius) {
		this.radius = radius;
		repaint();
	}

	public void setBrighterBGColor(Color brighterBGColor) {
		this.brighterBGColor = brighterBGColor;
		repaint();
	}

	public void setDarkerBGColor(Color darkerBGColor) {
		this.darkerBGColor = darkerBGColor;
		repaint();
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		repaint();
	}
}
package com.component.basic.border;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 鼠标滑过会有波纹动画的边框
 */
public class RippleBorder extends EmptyBorder implements MouseListener {
	protected final Timer animator;
	private float count = 1f;
	private Color hoverColor;
	private Color oldFG;

	/**
	 * @param c          要添加边框动画的组件
	 * @param size       边框大小
	 * @param hoverColor 鼠标悬停时字体色，如果不改变字体色填null
	 */
	public RippleBorder(Component c, int size, Color hoverColor) {
		super(size, size, size, size);
		this.hoverColor = hoverColor;
		animator = new Timer(80, e -> {
			c.repaint();
			count += .9f;
		});
		c.addMouseListener(this);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
		if (!animator.isRunning()) {
			super.paintBorder(c, g, x, y, w, h);
			return;
		}
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Color.WHITE); // c.getBackground().brighter());
		float a = 1f / count;
		boolean shouldBeHidden = .12f - a > 1.0e-2;
		if (shouldBeHidden) {
			a = 0f;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
		Insets i = getBorderInsets();
		int xx = i.left - (int) count;
		int yy = i.top - (int) count;
		int ww = i.left + i.right - (int) (count * 2f);
		int hh = i.top + i.bottom - (int) (count * 2f);
		g2.setStroke(new BasicStroke(count * 1.2f));
		g2.drawRoundRect(xx, yy, w - ww, h - hh, 10, 10);
		if (xx < 0 && animator.isRunning()) {
			count = 1f;
			animator.stop();
		}
		g2.dispose();
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
		if (hoverColor != null) {
			oldFG = e.getComponent().getForeground();
			e.getComponent().setForeground(hoverColor);
		}
		animator.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (hoverColor != null) {
			e.getComponent().setForeground(oldFG);
		}
	}
}

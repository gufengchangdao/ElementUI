package com.component.others.carousel;

import javax.swing.*;
import java.awt.*;

/**
 * 使用CardLayout实现的走马灯面板
 * <p>
 * 使用CardLayout实现走马灯会更方便，因为CardLayout提供了previous和next方法
 */
public class CarouselCardLayoutPanel extends JPanel {
	// 左右留下空间绘制两侧面板
	private CardLayout cardLayout;

	public CarouselCardLayoutPanel() {
		this(50);
	}

	/**
	 * @param hgap 两侧面板绘制的宽度
	 */
	public CarouselCardLayoutPanel(int hgap) {
		cardLayout = new CardLayout(hgap, 5);
		setLayout(cardLayout);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// for (Component c : getComponents()) {
		// 	if (c.isVisible()) {
		// 		paintSideComponents(g, getComponentZOrder(c));
		// 		return;
		// 	}
		// }
	}

	/**
	 * 绘制当前显示面板的两侧面板
	 *
	 * @param g       绘制上下文
	 * @param current 当前显示的面板索引值
	 */
	private void paintSideComponents(Graphics g, int current) {
		Graphics2D g2 = (Graphics2D) g.create();
		// 半透明
		g2.setComposite(AlphaComposite.SrcOver.derive(.5f));
		Insets insets = getInsets();
		int hgap = cardLayout.getHgap();
		int vgap = cardLayout.getVgap();
		int cw = getWidth() - (hgap * 2 + insets.left + insets.right);
		int gap = 10;
		int nc = getComponentCount();

		// 绘制左边的面板
		g2.translate(hgap + insets.left - cw - gap, vgap + insets.top);
		Component prev = getComponent(current > 0 ? current - 1 : nc - 1);
		prev.print(g2);

		// 绘制右边的面板
		g2.translate((cw + gap) * 2, 0);
		Component next = getComponent((current + 1) % nc);
		next.print(g2);

		g2.dispose();
	}

	public void last() {
		cardLayout.previous(this);
		repaint();
	}

	public void next() {
		cardLayout.next(this);
		repaint();
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
}

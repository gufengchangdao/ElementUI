package com.component.data.progress;

import com.component.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 线性圆角进度条
 */
public class LineRoundedProgress extends JProgressBar {
	@Override
	protected void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		String text = getString();

		Graphics2D g2 = (Graphics2D) g.create();
		UIUtil.setRenderingHints(g2);

		// 背景
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, w, h, h, h);

		// 进度条
		g2.setColor(getForeground());
		g2.fillRoundRect(0, 0, (int) (getPercentComplete() * w), h, h, h);

		// 文本
		if (paintString) {
			g2.setColor(Color.WHITE);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = getFont().getStringBounds(text, context);
			g2.drawString(text, (int) ((w - bounds.getWidth()) / 2),
					(int) (h - bounds.getY()) / 2);
		}

		g2.dispose();


		// 另一种方式：使用裁剪
		// Graphics2D g2 = (Graphics2D) g;
		// // 裁剪具有锯齿，我看别人说可以设置渲染或模糊处理，我这里绘制一层新边框覆盖边缘的锯齿
		// g2.setClip(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2,
		// 		getHeight() - 2, getHeight() - 2));
		// super.paintComponent(g2);
		//
		// g2.setClip(null);
		// g2.setColor(getBackground());
		// Object[] oldRender = UIUtils.setRenderingHints(g); //形状反锯齿
		// // 绘制三层边框盖住锯齿边缘，一层不能很好的盖住。这里只改变了圆角大小是因为改变边框大小会使边框过厚
		// g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2,
		// 		getHeight() - 1, getHeight() - 1);
		// g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2,
		// 		getHeight() - 2, getHeight() - 2);
		// g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2,
		// 		getHeight() - 3, getHeight() - 3);
		// UIUtils.resetRenderingHints(g, oldRender); //应用原本反锯齿提示
	}
}

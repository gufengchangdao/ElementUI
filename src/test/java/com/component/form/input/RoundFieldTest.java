package com.component.form.input;

import com.component.basic.border.RoundBorder;
import com.component.common.component.BaseInputField;
import com.component.util.SwingTestUtil;

import java.awt.*;

/**
 * 圆角输入框演示
 */
public class RoundFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			BaseInputField field = new BaseInputField();
			field.setBorder(new RoundBorder(-1));
			field.setPlaceholder("请输入关键词");
			//消除矩形背景
			field.setBackground(null);
			field.setPreferredSize(new Dimension(400, 40));

			SwingTestUtil.test(field);
		});
	}

	// 绘制圆角边框，原理是绘制椭圆
	// @Override
	// protected void paintBorder(Graphics g) {
	// 	int h = getHeight();
	// 	int w = getWidth();
	// 	Graphics2D g2 = (Graphics2D) g.create();
	// 	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	// 	g2.drawRoundRect(1, 1, w - 2, h - 2, h, h);
	// 	g2.dispose();
	// 	super.paintBorder(g2);
	// }
}

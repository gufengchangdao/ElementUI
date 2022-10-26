package com.component.data.label;

import com.component.basic.border.RoundBorder;

import javax.swing.*;
import java.awt.*;

public class LabelFactory {
	/**
	 * 创建一个标签
	 *
	 * @param text  文本
	 * @param font  字体，为null时使用默认的
	 * @param color 字体色和边框色
	 */
	public static JLabel createLabel(String text, Font font, Color color) {
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setForeground(color);
		if (font != null)
			label.setFont(font);

		label.setBorder(new RoundBorder(color, 6));
		// 内边距设置为 4
		Dimension size = label.getPreferredSize();
		size.width += 6;
		size.height += 4;
		label.setPreferredSize(size);
		return label;
	}
}

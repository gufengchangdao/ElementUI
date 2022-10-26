package com.component.basic.button;

import com.component.basic.color.ColorUtil;
import com.component.font.FontUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * 按钮工厂类
 */
public class ButtonFactory {
	/** 默认按钮 */
	public static JButton createDefaultButton(String text, Color backgroundColor) {
		return createDefaultButton(text, FontUtil.DEFAULT_FONT, null, backgroundColor, Color.WHITE, null,
				0, 0, 6, false, null);
	}

	public static JButton createDefaultButton(String text, Color textColor, Color backgroundColor, Color borderColor) {
		return createDefaultButton(text, FontUtil.DEFAULT_FONT, null, backgroundColor, textColor, borderColor,
				0, 0, 6, false, null);
	}

	/** 朴素按钮 */
	public static JButton createPlainButton(String text, Color color) {
		Color backgroundColor = ColorUtil.changeAlpha(color, .4f);
		PlainButtonMouseListener listener = new PlainButtonMouseListener(color, backgroundColor);
		JButton b = createDefaultButton(text, FontUtil.DEFAULT_FONT, null, backgroundColor, color, color,
				0, 0, 6, false, listener);
		// 这里偷个懒,直接传入,就不靠e来获取了
		listener.setButton(b);
		return b;
	}

	public static JButton createPlainButton(String text, Color textColor, Color backgroundColor, Color borderColor) {
		PlainButtonMouseListener listener = new PlainButtonMouseListener(textColor, backgroundColor);
		JButton b = createDefaultButton(text, FontUtil.DEFAULT_FONT, null, backgroundColor, textColor, borderColor,
				0, 0, 6, false, listener);
		// 这里偷个懒,直接传入,就不靠e来获取了
		listener.setButton(b);
		return b;
	}

	/** 圆角按钮，左右为半圆 */
	public static JButton createRoundButton(String text, Color backgroundColor) {
		return createDefaultButton(text, FontUtil.DEFAULT_FONT, null, backgroundColor, Color.WHITE, null,
				0, 0, -1, false, null);
	}

	public static JButton createRoundButton(String text, Color textColor, Color backgroundColor, Color borderColor) {
		return createDefaultButton(text, FontUtil.DEFAULT_FONT, null, backgroundColor, textColor, borderColor,
				0, 0, -1, false, null);
	}

	/** 纯图标按钮 */
	public static JButton createIconRoundButton(Icon icon, Color backgroundColor, boolean isRound) {
		int size = isRound ? (int) (Math.max(icon.getIconWidth(), icon.getIconHeight()) * 1.5) : 0;
		return createDefaultButton(null, null, icon, backgroundColor, null, null,
				size, size, isRound ? -1 : 6, false, null);
	}

	/**
	 * 按钮的总构造方法，一般不直接调用
	 *
	 * @param text            文本
	 * @param font            字体
	 * @param backgroundColor 背景色
	 * @param textColor       文本色
	 * @param borderColor     边框色，为null时与背景色保持一致
	 * @param width           宽度
	 * @param height          高度
	 * @param radius          圆角大小
	 * @param isDisabled      是否禁用
	 * @return 构造好的按钮对象
	 */
	public static JButton createDefaultButton(String text, Font font,
	                                          Icon icon,
	                                          Color backgroundColor, Color textColor, Color borderColor,
	                                          int width, int height, int radius, boolean isDisabled,
	                                          MouseListener listener) {
		JButton b;
		b = new RoundButton(text, icon, backgroundColor, borderColor, radius, listener);

		if (font != null)
			b.setFont(font);

		if (textColor != null)
			b.setForeground(textColor);

		// 大小
		if (width > 0 && height > 0) {
			b.setPreferredSize(new Dimension(width, height));
			if (font == null) b.setFont(FontUtil.DEFAULT_FONT.deriveFont(height * 0.6f));
		}

		// 功能性
		b.setFocusable(false);
		b.setEnabled(!isDisabled);
		b.setMargin(new Insets(6, 15, 6, 15));
		return b;
	}

	public static JButton createIconButton(String text, Image image, Color background) {
		return createIconButton(0, 0, text, image, SwingConstants.CENTER, background);
	}

	/**
	 * 生成包含图标和文字的按钮，图标和文字水平布局
	 *
	 * @param width     宽度，使用默认就填0
	 * @param height    高度，使用默认就填0
	 * @param text      文本
	 * @param image     左侧图标，没有填null
	 * @param alignment 对齐方式，用于控制指定宽高下文本和图标的位置，建议使用SwingConstants的常量
	 */
	public static JButton createIconButton(int width, int height, String text, Image image, int alignment, Color background) {
		JButton button = image == null ? new JButton(text) : new JButton(text, new ImageIcon(image));
		if (width > 0 && height > 0)
			button.setPreferredSize(new Dimension(width, height));
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		button.setHorizontalAlignment(alignment);
		button.setBackground(background);
		button.setFocusPainted(false);
		button.setFont(FontUtil.DEFAULT_FONT.deriveFont(12f));
		return button;
	}

	/**
	 * 创建没有文本的按钮，用于轮播图下方的按钮
	 */
	public static JButton createNoTextButton(int width, int height, Color backgroundColor) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(width, height));
		button.setBorder(null);
		button.setBackground(backgroundColor);
		return button;
	}
}

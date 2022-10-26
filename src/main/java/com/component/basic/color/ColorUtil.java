package com.component.basic.color;

import java.awt.*;

public class ColorUtil {
	public static final Color PRIMARY = new Color(64, 158, 255);
	public static final Color SUCCESS = new Color(103, 194, 58);
	public static final Color WARNING = new Color(230, 162, 60);
	public static final Color DANGER = new Color(245, 108, 108);
	public static final Color INFO = new Color(144, 147, 153);

	// 中性色
	// 中性色用于文本、背景和边框颜色。通过运用不同的中性色，来表现层次结构。
	// 文字
	public static final Color PRIMARY_TEXT = new Color(48, 49, 51);
	/** FlatLaf默认背景色 */
	// public static final Color BACKGROUND_COLOR = new Color(60, 63, 65);
	public static final Color BACKGROUND = Color.WHITE;
	public static final Color COMMON_TEXT = new Color(96, 98, 102);
	public static final Color SECONDARY_TEXT = new Color(144, 147, 153);
	public static final Color PLACEHOLDER_TEXT = new Color(192, 196, 204);

	// 边框
	public static final Color BORDER_LEVEL1 = new Color(220, 223, 230);
	public static final Color BORDER_LEVEL2 = new Color(228, 231, 237);
	public static final Color BORDER_LEVEL3 = new Color(235, 238, 245);
	public static final Color BORDER_LEVEL4 = new Color(242, 246, 252);

	/**
	 * 改变演示的alpha值，要使组件不可用可以方便使用
	 *
	 * @param c 待修改颜色值
	 * @param a alpha值，范围为0~1
	 */
	public static Color changeAlpha(Color c, float a) {
		if (a > 1 || a < 0) throw new IllegalArgumentException("a的值必须在0~1之间");
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (a * 255));
	}

	/**
	 * 颜色混合
	 *
	 * @param c1    颜色1
	 * @param c2    颜色2
	 * @param ratio 颜色2所占比例
	 * @return 混合后颜色
	 */
	public static Color blend(Color c1, Color c2, float ratio) {
		if (ratio > 1f) ratio = 1f;
		else if (ratio < 0f) ratio = 0f;
		float iRatio = 1.0f - ratio;

		int i1 = c1.getRGB();
		int i2 = c2.getRGB();

		int a1 = (i1 >> 24 & 0xff);
		int r1 = ((i1 & 0xff0000) >> 16);
		int g1 = ((i1 & 0xff00) >> 8);
		int b1 = (i1 & 0xff);

		int a2 = (i2 >> 24 & 0xff);
		int r2 = ((i2 & 0xff0000) >> 16);
		int g2 = ((i2 & 0xff00) >> 8);
		int b2 = (i2 & 0xff);

		int a = (int) ((a1 * iRatio) + (a2 * ratio));
		int r = (int) ((r1 * iRatio) + (r2 * ratio));
		int g = (int) ((g1 * iRatio) + (g2 * ratio));
		int b = (int) ((b1 * iRatio) + (b2 * ratio));

		return new Color(a << 24 | r << 16 | g << 8 | b);
	}
}

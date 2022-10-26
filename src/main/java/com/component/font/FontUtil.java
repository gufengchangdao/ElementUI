package com.component.font;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class FontUtil {
	/** 辅助文字 */
	public static final int AUXILIARY_WORDS = 12;
	/** 正文 */
	public static final int NORMAL = 14;
	/** 小标题 */
	public static final int SMALL_TITLE = 16;
	/** 标题 */
	public static final int TITLE = 18;
	/** 主标题 */
	public static final int PRIMARY_TITLE = 20;

	/**
	 * 有抗锯齿和分数度量 FontRenderContext 对象，应用于自定义组件中文本绘制的定位
	 */
	public static final FontRenderContext FRC = new FontRenderContext(new AffineTransform(), true, true);
	public static final Font SMALL_FONT = new Font(Font.SERIF, Font.PLAIN, AUXILIARY_WORDS);
	public static final Font DEFAULT_FONT = new Font(Font.SERIF, Font.PLAIN, NORMAL);

}

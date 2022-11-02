package com.component.font;

import org.apache.batik.ext.swing.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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

	/**
	 * 替换全局字体名，但保留组件默认字体的类型和大小。
	 */
	public static void registerGlobalFont(Font font) {
		UIDefaults defaults = UIManager.getDefaults();
		for (Map.Entry<Object, Object> entry : defaults.entrySet()) {
			Object key = entry.getKey();
			if (key.toString().endsWith(".font")) {
				Font f = UIManager.getFont(key);
				UIManager.put(key, font.deriveFont(f.getStyle(), f.getSize()));
			}
		}
	}

	/**
	 * 引入外部字体
	 * <p>
	 * 你可以通过引入外部字体充当图标。例如
	 * <pre>
	 * Font f = getFont("/font/fontawesome-webfont.ttf").deriveFont(20f);
	 * JLabel iconLabel = new JLabel(String.valueOf('\uf0f4'));
	 * iconLabel.setFont(f);
	 * </pre>
	 *
	 * @param fontFileName 外部字体所在路径
	 * @return 引入的字体对象。新字体创建时，字号为1，样式为PLAIN。
	 */
	public static Font getFont(String fontFileName) {
		try (InputStream in = Resources.class.getResourceAsStream(fontFileName)) {
			return Font.createFont(Font.TRUETYPE_FONT, in);
		} catch (IOException | FontFormatException e) {
			throw new RuntimeException(e);
		}
	}
}

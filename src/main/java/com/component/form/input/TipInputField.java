package com.component.form.input;

import com.component.basic.color.ColorUtil;
import com.component.font.FontUtil;
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 含有占位文本的输入框，该类为占位文本提供基本服务，其他输入框可在该类基础上开发（需要调用paintComponent()方法）。
 * 不过 {@link JXTextField} 也有这功能。但那个不是自定义的，不好扩展
 */
public class TipInputField extends JTextField {
	private String placeholder;
	private Color placeholderColor = ColorUtil.PLACEHOLDER_TEXT;

	public TipInputField() {
	}

	public TipInputField(String text) {
		super(text);
	}

	public TipInputField(int columns) {
		super(columns);
	}

	public TipInputField(int columns, String placeholder) {
		super(columns);
		this.placeholder = placeholder;
	}

	public TipInputField(String text, int columns) {
		super(text, columns);
	}

	public TipInputField(Document doc, String text, int columns) {
		super(doc, text, columns);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		Dimension s = getPreferredSize();
		s.width = (int) getFont().getStringBounds(placeholder, FontUtil.FRC).getWidth() + 20;
		setPreferredSize(s);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 绘制文字
		Graphics2D g2 = (Graphics2D) g;
		if (placeholder != null && "".equals(getText())) {
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setColor(placeholderColor);
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = getFont().getStringBounds(placeholder, context);
			g2.drawString(placeholder, getInsets().left, (int) (getPreferredSize().height + bounds.getHeight() / 2) / 2);
		}
	}
}

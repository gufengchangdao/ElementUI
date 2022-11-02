package com.component.common.component;

import com.component.basic.color.ColorUtil;
import com.component.font.FontUtil;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 基础输入框
 * <p>
 * 功能有
 * <ul>
 *     <li>支持设置占位符</li>
 *     <li>支持添加左侧图标和右侧图标</li>
 *     <li>圆角</li>
 * </ul>
 */
public class BaseInputField extends JTextField {
	private String placeholder;
	private Color placeholderColor = ColorUtil.PLACEHOLDER_TEXT;
	private JLabel leftIcon;
	private JLabel rightIcon;
	/** 圆角大小，为 -1 时圆角最大 */
	private Integer arc;

	public BaseInputField() {
	}

	public BaseInputField(String text) {
		super(text);
	}

	public BaseInputField(int columns) {
		super(columns);
	}

	public BaseInputField(int columns, String placeholder) {
		super(columns);
		this.placeholder = placeholder;
	}

	public BaseInputField(String text, int columns) {
		super(text, columns);
	}

	public BaseInputField(Document doc, String text, int columns) {
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
		// 绘制圆角
		// 只有非不透明的，父类才不会覆盖子类绘制的边框
		if (arc != null && !isOpaque()) {
			int w = getWidth() - 1;
			int h = getHeight() - 1;
			int a = arc < 0 ? h : arc;
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(UIManager.getColor("TextField.background"));
			g2.fillRoundRect(0, 0, w, h, a, a);
			g2.setPaint(ColorUtil.PRIMARY);
			g2.drawRoundRect(0, 0, w, h, a, a);
			g2.dispose();
		}

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

	public JLabel getLeftIcon() {
		return leftIcon;
	}

	public void setLeftIcon(Icon leftIcon) {
		if (this.leftIcon != null) remove(this.leftIcon);
		Insets m = getMargin();
		int w = leftIcon.getIconWidth();
		int h = leftIcon.getIconHeight();
		if (this.leftIcon == null) {
			setMargin(new Insets(m.top, m.left + w, m.bottom, m.right));
		}
		this.leftIcon = new JLabel(leftIcon);
		add(this.leftIcon);
		Dimension s = getPreferredSize();
		this.leftIcon.setBounds(m.left, (s.height - h) / 2, w, h);
	}

	public JLabel getRightIcon() {
		return rightIcon;
	}

	public void setRightIcon(Icon rightIcon) {
		if (this.rightIcon != null) remove(this.rightIcon);
		Insets m = getMargin();
		int w = rightIcon.getIconWidth();
		int h = rightIcon.getIconHeight();
		if (this.rightIcon == null) {
			setMargin(new Insets(m.top, m.left, m.bottom, m.right + w));
		}
		this.rightIcon = new JLabel(rightIcon);
		add(this.rightIcon);
		Dimension s = getPreferredSize();
		this.rightIcon.setBounds(s.width - m.right - w, (s.height - h) / 2, w, h);
	}

	public Integer getArc() {
		return arc;
	}

	/**
	 * 设置圆角，为 -1 时圆角最大，该操作也会为组件添加空的边框，并且设置组件为非不透明
	 *
	 * @param arc 圆角角度
	 */
	public void setArc(Integer arc) {
		this.arc = arc;
		setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
		setOpaque(false);
		repaint();
	}
}

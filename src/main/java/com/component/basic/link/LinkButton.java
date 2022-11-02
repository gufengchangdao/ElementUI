package com.component.basic.link;

import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.util.BrowserLauncherUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 文字链接
 * <p>
 * 文字超链接
 */
public class LinkButton extends JButton implements MouseListener {
	private boolean isEnter;
	/** 是否绘制下划线 */
	private boolean underlinePainted;
	/** 点击后访问的url，默认为null，即不做任何事 */
	private String url;
	/** 字体色 */
	private Color fg;
	/** 鼠标悬停时字体色，默认为字体色的不透明度更低的颜色 */
	private Color hoverFGColor;
	/** 图标 */
	private RadianceIcon icon;
	/** 鼠标悬停时图标色，与字体色保持一致 */
	private RadianceIcon.ColorFilter colorFilter;

	public LinkButton(String text, Color fg) {
		this(text, null, fg, null);
	}

	public LinkButton(String text, Color fg, Color hoverFGColor) {
		this(text, null, fg, hoverFGColor);
	}

	public LinkButton(String text, RadianceIcon icon, Color fg, Color hoverFGColor) {
		this(text, null, icon, fg, hoverFGColor, true);
	}

	/**
	 * @param text             文本
	 * @param icon             图标，要求是RadianceIcon类型，是为了鼠标悬停时可以改变图标颜色
	 * @param fg               字体色，可以为null
	 * @param hoverFGColor     鼠标悬停时字体色，填null时为字体色的不透明度更低的颜色
	 * @param underlinePainted 是否绘制下划线
	 */
	public LinkButton(String text, String url, RadianceIcon icon, Color fg, Color hoverFGColor, boolean underlinePainted) {
		super(text, icon);
		this.url = url;
		this.icon = icon;
		this.fg = fg;
		setForeground(fg);
		if (hoverFGColor == null)
			hoverFGColor = ColorUtil.changeAlpha(fg == null ? UIManager.getColor("Button.foreground") : fg, .8f);
		this.hoverFGColor = hoverFGColor;
		this.underlinePainted = underlinePainted;
		init();
	}

	private void init() {
		addMouseListener(this);
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(null);
		setOpaque(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setMargin(new Insets(0, 0, 0, 0));
		if (icon != null) {
			colorFilter = color -> hoverFGColor;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isEnter && underlinePainted && isEnabled()) {
			Insets i = getInsets();
			g.drawLine(i.left, getHeight() - i.bottom - 2, getWidth() - i.right, getHeight() - i.bottom - 2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (url != null) BrowserLauncherUtil.openUrl(url);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setForeground(hoverFGColor);
		if (icon != null) icon.setColorFilter(colorFilter);
		isEnter = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setForeground(fg);
		if (icon != null) icon.setColorFilter(null);
		isEnter = false;
		repaint();
	}

	public boolean isEnter() {
		return isEnter;
	}

	public Color getFg() {
		return fg;
	}

	public Color getHoverFGColor() {
		return hoverFGColor;
	}

	public boolean isUnderlinePainted() {
		return underlinePainted;
	}

	@Override
	public RadianceIcon getIcon() {
		return icon;
	}

	public void setUnderlinePainted(boolean underlinePainted) {
		this.underlinePainted = underlinePainted;
	}

	public void setHoverFGColor(Color hoverFGColor) {
		this.hoverFGColor = hoverFGColor;
	}

	public void setIcon(RadianceIcon icon) {
		this.icon = icon;
	}

	public void setColorFilter(RadianceIcon.ColorFilter colorFilter) {
		this.colorFilter = colorFilter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

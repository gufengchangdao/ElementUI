package com.component.basic.link;

import com.component.util.BrowserLauncherUtil;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import java.awt.*;

/**
 * 使用JEditorPane实现的链接文本
 */
public class LinkText extends JEditorPane implements HyperlinkListener {
	private String text;
	/** 点击后访问的链接，如果为null，则不作任何事 */
	private String url;
	/** 鼠标悬停时是否改变颜色 */
	private boolean hoverChangeFG;

	public LinkText(String text, String url) {
		this(text, url, true);
	}

	/**
	 * @param text          文本内容
	 * @param url           点击后访问的链接，如果为null，则不作任何事
	 * @param hoverChangeFG 鼠标悬停时是否改变字体色
	 */
	public LinkText(String text, String url, boolean hoverChangeFG) {
		super("text/html", String.format("<html><a href='%s' color='%s'>%s</a>", url, "#3498db", text));
		this.text = text;
		this.url = url;
		this.hoverChangeFG = hoverChangeFG;
		setOpaque(false);
		// 未指定时使用组件的默认字体和前景色
		putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		setEditable(false);
		addHyperlinkListener(this);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (hoverChangeFG && e.getEventType() == HyperlinkEvent.EventType.ENTERED) {
			setElementColor(e.getSourceElement(), "red");
		} else if (hoverChangeFG && e.getEventType() == HyperlinkEvent.EventType.EXITED) {
			setElementColor(e.getSourceElement(), "#3498db");
		} else if (url != null && e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			BrowserLauncherUtil.openUrl(url);
		}

		// 调用这两个方法文本颜色才会改变，和注释掉的内容同样有效
		Color fg = getForeground();
		firePropertyChange("foreground", fg, Color.WHITE);
		firePropertyChange("foreground", fg, Color.BLACK);
		// setForeground(Color.WHITE);
		// setForeground(Color.BLACK);
	}

	private static void setElementColor(Element element, String color) {
		AttributeSet attrs = element.getAttributes();
		Object o = attrs.getAttribute(HTML.Tag.A);
		if (o instanceof MutableAttributeSet) {
			MutableAttributeSet a = (MutableAttributeSet) o;
			a.addAttribute(HTML.Attribute.COLOR, color);
		}
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String t) {
		this.text = t;
		super.setText(String.format("<html><a href='%s'>%s</a>", url, text));
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		super.setText(String.format("<html><a href='%s'>%s</a>", url, text));
	}
}

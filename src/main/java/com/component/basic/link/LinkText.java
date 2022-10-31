package com.component.basic.link;

import com.component.util.BrowserLauncherUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;

/**
 * 使用JEditorPane实现的链接文本
 */
public class LinkText extends JEditorPane implements HyperlinkListener {
	private String text;
	/** 点击后访问的链接，如果为null，则不作任何事 */
	private String url;

	/**
	 * @param text 文本内容
	 * @param url  点击后访问的链接，如果为null，则不作任何事
	 */
	public LinkText(String text, String url) {
		super("text/html", String.format("<html><a href='%s'>%s</a>", url, text));
		this.text = text;
		this.url = url;
		setOpaque(false);
		// 未指定时使用组件的默认字体和前景色
		putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		setEditable(false);
		addHyperlinkListener(this);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (url != null && e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			BrowserLauncherUtil.openUrl(url);
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

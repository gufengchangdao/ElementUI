package com.component.notice.notification;

import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.common.template.Y4CCNCCRComponent;
import com.component.data.tag.TagFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 通知
 * <p>
 * 悬浮出现在页面角落，显示全局的通知提醒消息。
 */
public class NotificationComponent extends Y4CCNCCRComponent<JLabel, IconButton, JLabel, JButton> {
	/** 是否显示关闭按钮 */
	private boolean showClose;

	/**
	 * @see #NotificationComponent(String, Icon, JLabel, boolean)
	 */
	public NotificationComponent(String title, JLabel content, boolean showClose) {
		this(title, null, content, showClose);
	}

	/**
	 * @param title     通知的标题
	 * @param icon      标题旁展示的图标
	 * @param content   通知的内容
	 * @param showClose 是否显示关闭按钮
	 */
	public NotificationComponent(String title, Icon icon, JLabel content, boolean showClose) {
		this.showClose = showClose;
		JLabel titleLabel;
		if (icon != null) titleLabel = new JLabel(title, icon, SwingConstants.LEFT);
		else titleLabel = new JLabel(title);
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 15));

		IconButton closeButton = null;
		if (showClose)
			closeButton = TagFactory.createCloseButton(ColorUtil.PLACEHOLDER_TEXT);

		setProperty(titleLabel, closeButton,
				content, null, 6, 6);
		setInsets(new Insets(14, 13, 14, 13));

		init();
	}

	public JLabel getTitle() {
		return getTopLeftC();
	}

	public IconButton getCloseButton() {
		return getTopRightC();
	}

	public JLabel getContent() {
		return getCenterC();
	}

	public boolean isShowClose() {
		return showClose;
	}
}

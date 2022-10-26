package com.component.navigation.pageheader;

import com.component.basic.color.ColorUtil;
import com.component.svg.icon.regular.ArrowLeftSvg;

import javax.swing.*;
import java.awt.*;

/**
 * 页头
 * <p>
 * 如果页面的路径比较简单，推荐使用页头组件而非面包屑组件。
 */
public class PageHeader extends JPanel {
	/** 返回按钮，可以为它设置margin实现设置左边距功能 */
	private JButton returnButton;
	/** 标题 */
	private JLabel titleLabel;

	public PageHeader(String title) {
		this.titleLabel = new JLabel(title);
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 17));

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		returnButton = new JButton("返回", ArrowLeftSvg.of(16, 16));
		returnButton.setBorderPainted(false);
		returnButton.setFocusPainted(false);
		returnButton.setBackground(null);
		returnButton.setMargin(new Insets(0, 0, 0, 0));

		JLabel separator = new JLabel("   |   ");
		separator.setForeground(ColorUtil.PLACEHOLDER_TEXT);

		add(returnButton);
		add(separator);
		add(titleLabel);
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}
}

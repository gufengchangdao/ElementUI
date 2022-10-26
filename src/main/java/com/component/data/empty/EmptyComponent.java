package com.component.data.empty;

import com.component.basic.color.ColorUtil;
import com.component.common.template.Y3Component;
import com.component.svg.empty.EmptyImageSvg;

import javax.swing.*;

/**
 * 空状态
 * <p>
 * 空状态时的占位提示。
 * <p>
 * 功能有：
 * <ul>
 *     <li>顶部图标</li>
 *     <li>中间描述文本</li>
 *     <li>底部按钮</li>
 * </ul>
 */
public class EmptyComponent extends Y3Component<JLabel, JLabel, JButton> {
	/**
	 * 三个组件都是自动居中布局
	 *
	 * @param icon        顶部图标，有默认图标
	 * @param description 中间描述文本
	 * @param button      底部按钮
	 */
	public EmptyComponent(JLabel icon, String description, JButton button) {
		JLabel centerLabel = null;

		if (icon == null)
			icon = new JLabel(EmptyImageSvg.of(108, 108), SwingConstants.CENTER);


		if (description != null) {
			centerLabel = new JLabel(description, SwingConstants.CENTER);
			centerLabel.setForeground(ColorUtil.PLACEHOLDER_TEXT);
		}

		setProperty(icon, centerLabel, button, 6);
		init();
	}

	public JLabel getIcon() {
		return getTopC();
	}

	public JLabel getDescription() {
		return getCenterC();
	}
}

package com.component.notice.alert;

import com.component.animator.popup.PopupAnimatorTask;
import com.component.animator.popup.listener.CloseMouseListener;
import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.common.template.X2Component;
import com.component.data.tag.TagFactory;
import com.component.radiance.common.api.icon.RadianceIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * 警告组件
 * <p>
 * 用于页面中展示重要的提示信息
 * <p>
 * 支持功能：
 * <ul>
 *     <li>左侧图标、文本、按钮、颜色、边距</li>
 *     <li>内部组件大小自适应</li>
 *     <li>点击关闭按钮移除组件，需要注意的是该操作需要提供 container</li>
 * </ul>
 */
public class AlertComponent extends X2Component<JLabel, IconButton> {
	private boolean closable = false;
	private Container container;
	private MouseListener closeListener;

	/**
	 * @param icon     左侧图标，可以为 null，不需要设置颜色过滤器，构造器中会设置为 c
	 * @param text     文本，可以使用html标签，不过获取时得手动解析
	 * @param c        主题色，字体色、背景色、图标色参考这个颜色
	 * @param closable 是否可关闭(右边是否含有关闭按钮)。需要注意的是这里只是决定是否绘制关闭按钮，并不设置按钮逻辑
	 * @param style    增量类型，默认为 {@link GrowStyle#CONSTANT}
	 * @param insets   外边距
	 */
	public AlertComponent(RadianceIcon icon,
	                      String text, Color c, boolean closable, GrowStyle style,
	                      Insets insets) {
		JLabel label;
		IconButton button = null;
		// 左侧标签
		if (icon != null) {
			icon.setColorFilter(color -> c);
			label = new JLabel(text, icon, JLabel.LEFT);
		} else {
			label = new JLabel("成功提示的文案", JLabel.LEFT);
		}
		label.setForeground(c);

		// 右侧按钮
		if (closable) {
			this.closable = closable;
			button = TagFactory.createCloseButton(ColorUtil.PLACEHOLDER_TEXT);
		}

		setProperty(label, button, style, insets);
		Color bg = ColorUtil.blend(c, Color.WHITE, .8f);
		setBorderColor(bg);
		setBackground(bg);
		setInsets(insets);

		init(20);
	}

	public JLabel getLabel() {
		return getLeftC();
	}

	public IconButton getButton() {
		return (IconButton) getRightC();
	}

	public boolean isClosable() {
		return closable;
	}

	public void registerClose(Container container, boolean closable) {
		registerClose(container, closable, null);
	}

	/**
	 * 注册关闭按钮
	 *
	 * @param container 组件所在容器，关闭按钮需要这个容器调用 remove()
	 * @param closable  是否可关闭，可关闭将注册按钮逻辑，点击按钮即会移除组件，不可关闭也会把原先按钮、监听器移除
	 * @param task      执行动画的任务对象，如果 task 不为null，则可以在关闭时播放淡出动画，否则组件直接移除
	 */
	public void registerClose(Container container, boolean closable, PopupAnimatorTask<?> task) {
		if (this.closable == closable && this.container != null) return;
		if (this.container == null && closable) { //添加删除并且之前没有设置容器
			setContainer(container, task);
			return;
		} else if (this.closable) { //可关闭变不可关闭
			removeMouseListener(closeListener);
			remove(getRightC());
			this.closable = false;
			setRightC(null);
			return;
		}
		// 不可关闭变可关闭
		this.closable = true;
		IconButton button = TagFactory.createCloseButton(ColorUtil.PLACEHOLDER_TEXT);
		setRightC(button);
		add(button);
		this.container = container;
		closeListener = new CloseMouseListener(container, this, task);
		button.addMouseListener(closeListener);
	}

	/**
	 * 设置组件所在容器，该操作为关闭按钮添加点击事件，点击后从容器中移除组件
	 *
	 * @param container 组件所在容器，关闭按钮需要这个容器调用 remove()
	 * @param task      执行动画的任务对象，如果 task 不为null，则可以在关闭时播放淡出动画，否则组件直接移除
	 */
	public void setContainer(Container container, PopupAnimatorTask<?> task) {
		if (this.container == container) return;
		this.container = container;
		if (getRightC() != null)
			getRightC().addMouseListener(new CloseMouseListener(container, this, task));
	}
}

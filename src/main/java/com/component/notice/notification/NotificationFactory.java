package com.component.notice.notification;

import com.component.animator.popup.PopupAnimatorGroup;
import com.component.animator.popup.PopupAnimatorTask;
import com.component.basic.color.ColorUtil;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NotificationFactory {
	// public static PopupFactory factory = new PopupFactory();
	public static PopupAnimatorGroup<NotificationComponent> group = new PopupAnimatorGroup<>(null);

	/**
	 * 创建一个弹窗展示通知。
	 * <p>
	 * 这个我不使用，因为弹窗无法更改位置，就无法完成动画。建议使用玻璃窗格的形式
	 * {@link #openNotification(JComponent, String, Icon, JLabel, boolean, boolean, int, Integer, int)}
	 */
	public static Popup getPopup(Component owner,
	                             String title, JLabel content,
	                             boolean showClose, boolean paintedShadow,
	                             int position, Integer y) {
		NotificationComponent c = new NotificationComponent(title, content, showClose);
		// 绘制阴影
		if (paintedShadow)
			c.setBorder(new DropShadowBorder(ColorUtil.INFO, 7, 0.2f, 12,
					true, true, true, true));

		// 方位
		Dimension size = owner.getPreferredSize();
		Dimension cs = c.getPreferredSize();
		int x;
		if (y == null) y = 10;
		switch (position) {
			case SwingConstants.NORTH_EAST://右上角
				x = size.width - cs.width - 5;
				break;
			case SwingConstants.NORTH_WEST://左上角
				x = 5;
				break;
			default:
				x = (size.width - cs.width) / 2;//正上方
				break;
		}
		Point p = owner.getLocationOnScreen();
		x += p.x;
		y += p.y;

		return new PopupFactory().getPopup(owner, c, x, y);
		// return factory.getPopup(owner, c, x, y);
	}

	/**
	 * 创建一个弹窗展示通知。
	 * <p>
	 *
	 * @param glassPane     用于确定新弹出窗口将父组件创建到哪个窗口，一般是玻璃窗格
	 * @param title         标题
	 * @param content       内容
	 * @param showClose     是否展示关闭按钮
	 * @param paintedShadow 是否绘制阴影
	 * @param position      相对于 owner 组件的位置，值为 SwingConstants 中的常量
	 * @param y             相对于 owner 组件的y坐标，这个得自己设置
	 * @return 创建好的弹窗对象
	 */
	public static NotificationComponent openNotification(JComponent glassPane,
	                                                     String title, Icon icon,
	                                                     JLabel content,
	                                                     boolean showClose, boolean paintedShadow,
	                                                     int position, Integer y,
	                                                     int duration) {
		NotificationComponent c = new NotificationComponent(title, icon, content, showClose);
		return openNotification(glassPane, c, showClose, paintedShadow, position, y, duration);
	}

	public static NotificationComponent openNotification(JComponent glassPane,
	                                                     NotificationComponent c,
	                                                     boolean showClose, boolean paintedShadow,
	                                                     int position, Integer y,
	                                                     int duration) {
		// 绘制阴影
		if (paintedShadow)
			c.setBorder(new DropShadowBorder(ColorUtil.INFO, 7, 0.2f, 12,
					true, true, true, true));

		// 方位
		Dimension size = glassPane.getRootPane().getPreferredSize();
		Dimension cs = c.getPreferredSize();
		Point from = new Point();
		Point to = new Point();

		if (y == null) from.y = to.y = 10;
		else from.y = to.y = y;
		if (position == SwingConstants.NORTH_WEST) { //左上角
			from.x = -cs.width;
			to.x = 5;
		} else {
			from.x = size.width;
			to.x = size.width - cs.width - 5; //右上角
		}
		group.setContainer(glassPane);
		PopupAnimatorTask<NotificationComponent> task = group.createTask(c, from, to).setDurationTime(duration);

		// 关闭事件
		if (showClose)
			c.getCloseButton().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					task.startFadeOutAnimator();
				}
			});

		group.startAnimator(task);

		return c;
	}

	/**
	 * 弹出一个无图标、可关闭的简单弹窗
	 */
	public static NotificationComponent openSimpleNotification(JComponent glassPane, String title, String content) {
		return openNotification(glassPane, title, null, new JLabel(content), true, true,
				SwingConstants.NORTH_EAST, 10, 4500);
	}
}

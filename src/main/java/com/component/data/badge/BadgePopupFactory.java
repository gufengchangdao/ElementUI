package com.component.data.badge;

import com.component.data.label.badge.BadgeLabel;

import java.awt.*;

/**
 * 标记形式的弹窗
 *
 * @deprecated 这个是使用弹窗实现的标记，虽然也可以实现效果，但是必须要考虑各种操作使得弹窗关闭，例如切屏操作就得手动把弹窗关闭。
 * 建议使用新的标记实现 {@link BadgePanelGroup}
 */
@Deprecated
public class BadgePopupFactory {
	/** 弹窗左边界相对于组件右边界的偏移量，向右为正 */
	private int gap = 10;

	/**
	 * 创建一个弹窗
	 *
	 * @param owner owner用于确定新弹出窗口将父组件创建到哪个窗口。
	 * @param text  标记文本
	 * @param bg    背景色
	 * @param fg    字体色
	 * @param point 标记左下角相对于 owner 的坐标。如果为 null 则位置默认在右上角
	 * @return 创建后的弹窗对象
	 */
	public LabelPopup openPopup(Component owner, String text, Color bg, Color fg, Point point) {
		BadgeLabel label = new BadgeLabel(text, bg, fg);
		label.setFont(label.getFont().deriveFont(10f));
		if (point == null) { // 右上角位置
			Point l = owner.getLocationOnScreen();
			Dimension size = owner.getPreferredSize();
			point = new Point(l.x + size.width - gap, l.y - label.getPreferredSize().height / 2);
		}

		return new LabelPopup(owner, label, point.x, point.y);
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}
}


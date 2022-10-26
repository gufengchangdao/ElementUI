package com.component.util;

import java.awt.*;

/**
 * 大小调整工具
 */
public class SizeAdjustUtil {
	/**
	 * 默认内边距为(0,0,0,0),详细见
	 *
	 * @see SizeAdjustUtil#adjustDimensionSize(Dimension, Dimension, Insets)
	 */
	public static Dimension adjustDimensionSize(Dimension container, Dimension child) {
		float f = Math.min((float) container.width / child.width, (float) container.height / child.height);
		child.width *= f;
		child.height *= f;
		return child;
	}

	/**
	 * 根据容器大小调整子组件的大小——自适应
	 *
	 * @param container 父容器大小
	 * @param child     子组件大小，这个值会被修改并且作为返回值
	 * @param insets    父容器的内边距，计算时去除父容器的内边距
	 * @return 调整好的子组件大小，就是更新过的n
	 */
	public static Dimension adjustDimensionSize(Dimension container, Dimension child, Insets insets) {
		Dimension d = new Dimension(container);
		d.width = d.width - insets.left - insets.right;
		d.height = d.height - insets.top - insets.bottom;
		return adjustDimensionSize(d, child);
	}
}

package com.component.util;

import javax.swing.*;

/**
 * Box布局使用的工具类
 */
public class BoxLayoutUtil {
	/**
	 * 组件水平居中
	 * <p>
	 * 当布局为Y轴排列并组件放大时，组件并不会居中布局，可以用此方法将组件嵌套一层，这样可实现居中效果
	 */
	public static Box horizontalCenter(JComponent c) {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalGlue());
		b.add(c);
		b.add(Box.createHorizontalGlue());
		return b;
	}

	/** 靠左 */
	public static Box left(JComponent c) {
		Box b = Box.createHorizontalBox();
		b.add(c);
		b.add(Box.createHorizontalGlue());
		return b;
	}

	/** 靠右 */
	public static Box right(JComponent c) {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalGlue());
		b.add(c);
		return b;
	}

	/** 两边对齐，可以有一个为 null */
	public static Box horizontalAlign(JComponent leftC, JComponent rightC) {
		Box b = Box.createHorizontalBox();
		if (leftC != null) {
			b.add(leftC);
			if (rightC != null) b.add(Box.createHorizontalGlue());
		}
		if (rightC != null) b.add(rightC);
		return b;
	}

	/** 组件垂直居中 */
	public static Box verticalCenter(JComponent c) {
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalGlue());
		b.add(c);
		b.add(Box.createVerticalGlue());
		return b;
	}
}

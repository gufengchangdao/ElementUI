package com.component.common.template;

import com.component.common.component.RoundComponent;

import javax.swing.*;
import java.awt.*;

/**
 * X轴方向，两组件，leftComponent、rightComponent。分布在组件两侧
 * <p>
 * 内部组件支持自适应
 * <pre>
 *     |-----------------|
 *     |  leftC  rightC  |
 *     |-----------------|
 * </pre>
 */
public class X2Component<L extends JComponent, R extends JComponent> extends RoundComponent {
	/**
	 * X轴方向增长类型。当组件大小改变时决定内部组件的排列方式
	 */
	public enum GrowStyle {
		/** 不变 */
		CONSTANT,
		/** 左侧组件拉伸适应新大小 */
		LEFT_GROW,
		/** 右侧组件拉伸适应新大小 */
		RIGHT_GROW,
	}

	private L leftC;
	private R rightC;
	/** 增长类型 */
	private GrowStyle style;
	private Component centerStrut;

	public X2Component() {
	}

	/**
	 * @param leftC  左侧组件
	 * @param rightC 右侧组件
	 * @param style  增长类型，决定当组件大小改变时内部组件如果自适应，为 null 表示 {@link GrowStyle#CONSTANT}
	 */
	public X2Component(L leftC, R rightC, GrowStyle style, Insets insets) {
		this.leftC = leftC;
		this.rightC = rightC;
		if (style == null) this.style = GrowStyle.CONSTANT;
		else this.style = style;
		setInsets(insets);
	}

	/**
	 * @see #X2Component(JComponent, JComponent, GrowStyle, Insets)
	 */
	public void setProperty(L leftC, R rightC, GrowStyle style, Insets insets) {
		this.leftC = leftC;
		this.rightC = rightC;
		if (style == null) this.style = GrowStyle.CONSTANT;
		else this.style = style;
		setInsets(insets);
	}

	/**
	 * 初始化组件
	 *
	 * @param centerWidth 初始化时左右组件之间的间距
	 */
	public void init(int centerWidth) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(leftC);
		centerStrut = Box.createHorizontalStrut(centerWidth);
		if (rightC != null) {
			add(centerStrut);
			add(rightC);
		}
	}

	public L getLeftC() {
		return leftC;
	}

	public R getRightC() {
		return rightC;
	}

	/**
	 * 重写后的方法，当改变组件大小时，内部组件宽度也会根据增长类型来自适应新大小
	 */
	@Override
	public void setPreferredSize(Dimension s) {
		super.setPreferredSize(s);
		if (s == null) return;
		// 根据类型调整内部组件宽度，组件高度不改变
		switch (style) {
			case CONSTANT: {
				int w = leftC.getPreferredSize().width + (rightC == null ? 0 : rightC.getPreferredSize().width);
				centerStrut.setPreferredSize(new Dimension(Math.max(0, s.width - w),
						centerStrut.getPreferredSize().height));
				break;
			}
			case LEFT_GROW: {
				int w = rightC == null ? 0 : rightC.getPreferredSize().width;
				leftC.setPreferredSize(new Dimension(s.width - w,
						leftC.getPreferredSize().height));
				break;
			}
			case RIGHT_GROW: {
				if (rightC == null) break;
				int w = leftC.getPreferredSize().width;
				rightC.setPreferredSize(new Dimension(s.width - w,
						rightC.getPreferredSize().height));
				break;
			}
		}
	}

	public GrowStyle getStyle() {
		return style;
	}

	public void setStyle(GrowStyle style) {
		this.style = style;
	}

	public void setLeftC(L leftC) {
		this.leftC = leftC;
	}

	public void setRightC(R rightC) {
		this.rightC = rightC;
	}

	public void setCenterStrut(Component centerStrut) {
		this.centerStrut = centerStrut;
	}
}

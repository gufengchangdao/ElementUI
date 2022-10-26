package com.component.common.template;

import com.component.common.component.BaseComponent;

import javax.swing.*;

/**
 * Y轴方向，两组件，topC、bottomC。上下分布，并且bottomC右对齐
 * <p>
 * 内部组件支持自适应
 * <pre>
 *     |-----------|
 *     |  topC     |
 *     |  bottomC  |
 *     |-----------|
 * </pre>
 */
public class Y2Component<T extends JComponent, B extends JComponent> extends BaseComponent {
	private T topC;
	private B bottomC;

	public Y2Component() {
	}

	public Y2Component(T topC, B bottomC) {
		this.topC = topC;
		this.bottomC = bottomC;
	}

	public void setProperty(T topC, B bottomC) {
		this.topC = topC;
		this.bottomC = bottomC;
	}

	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(topC);
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalGlue());
		b.add(bottomC);
		add(b);
	}

	public T getTopC() {
		return topC;
	}

	public void setTopC(T topC) {
		this.topC = topC;
	}

	public B getBottomC() {
		return bottomC;
	}

	public void setBottomC(B bottomC) {
		this.bottomC = bottomC;
	}
}

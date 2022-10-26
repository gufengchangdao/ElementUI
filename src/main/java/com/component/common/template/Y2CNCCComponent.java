package com.component.common.template;

import com.component.common.component.BaseComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Y轴方向，三组件，topC、bottomLeftC、bottomRightC。上下分布，并且底部的两个组件向右对齐
 * <p>
 * 内部组件支持自适应
 * <pre>
 *     |----------------------------|
 *     |  topC                      |
 *     |  bottomLeftC bottomRightC  |
 *     |----------------------------|
 * </pre>
 */
public class Y2CNCCComponent<T extends JComponent, B extends JComponent> extends BaseComponent {
	private T topC;
	private B bottomLeftC;
	private B bottomRightC;

	private int vGap = 5;

	public Y2CNCCComponent() {
	}

	public Y2CNCCComponent(T topC, B bottomLeftC, B bottomRightC, int vGap) {
		this.topC = topC;
		this.bottomLeftC = bottomLeftC;
		this.bottomRightC = bottomRightC;
		this.vGap = vGap;
	}

	public void setProperty(T topC, B bottomLeftC, B bottomRightC, int vGap) {
		this.topC = topC;
		this.bottomLeftC = bottomLeftC;
		this.bottomRightC = bottomRightC;
		this.vGap = vGap;
	}

	public void init() {
		setLayout(new BorderLayout(0, vGap));
		add(topC);

		JPanel b = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		b.setOpaque(false); //用于子类重写paintComponent时可以绘制背景
		b.add(bottomLeftC);
		b.add(bottomRightC);
		add(b, BorderLayout.SOUTH);

		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// add(topC);
		// add(Box.createVerticalStrut(vGap));
		// Box b = Box.createHorizontalBox();
		// b.add(Box.createHorizontalGlue());
		// b.add(bottomLeftC);
		// b.add(bottomRightC);
		// add(b);
	}

	public T getTopC() {
		return topC;
	}

	public void setTopC(T topC) {
		this.topC = topC;
	}

	public B getBottomLeftC() {
		return bottomLeftC;
	}

	public void setBottomLeftC(B bottomLeftC) {
		this.bottomLeftC = bottomLeftC;
	}

	public B getBottomRightC() {
		return bottomRightC;
	}

	public void setBottomRightC(B bottomRightC) {
		this.bottomRightC = bottomRightC;
	}

	public int getvGap() {
		return vGap;
	}

	public void setvGap(int vGap) {
		this.vGap = vGap;
	}
}

package com.component.common.template;

import com.component.common.component.BaseComponent;

import javax.swing.*;

import static com.component.util.BoxLayoutUtil.horizontalCenter;
import static com.component.util.BoxLayoutUtil.right;

/**
 * Y轴方向，三层，四组件
 *
 * <pre>
 *     |-----------------------|
 *     |  topLeftC  topRightC  |
 *     |       centerC         |
 *     |             bottomC   |
 *     |-----------------------|
 * </pre>
 */
public class Y4CCNCCRComponent<TL extends JComponent, TR extends JComponent, C extends JComponent, B extends JComponent>
		extends BaseComponent {
	private TL topLeftC;
	private TR topRightC;
	private C centerC;
	private B bottomC;

	private int vGap1 = 5;
	private int vGap2 = 5;

	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Box b;
		if (topLeftC != null || topRightC != null) {
			b = Box.createHorizontalBox();
			if (topLeftC == null) b.add(Box.createHorizontalGlue());
			else b.add(topLeftC);
			b.add(Box.createHorizontalGlue());
			if (topRightC == null) b.add(Box.createHorizontalGlue());
			else b.add(topRightC);
			add(b);
		}

		if (centerC != null) {
			add(Box.createVerticalStrut(vGap1));
			add(horizontalCenter(centerC));
		}

		if (bottomC != null) {
			add(Box.createVerticalStrut(vGap2));
			add(right(bottomC));
		}
	}

	public Y4CCNCCRComponent() {
	}

	public Y4CCNCCRComponent(TL topLeftC, TR topRightC, C centerC, B bottomC) {
		this.topLeftC = topLeftC;
		this.topRightC = topRightC;
		this.centerC = centerC;
		this.bottomC = bottomC;
	}

	public Y4CCNCCRComponent(TL topLeftC, TR topRightC, C centerC, B bottomC,
	                         int vGap1, int vGap2) {
		this.topLeftC = topLeftC;
		this.topRightC = topRightC;
		this.centerC = centerC;
		this.bottomC = bottomC;
		this.vGap1 = vGap1;
		this.vGap2 = vGap2;
	}

	public void setProperty(TL topLeftC, TR topRightC, C centerC, B bottomC) {
		this.topLeftC = topLeftC;
		this.topRightC = topRightC;
		this.centerC = centerC;
		this.bottomC = bottomC;
	}

	public void setProperty(TL topLeftC, TR topRightC, C centerC, B bottomC,
	                        int vGap1, int vGap2) {
		this.topLeftC = topLeftC;
		this.topRightC = topRightC;
		this.centerC = centerC;
		this.bottomC = bottomC;
		this.vGap1 = vGap1;
		this.vGap2 = vGap2;
	}

	public TL getTopLeftC() {
		return topLeftC;
	}

	public TR getTopRightC() {
		return topRightC;
	}

	public C getCenterC() {
		return centerC;
	}

	public B getBottomC() {
		return bottomC;
	}

	public int getvGap1() {
		return vGap1;
	}

	public int getvGap2() {
		return vGap2;
	}

}

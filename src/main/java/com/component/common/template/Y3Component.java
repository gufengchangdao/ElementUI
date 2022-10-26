package com.component.common.template;

import com.component.common.component.BaseComponent;

import javax.swing.*;

import static com.component.util.BoxLayoutUtil.horizontalCenter;

/**
 * Y轴方向，三组件
 * <p>
 * 支持延迟加载
 * <pre>
 * |-----------|
 * |   topC    |
 * |  centerC  |
 * |  bottomC  |
 * |-----------|
 * </pre>
 */
public class Y3Component<Q extends JComponent, W extends JComponent, R extends JComponent> extends BaseComponent {
	private Q topC;
	private W centerC;
	private R bottomC;
	/** 垂直间距 */
	private int vGap1;
	private int vGap2;

	/**
	 * 空构造器，如果对于子类组件初始化需要延后，则可以重写这个构造器
	 */
	public Y3Component() {
	}

	public Y3Component(Q topC, W centerC, R bottomC) {
		this.topC = topC;
		this.centerC = centerC;
		this.bottomC = bottomC;
	}

	/**
	 * 创建指定布局的面板
	 *
	 * @param topC    顶部标签
	 * @param centerC 中部标签
	 * @param bottomC 底部按钮
	 * @param vGap    组件间间距
	 */
	public Y3Component(Q topC, W centerC, R bottomC, int vGap) {
		this.topC = topC;
		this.centerC = centerC;
		this.bottomC = bottomC;
		this.vGap1 = this.vGap2 = vGap;
	}

	/**
	 * 创建指定布局的面板
	 *
	 * @param topC    顶部标签
	 * @param centerC 中部标签
	 * @param bottomC 底部按钮
	 * @param vGap1   第一个和第二个组件间间距
	 * @param vGap2   第二个和第三个组件间间距
	 */
	public Y3Component(Q topC, W centerC, R bottomC,
	                   int vGap1, int vGap2) {
		this.topC = topC;
		this.centerC = centerC;
		this.bottomC = bottomC;
		this.vGap1 = vGap1;
		this.vGap2 = vGap2;
	}

	/**
	 * 初始化布局。初始化操作与构造器分离，解决子类构造器中初始化操作super()只能在第一行的问题
	 */
	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Box b;
		add(horizontalCenter(topC));

		if (centerC != null) {
			add(Box.createVerticalStrut(vGap1));
			add(horizontalCenter(centerC));
		}

		if (bottomC != null) {
			add(Box.createVerticalStrut(vGap2));
			add(horizontalCenter(bottomC));
		}
	}

	public Q getTopC() {
		return topC;
	}

	public W getCenterC() {
		return centerC;
	}

	public R getBottomC() {
		return bottomC;
	}

	/**
	 * @see #setProperty(JComponent, JComponent, JComponent, int)
	 */
	public void setProperty(Q topC, W centerC, R bottomC,
	                        int vGap) {
		setProperty(topC, centerC, bottomC, vGap, vGap);
	}

	/**
	 * 在调用 {@link #init()} 之前调用。子类可调用此方法来给属性赋值
	 */
	public void setProperty(Q topC, W centerC, R bottomC,
	                        int vGap1, int vGap2) {
		this.topC = topC;
		this.centerC = centerC;
		this.bottomC = bottomC;
		this.vGap1 = vGap1;
		this.vGap2 = vGap2;
	}
}

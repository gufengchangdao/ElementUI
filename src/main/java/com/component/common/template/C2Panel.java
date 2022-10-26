package com.component.common.template;

import javax.swing.*;
import java.awt.*;

/**
 * 覆盖形式，两面板，在层次面板中，一上一下，层次分别是 1 和 0
 */
public class C2Panel<T extends JComponent, B extends JComponent> extends JLayeredPane {
	private T topC;
	private B bottomC;

	/**
	 * 空构造器，如果对于子类组件初始化需要延后，则可以重写这个构造器
	 */
	public C2Panel() {
	}

	public C2Panel(T topC, B bottomC) {
		this.topC = topC;
		this.bottomC = bottomC;
	}

	protected void init() {
		Dimension size = getPreferredSize();
		topC.setBounds(0, 0, size.width, size.height);
		bottomC.setBounds(0, 0, size.width, size.height);
		topC.setOpaque(false);
		add(bottomC, (Integer) 0);
		add(topC, (Integer) 1);
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		topC.setBounds(0, 0, preferredSize.width, preferredSize.height);
		bottomC.setBounds(0, 0, preferredSize.width, preferredSize.height);
	}

	public void setProperty(T topPanel, B bottomC) {
		this.topC = topPanel;
		this.bottomC = bottomC;
	}

	public T getTopC() {
		return topC;
	}

	public B getBottomC() {
		return bottomC;
	}

	public void setTopC(T topC) {
		if (this.topC != null) {
			remove(this.topC);
		}
		this.topC = topC;
		Dimension size = getPreferredSize();
		topC.setBounds(0, 0, size.width, size.height);
		topC.setOpaque(false);
		add(topC, (Integer) 0);
	}

	public void setBottomC(B bottomC) {
		if (this.bottomC != null) {
			remove(this.bottomC);
		}
		this.bottomC = bottomC;
		Dimension size = getPreferredSize();
		bottomC.setBounds(0, 0, size.width, size.height);
		add(bottomC, (Integer) 1);
	}
}

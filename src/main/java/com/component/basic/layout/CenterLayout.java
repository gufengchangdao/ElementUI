package com.component.basic.layout;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * 第一个子组件居中的简单布局，所以也不要添加第二个组件
 */
public class CenterLayout implements LayoutManager {
	@Override
	public void addLayoutComponent(String name, Component comp) {
		/* not needed */
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		/* not needed */
	}

	@Override
	public Dimension preferredLayoutSize(Container container) {
		return Optional.ofNullable(container.getComponent(0)).map(c -> {
			Dimension size = c.getPreferredSize();
			Insets insets = container.getInsets();
			size.width += insets.left + insets.right;
			size.height += insets.top + insets.bottom;
			return size;
		}).orElseGet(Dimension::new);
	}

	@Override
	public Dimension minimumLayoutSize(Container c) {
		return preferredLayoutSize(c);
	}

	@Override
	public void layoutContainer(Container container) {
		Component c = container.getComponent(0);
		c.setSize(c.getPreferredSize());
		if (container instanceof JComponent) {
			Dimension size = c.getSize();
			Rectangle r = SwingUtilities.calculateInnerArea((JComponent) container, null);
			int x = r.x + (r.width - size.width) / 2;
			int y = r.y + (r.height - size.height) / 2;
			c.setBounds(x, y, size.width, size.height);
		}
	}
}

package com.component.navigation.tabs.hoverclosetab;

import javax.swing.*;
import java.awt.*;

/**
 * 关闭图标
 */
public class CloseTabIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		g2.setPaint(Color.ORANGE);
		g2.drawLine(2, 3, 9, 10);
		g2.drawLine(2, 4, 8, 10);
		g2.drawLine(3, 3, 9, 9);
		g2.drawLine(9, 3, 2, 10);
		g2.drawLine(9, 4, 3, 10);
		g2.drawLine(8, 3, 2, 9);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 12;
	}

	@Override
	public int getIconHeight() {
		return 12;
	}
}

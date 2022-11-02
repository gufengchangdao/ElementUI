package com.component.others.scrollbar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * 回到顶部图标
 */
public class ScrollBackToTopIcon implements Icon {
	private final Color rolloverColor = new Color(0xAA_FF_AF_64, true);
	private final Color arrowColor = new Color(0xAA_64_64_64, true);

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.translate(x, y);
		if (c instanceof AbstractButton && ((AbstractButton) c).getModel().isRollover()) {
			g2.setPaint(rolloverColor);
		} else {
			g2.setPaint(arrowColor);
		}
		float w2 = getIconWidth() / 2f;
		float h2 = getIconHeight() / 2f;
		float tw = w2 / 3f;
		float th = h2 / 6f;
		g2.setStroke(new BasicStroke(w2 / 2f));
		Path2D p = new Path2D.Float();
		p.moveTo(w2 - tw, h2 + th);
		p.lineTo(w2, h2 - th);
		p.lineTo(w2 + tw, h2 + th);
		g2.draw(p);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 32;
	}

	@Override
	public int getIconHeight() {
		return 32;
	}
}

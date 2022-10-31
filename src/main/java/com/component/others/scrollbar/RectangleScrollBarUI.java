package com.component.others.scrollbar;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * 矩形灰色滚动条
 */
public class RectangleScrollBarUI extends BasicScrollBarUI {
	@Override
	protected JButton createDecreaseButton(int orientation) {
		return new ZeroSizeButton();
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return new ZeroSizeButton();
	}

	// @Override protected Dimension getMinimumThumbSize() {
	//   // return new Dimension(20, 20);
	//   return UIManager.getDimension("ScrollBar.minimumThumbSize");
	// }

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(trackColor);
		g2.fill(r);
		g2.dispose();
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		JScrollBar sb = (JScrollBar) c;
		if (!sb.isEnabled()) {
			return;
		}
		BoundedRangeModel m = sb.getModel();
		if (m.getMaximum() - m.getMinimum() - m.getExtent() > 0) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Color color;
			if (isDragging) {
				color = thumbDarkShadowColor;
			} else if (isThumbRollover()) {
				color = thumbLightShadowColor;
			} else {
				color = thumbColor;
			}
			g2.setPaint(color);
			g2.fillRect(r.x + 1, r.y + 1, r.width - 2, r.height - 2);
			g2.dispose();
		}
	}

	static class ZeroSizeButton extends JButton {
		private static final Dimension ZERO_SIZE = new Dimension();

		@Override
		public Dimension getPreferredSize() {
			return ZERO_SIZE;
		}
	}
}

package com.component.data.label.badge;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;

/**
 * 为文本、图标添加整数形式的标记，标记值超过1000时用1K表示
 */
public class NumberBadgeLayerUI extends LayerUI<NumberBadgeLabel> {
	private static final Point OFFSET = new Point(6, 2);
	private final Rectangle viewRect = new Rectangle();
	private final Rectangle iconRect = new Rectangle();
	private final Rectangle textRect = new Rectangle();

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		if (c instanceof JLayer) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			iconRect.setBounds(0, 0, 0, 0);
			textRect.setBounds(0, 0, 0, 0);

			NumberBadgeLabel label = (NumberBadgeLabel) ((JLayer<?>) c).getView();
			SwingUtilities.calculateInnerArea(label, viewRect);
			SwingUtilities.layoutCompoundLabel(
					label,
					label.getFontMetrics(label.getFont()),
					label.getText(),
					label.getIcon(),
					label.getVerticalAlignment(),
					label.getHorizontalAlignment(),
					label.getVerticalTextPosition(),
					label.getHorizontalTextPosition(),
					viewRect,
					iconRect,
					textRect,
					label.getIconTextGap());

			Icon badge = getBadgeIcon(label.getCounter());
			Point pt;
			if (label.getIcon() == null)
				pt = getTextBadgeLocation(label.getBadgePosition(), badge);
			else
				pt = getIconBadgeLocation(label.getBadgePosition(), badge);
			g2.translate(pt.x, pt.y);
			badge.paintIcon(label, g2, 0, 0);
			g2.dispose();
		}
	}

	protected Icon getBadgeIcon(int count) {
		return new BadgeIcon(count, Color.WHITE, new Color(0xAA_FF_16_16, true));
	}

	protected Point getIconBadgeLocation(BadgePosition pos, Icon icon) {
		int x;
		int y;
		switch (pos) {
			case NORTH_WEST:
				x = iconRect.x - OFFSET.x;
				y = iconRect.y - OFFSET.y;
				break;
			case NORTH_EAST:
				x = iconRect.x + iconRect.width - icon.getIconWidth() + OFFSET.x;
				y = iconRect.y - OFFSET.y;
				break;
			case SOUTH_WEST:
				x = iconRect.x - OFFSET.x;
				y = iconRect.y + iconRect.height - icon.getIconHeight() + OFFSET.y;
				break;
			case SOUTH_EAST:
			default:
				x = iconRect.x + iconRect.width - icon.getIconWidth() + OFFSET.x;
				y = iconRect.y + iconRect.height - icon.getIconHeight() + OFFSET.y;
				break;
		}
		return new Point(x, y);
	}

	protected Point getTextBadgeLocation(BadgePosition pos, Icon icon) {
		int x;
		int y;
		switch (pos) {
			case NORTH_WEST:
				x = textRect.x - OFFSET.x;
				y = textRect.y - OFFSET.y;
				break;
			case NORTH_EAST:
				x = textRect.x + textRect.width - icon.getIconWidth() + OFFSET.x;
				y = textRect.y - OFFSET.y;
				break;
			case SOUTH_WEST:
				x = textRect.x - OFFSET.x;
				y = textRect.y + textRect.height - icon.getIconHeight() + OFFSET.y;
				break;
			case SOUTH_EAST:
			default:
				x = textRect.x + textRect.width - icon.getIconWidth() + OFFSET.x;
				y = textRect.y + textRect.height - icon.getIconHeight() + OFFSET.y;
				break;
		}
		return new Point(x, y);
	}
}

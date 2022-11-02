package com.component.data.label.badge;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

/**
 * 为文本、图标添加整数形式的标记，标记值超过1000时用1K表示
 */
public class NumberBadgeLabel extends JLabel {
	private final BadgePosition pos;
	private final int counter;

	/**
	 * @param text    文本
	 * @param pos     标记位置
	 * @param counter 标记值
	 */
	public NumberBadgeLabel(String text, BadgePosition pos, int counter) {
		super(text);
		this.pos = pos;
		this.counter = counter;
	}

	/**
	 * @param image   图标
	 * @param pos     标记位置
	 * @param counter 标记值
	 */
	public NumberBadgeLabel(Icon image, BadgePosition pos, int counter) {
		super(image);
		this.pos = pos;
		this.counter = counter;
	}

	public BadgePosition getBadgePosition() {
		return pos;
	}

	// public void setCounter(int counter) {
	//   this.counter = counter;
	// }

	public int getCounter() {
		return counter;
	}
}


class BadgeIcon implements Icon {
	private final Color badgeBgc;
	private final Color badgeFgc;
	private final int value;

	protected BadgeIcon(int value, Color fgc, Color bgc) {
		this.value = value;
		this.badgeFgc = fgc;
		this.badgeBgc = bgc;
	}

	protected Shape getBadgeShape() {
		return new Ellipse2D.Double(0d, 0d, getIconWidth(), getIconHeight());
	}

	protected Shape getTextShape(Graphics2D g2) {
		// Java 12:
		// NumberFormat fmt = NumberFormat.getCompactNumberInstance(
		//    Locale.US, NumberFormat.Style.SHORT);
		// String txt = fmt.format(value);
		String txt = value > 999 ? "1K" : Objects.toString(value);
		AffineTransform at = txt.length() < 3 ? null : AffineTransform.getScaleInstance(.66, 1d);
		return new TextLayout(txt, g2.getFont(), g2.getFontRenderContext()).getOutline(at);
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		if (value <= 0) {
			return;
		}
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		Shape badge = getBadgeShape();
		g2.setPaint(badgeBgc);
		g2.fill(badge);
		g2.setPaint(badgeBgc.darker());
		g2.draw(badge);

		g2.setPaint(badgeFgc);
		Shape shape = getTextShape(g2);
		Rectangle b = shape.getBounds();
		double tx = getIconWidth() / 2d - b.getCenterX();
		double ty = getIconHeight() / 2d - b.getCenterY();
		AffineTransform toCenterAtf = AffineTransform.getTranslateInstance(tx, ty);
		g2.fill(toCenterAtf.createTransformedShape(shape));
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 20;
	}

	@Override
	public int getIconHeight() {
		return 20;
	}
}


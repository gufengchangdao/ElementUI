package com.component.others.line;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;
import java.util.Optional;

/**
 * 具有标题且末端淡出的分割符
 */
public class TitledSeparator extends JLabel {
	protected final String title;
	protected final Color target;
	protected final int separatorHeight;
	protected final int titlePosition;

	/**
	 * @param title         分割符附近文本
	 * @param height        线宽
	 * @param titlePosition 标题相对于分割符位置，为{@link TitledBorder} 中给定的常量
	 */
	protected TitledSeparator(String title, int height, int titlePosition) {
		this(title, null, height, titlePosition);
	}

	/**
	 * @param title         分割符附近文本
	 * @param target        分割符颜色
	 * @param height        线宽
	 * @param titlePosition 标题相对于分割符位置，为{@link TitledBorder} 中给定的常量
	 */
	protected TitledSeparator(String title, Color target, int height, int titlePosition) {
		super();
		this.title = title;
		this.target = target;
		this.separatorHeight = height;
		this.titlePosition = titlePosition;
		updateBorder();
	}

	private void updateBorder() {
		Icon icon = new TitledSeparatorIcon();
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(separatorHeight, 0, 0, 0, icon), title,
				TitledBorder.DEFAULT_JUSTIFICATION, titlePosition));
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(Short.MAX_VALUE, super.getPreferredSize().height);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		updateBorder();
	}

	private class TitledSeparatorIcon implements Icon {
		private int width = -1;
		private int height;
		private Paint painter1;
		private Paint painter2;

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			int w = c.getWidth();
			if (w != width || Objects.isNull(painter1) || Objects.isNull(painter2)) {
				width = w;
				Point2D start = new Point2D.Float();
				Point2D end = new Point2D.Float(width, 0f);
				float[] dist = {0f, 1f};
				Color bgc = getBackground();
				Color ec = Optional.ofNullable(bgc).orElse(UIManager.getColor("Panel.background"));
				Color sc = Optional.ofNullable(target).orElse(ec);
				painter1 = new LinearGradientPaint(start, end, dist, new Color[]{sc.darker(), ec});
				painter2 = new LinearGradientPaint(start, end, dist, new Color[]{sc.brighter(), ec});
			}
			int h = getIconHeight() / 2;
			Graphics2D g2 = (Graphics2D) g.create();
			// XXX: g2.translate(x, y);
			g2.setPaint(painter1);
			g2.fillRect(x, y, width, getIconHeight());
			g2.setPaint(painter2);
			g2.fillRect(x, y + h, width, getIconHeight() - h);
			g2.dispose();
		}

		@Override
		public int getIconWidth() {
			return 200; // dummy width
		}

		@Override
		public int getIconHeight() {
			return separatorHeight;
		}
	}
}

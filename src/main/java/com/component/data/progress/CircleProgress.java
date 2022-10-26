package com.component.data.progress;

import com.component.basic.color.ColorUtil;
import com.component.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * 环形进度条
 * <p>
 * 功能有
 * <ul>
 *     <li>进度条常用功能</li>
 *     <li>中间label设置</li>
 * </ul>
 * <p>
 * 注意，label和 string 无法同时显示，只有在 label 为 null时可以显示 string
 */
public class CircleProgress extends JProgressBar {
	/** 线宽 */
	private int lineWidth = 6;
	private Stroke stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	/** 中间显示的面板 */
	private JLabel label;

	public CircleProgress() {
		init();
	}

	public CircleProgress(int orient) {
		super(orient);
		init();
	}

	public CircleProgress(int min, int max) {
		super(min, max);
		init();
	}

	public CircleProgress(int orient, int min, int max) {
		super(orient, min, max);
		init();
	}

	public CircleProgress(BoundedRangeModel newModel) {
		super(newModel);
		init();
	}

	private void init() {
		setLayout(null);
		setOpaque(false);
		// 不要原有的长方形，改成正方形
		setPreferredSize(new Dimension(146, 146));
	}

	/**
	 * 取长和宽最小的一边作为新的长和宽
	 */
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		int m = Math.min(preferredSize.width, preferredSize.height);
		preferredSize.width = preferredSize.height = m;
		super.setPreferredSize(preferredSize);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		UIUtil.setRenderingHints(g2);
		int w = getWidth();
		int h = getHeight();

		// 圆饼
		g2.setColor(getBackground());
		g2.setStroke(stroke);
		int s = (int) Math.ceil(lineWidth * 1.0 / 2); //向上取整
		g2.drawOval(s, s, getWidth() - s * 2 - 1, getHeight() - s * 2 - 1);

		// 进度条
		g2.setColor(ColorUtil.PRIMARY);
		g2.drawArc(s, s, getWidth() - s * 2 - 1, getHeight() - s * 2 - 1,
				90, (int) (-360 * getPercentComplete()));

		// 文本
		if (isStringPainted() && label == null) {
			String text = getString();
			FontRenderContext context = g2.getFontRenderContext();
			Rectangle2D bounds = getFont().getStringBounds(text, context);
			g2.drawString(text, (int) ((w - bounds.getWidth()) / 2),
					(int) ((h - bounds.getY()) / 2));
		}

		paintChildren(g2);
		g2.dispose();
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
		stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		if (this.label != null) remove(label);
		this.label = label;

		add(label);
		Dimension size = getPreferredSize();
		Dimension labelSize = label.getPreferredSize();
		label.setBounds((size.width - labelSize.width) / 2, (size.height - labelSize.height) / 2,
				labelSize.width, labelSize.height);
	}
}

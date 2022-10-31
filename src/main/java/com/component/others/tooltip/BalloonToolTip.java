package com.component.others.tooltip;

import com.component.basic.color.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Optional;

/**
 * 气泡提示
 */
public class BalloonToolTip extends JToolTip {
	private transient HierarchyListener listener;

	public BalloonToolTip() {
		setForeground(ColorUtil.PRIMARY_TEXT);
	}

	@Override
	public void updateUI() {
		removeHierarchyListener(listener);
		super.updateUI();
		listener = e -> {
			Component c = e.getComponent();
			if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && c.isShowing()) {
				Optional.ofNullable(SwingUtilities.getWindowAncestor(c))
						.filter(w -> w.getType() == Window.Type.POPUP)
						.ifPresent(w -> w.setBackground(new Color(0x0, true)));
			}
		};
		addHierarchyListener(listener);
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 5));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.height = 28;
		return d;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Shape s = makeBalloonShape();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 背景
		g2.setColor(getBackground());
		g2.fill(s);
		// 边框
		g2.setColor(getForeground());
		g2.draw(s);
		g2.dispose();
		super.paintComponent(g);
	}

	private Shape makeBalloonShape() {
		Insets i = getInsets();
		float w = getWidth() - 1f;
		float h = getHeight() - 1f;
		float v = i.top * .5f;
		Path2D triangle = new Path2D.Float();
		triangle.moveTo(i.left + v + v, 0f);
		triangle.lineTo(i.left + v, v);
		triangle.lineTo(i.left + v + v + v, v);
		Area area = new Area(new RoundRectangle2D.Float(0f, v, w, h - i.bottom - v, i.top, i.top));
		area.add(new Area(triangle));
		return area;
	}
}

package com.component.notice.alert;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * 弹窗动画，用于玻璃面板
 * <p>
 * 使用示例
 * <pre>
 * p.getRootPane().setGlassPane(new LightboxGlassPane(img));
 * p.getRootPane().getGlassPane().setVisible(false);
 * JButton button = new JButton("Open");
 * button.addActionListener(e -> p.getRootPane().getGlassPane().setVisible(true));
 * </pre>
 */
public class LightboxGlassPane extends JPanel {
	private static final int BW = 5;
	private final transient AnimeIcon animatedIcon = new AnimeIcon();
	private float alpha;
	private final Dimension currentSize = new Dimension();
	private final Rectangle rect = new Rectangle();
	private final Timer animator = new Timer(10, e -> {
		animatedIcon.next();
		repaint();
	});
	private transient Handler handler;
	private final transient BufferedImage image;

	/**
	 * 动画结束后显示给定图片
	 */
	public LightboxGlassPane(BufferedImage image) {
		super();
		this.image = image;
	}

	@Override
	public void updateUI() {
		removeMouseListener(handler);
		removeHierarchyListener(handler);
		super.updateUI();
		setOpaque(false);
		super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		handler = new Handler();
		addMouseListener(handler);
		addHierarchyListener(handler);
	}

	private class Handler extends MouseAdapter implements HierarchyListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			e.getComponent().setVisible(false);
		}

		@Override
		public void hierarchyChanged(HierarchyEvent e) {
			boolean displayability = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
			if (displayability && !e.getComponent().isDisplayable()) {
				animator.stop();
			}
		}
	}

	@Override
	public void setVisible(boolean b) {
		boolean oldVisible = isVisible();
		super.setVisible(b);
		JRootPane rootPane = getRootPane();
		if (Objects.nonNull(rootPane) && b != oldVisible) {
			rootPane.getLayeredPane().setVisible(!b);
		}
		if (b && !animator.isRunning()) {
			currentSize.setSize(40, 40);
			alpha = 0f;
			animator.start();
		} else {
			animator.stop();
		}
		animatedIcon.setRunning(b);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Optional.ofNullable(getRootPane()).ifPresent(r -> r.getLayeredPane().print(g));
		super.paintComponent(g);

		if (currentSize.height < image.getHeight() + BW + BW) {
			currentSize.height += image.getHeight() / 16;
		} else if (currentSize.width < image.getWidth() + BW + BW) {
			currentSize.height = image.getHeight() + BW + BW;
			currentSize.width += image.getWidth() / 16;
		} else if (1f - alpha > 0) {
			currentSize.width = image.getWidth() + BW + BW;
			alpha = alpha + .1f;
		} else {
			animatedIcon.setRunning(false);
			animator.stop();
		}
		rect.setSize(currentSize);
		Rectangle screen = getBounds();
		Point centerPt = new Point(screen.x + screen.width / 2, screen.y + screen.height / 2);
		rect.setLocation(centerPt.x - rect.width / 2, centerPt.y - rect.height / 2);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(new Color(0x64_64_64_64, true));
		g2.fill(screen);
		g2.setPaint(new Color(0xC8_FF_FF_FF, true));
		g2.fill(rect);

		if (alpha > 0) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(alpha, 1f)));
			g2.drawImage(image, rect.x + BW, rect.y + BW, image.getWidth(), image.getHeight(), this);
		} else {
			int cx = centerPt.x - animatedIcon.getIconWidth() / 2;
			int cy = centerPt.y - animatedIcon.getIconHeight() / 2;
			animatedIcon.paintIcon(this, g2, cx, cy);
		}
		g2.dispose();
	}
}

class AnimeIcon implements Icon {
	private static final Color ELLIPSE_COLOR = new Color(0x80_80_80);
	private static final double R = 2d;
	private static final double SX = 0d;
	private static final double SY = 0d;
	private static final int WIDTH = (int) (R * 8 + SX * 2);
	private static final int HEIGHT = (int) (R * 8 + SY * 2);
	private final java.util.List<Shape> list = new ArrayList<>(Arrays.asList(
			new Ellipse2D.Double(SX + 3 * R, SY + 0 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 5 * R, SY + 1 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 6 * R, SY + 3 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 5 * R, SY + 5 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 3 * R, SY + 6 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 1 * R, SY + 5 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 0 * R, SY + 3 * R, 2 * R, 2 * R),
			new Ellipse2D.Double(SX + 1 * R, SY + 1 * R, 2 * R, 2 * R)));
	private boolean running;

	public void next() {
		if (running) {
			// list.add(list.remove(0));
			Collections.rotate(list, 1);
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		g2.setPaint(new Color(0x0, true));
		g2.fillRect(0, 0, getIconWidth(), getIconHeight());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(ELLIPSE_COLOR);
		int size = list.size();
		for (int i = 0; i < size; i++) {
			float alpha = running ? (i + 1) / (float) size : .5f;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2.fill(list.get(i));
		}
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return WIDTH;
	}

	@Override
	public int getIconHeight() {
		return HEIGHT;
	}
}
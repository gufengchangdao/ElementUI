package com.component.basic.border;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * 获取焦点时带有动画的边框。
 * <p>
 * 支持传入 paint
 */
public class AnimatedBorder extends EmptyBorder implements ActionListener, FocusListener {
	private static final int BOTTOM_SPACE = 20;
	private static final double PLAY_TIME = 300d;
	private static final int BORDER = 4;
	private final Timer animator = new Timer(10, null);
	private final transient Stroke stroke = new BasicStroke(BORDER);
	private final transient Stroke bottomStroke = new BasicStroke(BORDER / 2f);
	private long startTime = -1L;
	private final transient java.util.List<Point2D> points = new ArrayList<>();
	protected final Path2D borderPath = new Path2D.Double();
	private Paint borderColor;
	private JComponent c;

	protected AnimatedBorder(JComponent c) {
		this(c, ColorUtil.PRIMARY);
	}

	/**
	 * @param c           添加边框的组件
	 * @param borderColor 边框颜色，可以是渐变色
	 */
	protected AnimatedBorder(JComponent c, Paint borderColor) {
		super(BORDER, BORDER, BORDER + BOTTOM_SPACE, BORDER);
		this.c = c;
		this.borderColor = borderColor;
		animator.addActionListener(this);
		c.addFocusListener(this);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
		super.paintBorder(c, g, x, y, w, h);
		Graphics2D g2 = (Graphics2D) g.create();
		// g2.setPaint(c.isEnabled() ? Color.ORANGE : Color.GRAY);
		g2.setPaint(borderColor);
		g2.translate(x, y);
		g2.setStroke(bottomStroke);
		g2.drawLine(0, h - BOTTOM_SPACE, w - 1, h - BOTTOM_SPACE);
		g2.setStroke(stroke);
		g2.draw(borderPath);
		g2.dispose();
	}

	public static void makePointList(Shape shape, java.util.List<Point2D> points) {
		points.clear();
		PathIterator pi = shape.getPathIterator(null, .01);
		Point2D prev = new Point2D.Double();
		double delta = .02;
		double threshold = 2d;
		double[] coords = new double[6];
		while (!pi.isDone()) {
			int segment = pi.currentSegment(coords);
			Point2D current = createPoint(coords[0], coords[1]);
			if (segment == PathIterator.SEG_MOVETO) {
				points.add(current);
				prev.setLocation(current);
			} else if (segment == PathIterator.SEG_LINETO) {
				double distance = prev.distance(current);
				double fraction = delta;
				if (distance > threshold) {
					Point2D p = interpolate(prev, current, fraction);
					while (distance > prev.distance(p)) {
						points.add(p);
						fraction += delta;
						p = interpolate(prev, current, fraction);
					}
				} else {
					points.add(current);
				}
				prev.setLocation(current);
			}
			pi.next();
		}
	}

	private static Point2D createPoint(double x, double y) {
		return new Point2D.Double(x, y);
	}

	private static Point2D interpolate(Point2D start, Point2D end, double fraction) {
		double dx = end.getX() - start.getX();
		double dy = end.getY() - start.getY();
		double nx = start.getX() + dx * fraction;
		double ny = start.getY() + dy * fraction;
		return new Point2D.Double(nx, ny);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			JTextField field1 = makeTextField("Animated Border 1");
			field1.setBorder(new AnimatedBorder(field1));
			p.add(field1, "wrap");
			JTextField field2 = makeTextField("Animated Border 2");
			field2.setBorder(new AnimatedBorder(field2));
			p.add(field2, "wrap");

			JTextField field3 = makeTextField("Animated Border 2");
			Dimension s = field3.getPreferredSize();
			field3.setBorder(new AnimatedBorder(field3,
					new LinearGradientPaint(0, 0, s.width, 0,
							new float[]{0.3f, 0.6f}, new Color[]{Color.YELLOW, Color.RED})));
			p.add(field3);

			SwingTestUtil.test();
		});
	}

	private static JTextField makeTextField(String text) {
		JTextField field = new JTextField(text, 32);
		field.setOpaque(false);
		return field;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (startTime < 0) {
			startTime = System.currentTimeMillis();
		}
		long playTime = System.currentTimeMillis() - startTime;
		double progress = playTime / PLAY_TIME;
		boolean stop = progress > 1d || points.isEmpty();
		if (stop) {
			startTime = -1L;
			((Timer) e.getSource()).stop();
			c.repaint();
			return;
		}
		Point2D pos = new Point2D.Double();
		pos.setLocation(points.get(0));
		borderPath.reset();
		borderPath.moveTo(pos.getX(), pos.getY());
		int idx = Math.min(Math.max(0, (int) (points.size() * progress)), points.size() - 1);
		for (int i = 0; i <= idx; i++) {
			pos.setLocation(points.get(i));
			borderPath.lineTo(pos.getX(), pos.getY());
			borderPath.moveTo(pos.getX(), pos.getY());
		}
		borderPath.closePath();
		c.repaint();
	}

	@Override
	public void focusGained(FocusEvent e) {
		Rectangle r = c.getBounds();
		r.height -= BOTTOM_SPACE + 1;
		Path2D p = new Path2D.Double();
		p.moveTo(r.getWidth(), r.getHeight());
		p.lineTo(r.getWidth(), 0d);
		p.lineTo(0d, 0d);
		p.lineTo(0d, r.getHeight());
		p.closePath();
		makePointList(p, points);
		animator.start();
	}

	@Override
	public void focusLost(FocusEvent e) {
		points.clear();
		borderPath.reset();
		c.repaint();
	}
}

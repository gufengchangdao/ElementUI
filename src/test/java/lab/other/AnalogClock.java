package lab.other;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * 绘制转动的时钟
 *
 * <ul>
 *     <li>监听组件的显示与隐藏来控制计时器的启动和关闭</li>
 *     <li>图形绘制</li>
 * </ul>
 */
public class AnalogClock extends JPanel {
	protected LocalTime time = LocalTime.now(ZoneId.systemDefault());
	protected final Timer timer = new Timer(200, e -> {
		time = LocalTime.now(ZoneId.systemDefault());
		repaint();
	});
	private transient HierarchyListener listener;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new FlowLayout());
			AnalogClock analogClock = new AnalogClock();
			analogClock.setPreferredSize(new Dimension(320, 240));
			p.add(analogClock);

			SwingTestUtil.test();
		});
	}

	@Override
	public void updateUI() {
		removeHierarchyListener(listener);
		super.updateUI();
		// 控制计时器的开启和关闭
		listener = e -> {
			if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
				if (e.getComponent().isShowing()) {
					timer.start();
				} else {
					timer.stop();
				}
			}
		};
		addHierarchyListener(listener);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle rect = SwingUtilities.calculateInnerArea(this, null);
		g2.setColor(Color.BLACK);
		g2.fill(rect);
		double radius = Math.min(rect.width, rect.height) / 2d - 10d;
		g2.translate(rect.getCenterX(), rect.getCenterY());

		// Drawing the hour markers
		double hourMarkerLen = radius / 6d - 10d;
		Shape hourMarker = new Line2D.Double(0d, hourMarkerLen - radius, 0d, -radius);
		Shape minuteMarker = new Line2D.Double(0d, hourMarkerLen / 2d - radius, 0d, -radius);
		AffineTransform at = AffineTransform.getRotateInstance(0d);
		g2.setStroke(new BasicStroke(2f));
		g2.setColor(Color.WHITE);
		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {
				g2.draw(at.createTransformedShape(hourMarker));
			} else {
				g2.draw(at.createTransformedShape(minuteMarker));
			}
			at.rotate(Math.PI / 30d);
		}

		// Calculate the angle of rotation
		double secondRot = time.getSecond() * Math.PI / 30d;
		double minuteRot = time.getMinute() * Math.PI / 30d + secondRot / 60d;
		double hourRot = time.getHour() * Math.PI / 6d + minuteRot / 12d;

		// Drawing the hour hand
		double hourHandLen = radius / 2d;
		Shape hourHand = new Line2D.Double(0d, 0d, 0d, -hourHandLen);
		g2.setStroke(new BasicStroke(8f));
		g2.setPaint(Color.LIGHT_GRAY);
		g2.draw(AffineTransform.getRotateInstance(hourRot).createTransformedShape(hourHand));

		// Drawing the minute hand
		double minuteHandLen = 5d * radius / 6d;
		Shape minuteHand = new Line2D.Double(0d, 0d, 0d, -minuteHandLen);
		g2.setStroke(new BasicStroke(4f));
		g2.setPaint(Color.WHITE);
		g2.draw(AffineTransform.getRotateInstance(minuteRot).createTransformedShape(minuteHand));

		// Drawing the second hand
		double r = radius / 6d;
		double secondHandLen = radius - r;
		Shape secondHand = new Line2D.Double(0d, r, 0d, -secondHandLen);
		g2.setPaint(Color.RED);
		g2.setStroke(new BasicStroke(1f));
		g2.draw(AffineTransform.getRotateInstance(secondRot).createTransformedShape(secondHand));
		g2.fill(new Ellipse2D.Double(-r / 4d, -r / 4d, r / 2d, r / 2d));

		g2.dispose();
	}
}

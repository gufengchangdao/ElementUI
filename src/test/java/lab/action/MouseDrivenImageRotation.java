package lab.action;

import com.component.util.GraphicsUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * 可旋转、可拖拽图片
 */
public class MouseDrivenImageRotation extends JPanel {
	private final transient DraggableImageMouseListener di;

	private MouseDrivenImageRotation() {
		super();
		BufferedImage img;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/img/beauty.jpg"));
			img = GraphicsUtil.createThumbnail(img, 600);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		di = new DraggableImageMouseListener(new ImageIcon(img));
		addMouseListener(di);
		addMouseMotionListener(di);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		int w = getWidth();
		int h = getHeight();
		g2.setPaint(new GradientPaint(50f, 0f, Color.GRAY, w, h, Color.DARK_GRAY, true));
		g2.fillRect(0, 0, w, h);
		g2.dispose();
		di.paint(g, this);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			p.add(new MouseDrivenImageRotation(), "growx, growy");
			SwingTestUtil.test();
		});
	}
}

class DraggableImageMouseListener extends MouseAdapter {
	private static final BasicStroke BORDER_STROKE = new BasicStroke(4f);
	private static final Color BORDER_COLOR = Color.WHITE;
	private static final Color HOVER_COLOR = new Color(0x64_64_FF_C8, true);
	private static final int IR = 40;
	private static final int OR = IR * 3;
	private final Shape border;
	private final Shape polaroid;
	private final RectangularShape inner = new Ellipse2D.Double(0d, 0d, IR, IR);
	private final RectangularShape outer = new Ellipse2D.Double(0d, 0d, OR, OR);
	private final Point2D startPt = new Point2D.Double(); // drag start point
	private final Point2D centerPt = new Point2D.Double(100d, 100d); // center of Image
	private final Dimension imageSz;
	private final Image image;
	private double radian = 45d * (Math.PI / 180d);
	private double startRadian; // drag start radian
	private boolean moverHover;
	private boolean rotatorHover;

	protected DraggableImageMouseListener(ImageIcon ii) {
		super();
		image = ii.getImage();
		int width = ii.getIconWidth();
		int height = ii.getIconHeight();
		imageSz = new Dimension(width, height);
		border = new RoundRectangle2D.Double(0d, 0d, width, height, 10d, 10d);
		polaroid = new Rectangle2D.Double(-2d, -2d, width + 4d, height + 20d);
		setCirclesLocation(centerPt);
	}

	private void setCirclesLocation(Point2D center) {
		double cx = center.getX();
		double cy = center.getY();
		inner.setFrameFromCenter(cx, cy, cx + IR / 2d, cy - IR / 2d);
		outer.setFrameFromCenter(cx, cy, cx + OR / 2d, cy - OR / 2d);
	}

	public void paint(Graphics g, ImageObserver observer) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double w2 = imageSz.width / 2d;
		double h2 = imageSz.height / 2d;
		double tx = centerPt.getX() - w2;
		double ty = centerPt.getY() - h2;
		AffineTransform at = AffineTransform.getTranslateInstance(tx, ty);
		at.rotate(radian, w2, h2);

		g2.setPaint(BORDER_COLOR);
		g2.setStroke(BORDER_STROKE);
		Shape s = at.createTransformedShape(polaroid);
		g2.fill(s);
		g2.draw(s);

		g2.drawImage(image, at, observer);

		if (rotatorHover) {
			Area donut = new Area(outer);
			donut.subtract(new Area(inner));
			g2.setPaint(HOVER_COLOR);
			g2.fill(donut);
		} else if (moverHover) {
			g2.setPaint(HOVER_COLOR);
			g2.fill(inner);
		}

		g2.setPaint(BORDER_COLOR);
		g2.setStroke(BORDER_STROKE);
		g2.draw(at.createTransformedShape(border));
		g2.dispose();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point pt = e.getPoint();
		if (outer.contains(pt) && !inner.contains(pt)) {
			moverHover = false;
			rotatorHover = true;
		} else if (inner.contains(pt)) {
			moverHover = true;
			rotatorHover = false;
		} else {
			moverHover = false;
			rotatorHover = false;
		}
		e.getComponent().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		rotatorHover = false;
		moverHover = false;
		e.getComponent().repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point pt = e.getPoint();
		if (outer.contains(pt) && !inner.contains(pt)) {
			rotatorHover = true;
			startRadian = radian - Math.atan2(e.getY() - centerPt.getY(), e.getX() - centerPt.getX());
			e.getComponent().repaint();
		} else if (inner.contains(pt)) {
			moverHover = true;
			startPt.setLocation(pt);
			e.getComponent().repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (rotatorHover) {
			double y = e.getY() - centerPt.getY();
			double x = e.getX() - centerPt.getX();
			radian = startRadian + Math.atan2(y, x);
			e.getComponent().repaint();
		} else if (moverHover) {
			double x = centerPt.getX() + e.getX() - startPt.getX();
			double y = centerPt.getY() + e.getY() - startPt.getY();
			centerPt.setLocation(x, y);
			setCirclesLocation(centerPt);
			startPt.setLocation(e.getPoint());
			e.getComponent().repaint();
		}
	}
}

class MissingIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getIconWidth();
		int h = getIconHeight();
		int gap = w / 5;

		g2.setColor(Color.WHITE);
		g2.fillRect(x, y, w, h);

		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(w / 8f));
		g2.drawLine(x + gap, y + gap, x + w - gap, y + h - gap);
		g2.drawLine(x + gap, y + h - gap, x + w - gap, y + gap);

		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 320;
	}

	@Override
	public int getIconHeight() {
		return 240;
	}
}



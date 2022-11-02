package lab.other.draw;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * 获取到屏幕快照，绘制在面板上，实现窗体“半透明 + 模糊”效果
 */
public class ScreenCapture extends JPanel {
	private final transient Robot robot;
	private final Rectangle screenRect;
	private final Rectangle buf = new Rectangle();
	private transient BufferedImage backgroundImage;
	private final float[] data = {
			.1f, .1f, .1f,
			.1f, .2f, .1f,
			.1f, .1f, .1f,
	};
	private final transient Kernel kernel = new Kernel(3, 3, data);
	private final transient ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
	private final Color bgc = new Color(255, 255, 255, 100);

	private ScreenCapture() {
		super();
		robot = createRobot();
		screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		updateBackground();
		EventQueue.invokeLater(() -> {
			Window f = SwingUtilities.getWindowAncestor(this);
			f.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					repaint();
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					repaint();
				}
			});
			f.addWindowListener(new WindowAdapter() {
				@Override
				public void windowDeiconified(WindowEvent e) {
					updateBackground();
				}
			});
		});

		add(new JButton("JButton"));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		Point pt = getLocationOnScreen();
		buf.setBounds(screenRect);
		SwingUtilities.computeIntersection(pt.x, pt.y, getWidth(), getHeight(), buf);
		Image img = backgroundImage.getSubimage(buf.x, buf.y, buf.width, buf.height);
		g2.drawImage(img, -Math.min(pt.x, 0), -Math.min(pt.y, 0), this);
		g2.setPaint(bgc);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.dispose();
	}

	public void updateBackground() {
		backgroundImage = op.filter(robot.createScreenCapture(screenRect), null);
	}

	private static Robot createRobot() {
		Robot rbt = null;
		try {
			rbt = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		return rbt;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			p.add(new ScreenCapture(), "growx, growy");
			SwingTestUtil.test();
		});
	}
}

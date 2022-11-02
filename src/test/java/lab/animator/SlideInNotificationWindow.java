package lab.animator;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 提示的淡进淡出
 */
public class SlideInNotificationWindow extends JPanel {
	private SlideInNotificationWindow() {
		super(new BorderLayout());
		SlideInNotification handler = new SlideInNotification();

		// optionPane.addPropertyChangeListener(e -> {
		//   if (dialog.isVisible() && e.getSource() == optionPane
		//       // && e.getPropertyName().equals(VALUE_PROPERTY)
		//       && Objects.nonNull(e.getNewValue())
		//       && e.getNewValue() != JOptionPane.UNINITIALIZED_VALUE) {
		//     dialog.setVisible(false);
		//   }
		// });
		// dialog.getContentPane().add(optionPane);
		// dialog.pack();

		JButton easeIn = new JButton("easeIn");
		easeIn.addActionListener(e -> handler.startSlideIn(SlideInAnimation.EASE_IN));

		JButton easeOut = new JButton("easeOut");
		easeOut.addActionListener(e -> handler.startSlideIn(SlideInAnimation.EASE_OUT));

		JButton easeInOut = new JButton("easeInOut");
		easeInOut.addActionListener(e -> handler.startSlideIn(SlideInAnimation.EASE_IN_OUT));

		JPanel p = new JPanel();
		p.add(easeIn);
		p.add(easeOut);
		p.add(easeInOut);
		add(p, BorderLayout.NORTH);
		add(new JScrollPane(new JTextArea()));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new SlideInNotificationWindow());
			SwingTestUtil.test();
		});
	}

}

class SlideInNotification implements PropertyChangeListener, HierarchyListener {
	public static final int DELAY = 5;
	public static final int STEP = 3;
	private final JWindow dialog = new JWindow((Frame) null);
	private final Timer animator = new Timer(DELAY, null);
	private ActionListener listener;

	public void startSlideIn(SlideInAnimation slideInAnimation) {
		if (animator.isRunning()) {
			return;
		}
		if (dialog.isVisible()) {
			dialog.setVisible(false);
			dialog.getContentPane().removeAll();
		}

		JOptionPane optionPane = new JOptionPane("Warning", JOptionPane.WARNING_MESSAGE);
		DragWindowListener dwl = new DragWindowListener();
		optionPane.addMouseListener(dwl);
		optionPane.addMouseMotionListener(dwl);
		optionPane.addPropertyChangeListener(this);
		optionPane.addHierarchyListener(this);
		dialog.getContentPane().add(optionPane);
		dialog.pack();

		Dimension d = dialog.getContentPane().getPreferredSize();
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle desktopBounds = env.getMaximumWindowBounds();
		int dx = desktopBounds.width - d.width;
		int dy = desktopBounds.height;
		dialog.setLocation(new Point(dx, dy));
		dialog.setVisible(true);

		animator.removeActionListener(listener);
		AtomicInteger count = new AtomicInteger();
		listener = e -> {
			double v = count.addAndGet(STEP) / (double) d.height;
			double a;
			if (slideInAnimation == SlideInAnimation.EASE_IN) {
				a = AnimationUtil.easeIn(v);
			} else if (slideInAnimation == SlideInAnimation.EASE_OUT) {
				a = AnimationUtil.easeOut(v);
			} else { // EASE_IN_OUT
				a = AnimationUtil.easeInOut(v);
			}
			int visibleHeight = (int) (.5 + a * d.height);
			if (visibleHeight >= d.height) {
				visibleHeight = d.height;
				animator.stop();
			}
			dialog.setLocation(new Point(dx, dy - visibleHeight));
		};
		animator.addActionListener(listener);
		animator.start();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (dialog.isVisible() && !JOptionPane.UNINITIALIZED_VALUE.equals(e.getNewValue())) {
			dialog.setVisible(false);
			dialog.getContentPane().removeAll();
		}
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		boolean b = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
		if (b && !e.getComponent().isDisplayable()) {
			animator.stop();
		}
	}
}

class DragWindowListener extends MouseAdapter {
	private final Point startPt = new Point();

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			startPt.setLocation(e.getPoint());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component c = SwingUtilities.getRoot(e.getComponent());
		if (c instanceof Window && SwingUtilities.isLeftMouseButton(e)) {
			Point pt = c.getLocation();
			c.setLocation(pt.x - startPt.x + e.getX(), pt.y - startPt.y + e.getY());
		}
	}
}

enum SlideInAnimation {
	EASE_IN, EASE_OUT, EASE_IN_OUT
}

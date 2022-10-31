package lab.component.listener;
// vim:set fileencoding=utf-8:
// @homepage@


import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 两种方法实现视图随鼠标拖拽而改变
 */
public final class AutoScroll extends JPanel {
	private AutoScroll() {
		super(new BorderLayout());
		JLabel label1 = makeImageLabel();
		JScrollPane scroll = makeScrollPane(label1);
		ViewportDragScrollListener l = new ViewportDragScrollListener();
		JViewport v = scroll.getViewport();
		v.addMouseMotionListener(l);
		v.addMouseListener(l);
		v.addHierarchyListener(l);

		JLabel label2 = makeImageLabel();
		ComponentDragScrollListener l2 = new ComponentDragScrollListener();
		label2.addMouseMotionListener(l2);
		label2.addMouseListener(l2);
		label2.addHierarchyListener(l2);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("ViewportDragScrollListener", scroll);
		tabbedPane.addTab("ComponentDragScrollListener", makeScrollPane(label2));

		add(tabbedPane);
		setPreferredSize(new Dimension(320, 240));
	}

	private static JLabel makeImageLabel() {
		Icon icon = new ImageIcon(AutoScroll.class.getResource("CRW_3857_JFR.jpg"));
		return new JLabel(icon);
	}

	private static JScrollPane makeScrollPane(Component c) {
		JScrollPane scroll = new JScrollPane(c);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return scroll;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new BorderLayout());
			p.add(new AutoScroll());
			SwingTestUtil.test();
		});
	}
}

class ViewportDragScrollListener extends MouseAdapter implements HierarchyListener {
	private static final int SPEED = 4;
	private static final int DELAY = 10;
	private static final Cursor DC = Cursor.getDefaultCursor();
	private static final Cursor HC = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	private final Point startPt = new Point();
	private final Point move = new Point();
	private final Timer scroller = new Timer(DELAY, null);
	private ActionListener listener;

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		boolean b = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
		if (b && !e.getComponent().isDisplayable()) {
			scroller.stop();
			scroller.removeActionListener(listener);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		JViewport vport = (JViewport) e.getComponent();
		JComponent c = (JComponent) vport.getView();
		Point pt = e.getPoint();
		int dx = startPt.x - pt.x;
		int dy = startPt.y - pt.y;
		Point vp = vport.getViewPosition();
		vp.translate(dx, dy);
		c.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
		move.setLocation(SPEED * dx, SPEED * dy);
		startPt.setLocation(pt);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		e.getComponent().setCursor(HC);
		startPt.setLocation(e.getPoint());
		move.setLocation(0, 0);
		scroller.stop();
		scroller.removeActionListener(listener);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		c.setCursor(DC);
		if (c instanceof JViewport) {
			JViewport vport = (JViewport) c;
			JComponent label = (JComponent) vport.getView();
			listener = event -> {
				Point vp = vport.getViewPosition(); // = SwingUtilities.convertPoint(vport, 0, 0, label);
				vp.translate(move.x, move.y);
				label.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
				// vport.setViewPosition(vp);
			};
			scroller.addActionListener(listener);
			scroller.start();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setCursor(DC);
		move.setLocation(0, 0);
		scroller.stop();
		scroller.removeActionListener(listener);
	}
}

class ComponentDragScrollListener extends MouseAdapter implements HierarchyListener {
	private static final int SPEED = 4;
	private static final int DELAY = 10;
	private static final Cursor DC = Cursor.getDefaultCursor();
	private static final Cursor HC = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	private final Point startPt = new Point();
	private final Point move = new Point();
	private final Timer scroller = new Timer(DELAY, null);
	private ActionListener listener;

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		boolean b = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
		if (b && !e.getComponent().isDisplayable()) {
			scroller.stop();
			scroller.removeActionListener(listener);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		scroller.stop();
		scroller.removeActionListener(listener);
		JComponent jc = (JComponent) e.getComponent();
		Container c = SwingUtilities.getAncestorOfClass(JViewport.class, jc);
		if (c instanceof JViewport) {
			JViewport vport = (JViewport) c;
			Point cp = SwingUtilities.convertPoint(jc, e.getPoint(), vport);
			int dx = startPt.x - cp.x;
			int dy = startPt.y - cp.y;
			Point vp = vport.getViewPosition();
			vp.translate(dx, dy);
			jc.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
			move.setLocation(SPEED * dx, SPEED * dy);
			startPt.setLocation(cp);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		scroller.stop();
		scroller.removeActionListener(listener);
		move.setLocation(0, 0);
		Component c = e.getComponent();
		c.setCursor(HC);
		Container p = SwingUtilities.getUnwrappedParent(c);
		if (p instanceof JViewport) {
			JViewport vport = (JViewport) p;
			startPt.setLocation(SwingUtilities.convertPoint(c, e.getPoint(), vport));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		c.setCursor(DC);
		listener = event -> {
			Container p = SwingUtilities.getUnwrappedParent(c);
			if (p instanceof JViewport) {
				JViewport vport = (JViewport) p;
				Point vp = vport.getViewPosition();
				vp.translate(move.x, move.y);
				((JComponent) c).scrollRectToVisible(new Rectangle(vp, vport.getSize()));
			}
		};
		scroller.addActionListener(listener);
		scroller.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		scroller.stop();
		scroller.removeActionListener(listener);
		e.getComponent().setCursor(DC);
		move.setLocation(0, 0);
	}
}
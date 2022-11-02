package com.component.data.table.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 实现表格数据可拖拽
 * <p>
 * 使用示例
 * <pre>
 * handler = new DragScrollingListener(this);
 * addMouseMotionListener(handler);
 * addMouseListener(handler);
 * </pre>
 */
public class DragScrollingListener extends MouseAdapter {
	public static final int VELOCITY = 5;
	public static final int DELAY = 10;
	public static final double GRAVITY = .95;
	private final Cursor dc = Cursor.getDefaultCursor();
	private final Cursor hc = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	private final Timer scroller;
	private final Point startPt = new Point();
	private final Point delta = new Point();

	public DragScrollingListener(JComponent c) {
		super();
		this.scroller = new Timer(DELAY, e -> {
			JViewport vport = (JViewport) SwingUtilities.getUnwrappedParent(c);
			Point vp = vport.getViewPosition();
			vp.translate(-delta.x, -delta.y);
			c.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
			if (Math.abs(delta.x) > 0 || Math.abs(delta.y) > 0) {
				delta.setLocation((int) (delta.x * GRAVITY), (int) (delta.y * GRAVITY));
			} else {
				((Timer) e.getSource()).stop();
			}
		});
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		c.setCursor(hc);
		c.setEnabled(false);
		Container p = SwingUtilities.getUnwrappedParent(c);
		if (p instanceof JViewport) {
			startPt.setLocation(SwingUtilities.convertPoint(c, e.getPoint(), p));
			scroller.stop();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component c = e.getComponent();
		Container p = SwingUtilities.getUnwrappedParent(c);
		if (p instanceof JViewport) {
			JViewport vport = (JViewport) p;
			Point cp = SwingUtilities.convertPoint(c, e.getPoint(), vport);
			Point vp = vport.getViewPosition();
			vp.translate(startPt.x - cp.x, startPt.y - cp.y);
			delta.setLocation(VELOCITY * (cp.x - startPt.x), VELOCITY * (cp.y - startPt.y));
			((JComponent) c).scrollRectToVisible(new Rectangle(vp, vport.getSize()));
			startPt.setLocation(cp);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		c.setCursor(dc);
		c.setEnabled(true);
		scroller.start();
	}
}

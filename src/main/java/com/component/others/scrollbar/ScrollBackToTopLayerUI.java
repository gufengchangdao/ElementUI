package com.component.others.scrollbar;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 滚动窗格回到顶部外观
 */
public class ScrollBackToTopLayerUI extends LayerUI<JScrollPane> {
	private static final int GAP = 5;
	private final Container rubberStamp = new JPanel();
	private final Point mousePt = new Point();
	private final JButton button;
	private final Rectangle buttonRect;

	public ScrollBackToTopLayerUI() {
		this(new ScrollBackToTopIcon());
	}

	/**
	 * 自定义回到顶部按钮的图标
	 */
	public ScrollBackToTopLayerUI(Icon icon) {
		button = new JButton(icon) {
			@Override
			public void updateUI() {
				super.updateUI();
				setBorder(BorderFactory.createEmptyBorder());
				setFocusPainted(false);
				setBorderPainted(false);
				setContentAreaFilled(false);
				setRolloverEnabled(false);
			}
		};
		buttonRect = new Rectangle(button.getPreferredSize());
	}


	private void updateButtonRect(JScrollPane scroll) {
		JViewport viewport = scroll.getViewport();
		int x = viewport.getX() + viewport.getWidth() - buttonRect.width - GAP;
		int y = viewport.getY() + viewport.getHeight() - buttonRect.height - GAP;
		buttonRect.setLocation(x, y);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		if (c instanceof JLayer) {
			JScrollPane scroll = (JScrollPane) ((JLayer<?>) c).getView();
			updateButtonRect(scroll);
			if (scroll.getViewport().getViewRect().y > 0) {
				button.getModel().setRollover(buttonRect.contains(mousePt));
				SwingUtilities.paintComponent(g, button, rubberStamp, buttonRect);
			}
		}
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		if (c instanceof JLayer) {
			((JLayer<?>) c).setLayerEventMask(
					AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		}
	}

	@Override
	public void uninstallUI(JComponent c) {
		if (c instanceof JLayer) {
			((JLayer<?>) c).setLayerEventMask(0);
		}
		super.uninstallUI(c);
	}

	@Override
	protected void processMouseEvent(MouseEvent e, JLayer<? extends JScrollPane> l) {
		JScrollPane scroll = l.getView();
		Rectangle r = scroll.getViewport().getViewRect();
		Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), scroll);
		mousePt.setLocation(p);
		int id = e.getID();
		if (id == MouseEvent.MOUSE_CLICKED) {
			if (buttonRect.contains(mousePt)) {
				scrollBackToTop(l.getView());
			}
		} else if (id == MouseEvent.MOUSE_PRESSED && r.y > 0 && buttonRect.contains(mousePt)) {
			e.consume();
		}
	}

	@Override
	protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JScrollPane> l) {
		Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l.getView());
		mousePt.setLocation(p);
		l.repaint(buttonRect);
	}

	private void scrollBackToTop(JScrollPane scroll) {
		JComponent c = (JComponent) scroll.getViewport().getView();
		Rectangle current = scroll.getViewport().getViewRect();
		new Timer(20, e -> {
			Timer animator = (Timer) e.getSource();
			if (0 < current.y && animator.isRunning()) {
				current.y -= Math.max(1, current.y / 2);
				c.scrollRectToVisible(current);
			} else {
				animator.stop();
			}
		}).start();
	}
}


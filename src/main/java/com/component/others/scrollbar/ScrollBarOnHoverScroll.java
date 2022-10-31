package com.component.others.scrollbar;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.stream.IntStream;

public class ScrollBarOnHoverScroll extends JPanel {
	public final JPanel scrollBar = new JPanel();
	public final Timer expand = new Timer(10, e -> scrollBar.revalidate());
	public final Timer collapse = new Timer(10, e -> scrollBar.revalidate());

	private ScrollBarOnHoverScroll(Component c) {
		JScrollPane scroll = new JScrollPane(c);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		scrollBar.setLayout(new ScrollBarLayout());
		scrollBar.add(scroll.getVerticalScrollBar());

		setLayout(new BorderLayout());
		add(scrollBar, BorderLayout.EAST);
		add(scroll);
		// return new JLayer<>(wrap, new TimerScrollBarLayerUI());
	}

	public static JLayer<JPanel> getLayer(Component c) {
		ScrollBarOnHoverScroll p = new ScrollBarOnHoverScroll(c);
		return new JLayer<>(p, p.new TimerScrollBarLayerUI());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			p.add(getLayer(makeList()));

			SwingTestUtil.test();
		});
	}

	private static Component makeList() {
		DefaultListModel<String> m = new DefaultListModel<>();
		IntStream.range(0, 50)
				.mapToObj(i -> String.format("%05d: %s", i, LocalDateTime.now(ZoneId.systemDefault())))
				.forEach(m::addElement);
		return new JList<>(m);
	}

	private class ScrollBarLayout extends BorderLayout {
		public static final int MIN_WIDTH = 6;
		private int controlsWidth = MIN_WIDTH;

		@Override
		public Dimension preferredLayoutSize(Container target) {
			Dimension ps = super.preferredLayoutSize(target);
			int barInitWidth = ps.width;
			if (expand.isRunning() && scrollBar.getWidth() < barInitWidth) {
				controlsWidth += 1;
				if (controlsWidth >= barInitWidth) {
					controlsWidth = barInitWidth;
					expand.stop();
				}
			} else if (collapse.isRunning() && scrollBar.getWidth() > MIN_WIDTH) {
				controlsWidth -= 1;
				if (controlsWidth <= MIN_WIDTH) {
					controlsWidth = MIN_WIDTH;
					collapse.stop();
				}
			}
			ps.width = controlsWidth;
			return ps;
		}
	}

	private class TimerScrollBarLayerUI extends ScrollBarLayerUI {
		@Override
		protected void processMouseEvent(MouseEvent e, JLayer<? extends JPanel> l) {
			if (!(e.getComponent() instanceof JScrollBar)) {
				return;
			}
			switch (e.getID()) {
				case MouseEvent.MOUSE_ENTERED:
					expandStart(isDragging);
					break;
				case MouseEvent.MOUSE_EXITED:
					collapseStart(isDragging);
					break;
				case MouseEvent.MOUSE_RELEASED:
					isDragging = false;
					collapseStart(!e.getComponent().getBounds().contains(e.getPoint()));
					break;
				default:
					break;
			}
			l.getView().repaint();
		}

		private void expandStart(boolean dragging) {
			if (!expand.isRunning() && !dragging) {
				expand.setInitialDelay(0);
				expand.start();
			}
		}

		private void collapseStart(boolean dragging) {
			if (!collapse.isRunning() && !dragging) {
				collapse.setInitialDelay(500);
				collapse.start();
			}
		}
	}
}

class ScrollBarLayerUI extends LayerUI<JPanel> {
	protected boolean isDragging;

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
	protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JPanel> l) {
		int id = e.getID();
		Component c = e.getComponent();
		if (c instanceof JScrollBar && id == MouseEvent.MOUSE_DRAGGED) {
			isDragging = true;
		}
	}
}


package com.component.others.scrollbar;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class TranslucentScroll extends JScrollPane {
	public TranslucentScroll(Component c) {
		super(c);
	}

	public static JLayer<JPanel> getLayer(Component c) {

		return new JLayer<>(makeTranslucentScrollBar(makeList()), new ScrollBarOnHoverLayerUI());
	}

	@Override
	public boolean isOptimizedDrawingEnabled() {
		return false; // JScrollBar is overlap
	}

	@Override
	public void updateUI() {
		super.updateUI();
		EventQueue.invokeLater(() -> {
			getVerticalScrollBar().setUI(new TranslucentScrollBarUI());
			setComponentZOrder(getVerticalScrollBar(), 0);
			setComponentZOrder(getViewport(), 1);
			getVerticalScrollBar().setOpaque(false);
			getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));
		});
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setLayout(new TranslucentScrollPaneLayout());
	}
}

class TranslucentScrollPaneLayout extends ScrollPaneLayout {
	private static final int BAR_SIZE = 12;

	@Override
	public void layoutContainer(Container parent) {
		if (parent instanceof JScrollPane) {
			JScrollPane scrollPane = (JScrollPane) parent;
			Rectangle availR = SwingUtilities.calculateInnerArea(scrollPane, null);
			if (Objects.nonNull(viewport)) {
				viewport.setBounds(availR);
			}
			if (Objects.nonNull(vsb)) {
				vsb.setLocation(availR.x + availR.width - BAR_SIZE, availR.y);
				vsb.setSize(BAR_SIZE, availR.height);
				vsb.setVisible(true);
			}
		}
	}
}

class ScrollBarOnHoverLayerUI extends LayerUI<JScrollPane> {
	private final Timer timer = new Timer(2000, null);
	private transient ActionListener listener;

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		if (c instanceof JLayer) {
			((JLayer<?>) c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK);
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
		int id = e.getID();
		Component c = e.getComponent();
		if (c instanceof JScrollBar) {
			if (id == MouseEvent.MOUSE_ENTERED) {
				c.setPreferredSize(new Dimension(TranslucentScrollBarUI.MAX_WIDTH, 0));
			} else if (id == MouseEvent.MOUSE_EXITED) {
				timer.removeActionListener(listener);
				listener = ev -> {
					c.setPreferredSize(new Dimension(TranslucentScrollBarUI.MIN_WIDTH, 0));
					l.getView().revalidate();
					l.getView().repaint();
				};
				timer.addActionListener(listener);
				timer.setRepeats(false);
				timer.start();
			}
			l.getView().repaint();
		}
	}
}

class TranslucentScrollBarUI extends BasicScrollBarUI {
	protected static final int MAX_WIDTH = 12;
	protected static final int MIN_WIDTH = 6;
	private static final Color DEFAULT_COLOR = new Color(100, 100, 100, 190);
	private static final Color DRAGGING_COLOR = new Color(100, 100, 100, 220);
	private static final Color ROLLOVER_COLOR = new Color(100, 100, 100, 220);

	@Override
	protected JButton createDecreaseButton(int orientation) {
		return new ZeroSizeButton();
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return new ZeroSizeButton();
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		// Graphics2D g2 = (Graphics2D) g.create();
		// g2.setPaint(new Color(100, 100, 100, 100));
		// g2.fillRect(r.x, r.y, r.width - 1, r.height - 1);
		// g2.dispose();
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		JScrollBar sb = (JScrollBar) c;
		Color color;
		if (!sb.isEnabled() || r.width > r.height) {
			return;
		} else if (isDragging) {
			color = DRAGGING_COLOR;
		} else if (isThumbRollover()) {
			color = ROLLOVER_COLOR;
		} else {
			color = DEFAULT_COLOR;
			int dw = r.width - sb.getPreferredSize().width;
			r.x += dw;
			r.width -= dw;
		}
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(color);
		g2.fillRect(r.x, r.y, r.width - 2, r.height - 1);
		g2.dispose();
	}
}

class ZeroSizeButton extends JButton {
	private static final Dimension ZERO_SIZE = new Dimension();

	@Override
	public Dimension getPreferredSize() {
		return ZERO_SIZE;
	}
}
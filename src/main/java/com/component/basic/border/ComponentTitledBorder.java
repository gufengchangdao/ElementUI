package com.component.basic.border;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 左上角为组件的边框
 */
public class ComponentTitledBorder extends MouseAdapter implements Border, SwingConstants {
	private static final int OFFSET = 5;
	private final Component comp;
	private final Border border;

	protected ComponentTitledBorder(Component comp, Container container, Border border) {
		super();
		this.comp = comp;
		this.border = border;
		if (comp instanceof JComponent) {
			((JComponent) comp).setOpaque(true);
		}
		container.addMouseListener(this);
		container.addMouseMotionListener(this);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		if (c instanceof Container) {
			Insets borderInsets = border.getBorderInsets(c);
			Insets insets = getBorderInsets(c);
			int temp = (insets.top - borderInsets.top) / 2;
			border.paintBorder(c, g, x, y + temp, width, height - temp);
			Dimension size = comp.getPreferredSize();
			Rectangle rect = new Rectangle(OFFSET, 0, size.width, size.height);
			SwingUtilities.paintComponent(g, comp, (Container) c, rect);
			comp.setBounds(rect);
		}
	}

	@Override
	public Insets getBorderInsets(Component c) {
		Dimension size = comp.getPreferredSize();
		Insets insets = border.getBorderInsets(c);
		insets.top = Math.max(insets.top, size.height);
		return insets;
	}

	/**
	 * 事件分发，监听组件的上的事件并分发给作为边框的组件
	 */
	private void dispatchEvent(MouseEvent e) {
		Component src = e.getComponent();
		// 事件通知给边框上的组件
		comp.dispatchEvent(SwingUtilities.convertMouseEvent(src, e, comp));
		src.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		dispatchEvent(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		dispatchEvent(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		dispatchEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		dispatchEvent(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dispatchEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		dispatchEvent(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		dispatchEvent(e);
	}

	public Component getComp() {
		return comp;
	}

	public Border getBorder() {
		return border;
	}
}
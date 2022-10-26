package com.component.animator.popup.listener;

import com.component.animator.popup.PopupAnimatorTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CloseMouseListener implements MouseListener {
	private Container container;
	private JComponent c;
	private PopupAnimatorTask<?> task;

	public CloseMouseListener(Container container, JComponent c, PopupAnimatorTask<?> task) {
		this.container = container;
		this.c = c;
		this.task = task;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (task != null) {
			task.startFadeOutAnimator();
		} else {
			if (container == null) return;
			container.remove(c);
			Dimension size = c.getPreferredSize();
			container.repaint(c.getX(), c.getY(), size.width, size.height);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

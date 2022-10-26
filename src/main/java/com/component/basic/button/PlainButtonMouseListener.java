package com.component.basic.button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 朴素按钮的动作监听
 */
public class PlainButtonMouseListener implements MouseListener {
	private JButton button;
	/** 字体色 */
	private Color c;
	private Color backgroundColor;
	private boolean isPressed = false;
	private boolean isEnter = false;

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public PlainButtonMouseListener(Color c, Color backgroundColor) {
		this.c = c;
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
		button.setBackground(c.darker());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
		if (!isEnter) mouseExited(e);
		else button.setBackground(c);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		isEnter = true;
		button.setBackground(c);
		button.setForeground(Color.WHITE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		isEnter = false;
		if (isPressed) return;
		button.setBackground(backgroundColor);
		button.setForeground(c);
	}
}

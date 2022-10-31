package com.component.listener;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 整数连加鼠标监听器
 * <p>
 * 为按钮添加MouseListener和ActionListener，可以实现点击按钮和按住按钮实现输入组件加数效果，例如：
 * <pre>
 * 	JButton b1 = new JButton(String.format("%+d", 1));
 * 	b1.addMouseListener(autoRepeatHandler);
 * 	b1.addActionListener(autoRepeatHandler);
 * </pre>
 */
public class AutoRepeatHandler extends MouseAdapter implements ActionListener {
	private final Timer autoRepeatTimer;
	private final BigInteger extent;
	private final JTextComponent view;
	private JButton arrowButton;

	/**
	 * @param extent 步长
	 * @param view   输入组件，要求文本内容为整数
	 */
	public AutoRepeatHandler(int extent, JTextComponent view) {
		super();
		this.extent = BigInteger.valueOf(extent);
		this.view = view;
		autoRepeatTimer = new Timer(60, this);
		autoRepeatTimer.setInitialDelay(300); //连加的初始延迟
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof Timer) {
			// 是否松开，松开就停止计时器
			boolean isPressed = Objects.nonNull(arrowButton) && !arrowButton.getModel().isPressed();
			if (isPressed && autoRepeatTimer.isRunning()) {
				autoRepeatTimer.stop();
				arrowButton = null;
			}
		} else if (o instanceof JButton) {
			arrowButton = (JButton) o;
		}
		BigInteger i = new BigInteger(view.getText()); //文本转数字
		view.setText(i.add(extent).toString()); //数字加上步长后再转文本
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e) && e.getComponent().isEnabled()) {
			autoRepeatTimer.start();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		autoRepeatTimer.stop();
		arrowButton = null;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (autoRepeatTimer.isRunning()) {
			autoRepeatTimer.stop();
		}
	}
}
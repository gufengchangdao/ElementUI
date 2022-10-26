package com.component.basic.button;

import com.component.basic.color.ColorUtil;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.PropertySetter;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * 圆角Switch开关
 */
public class SwitchButton extends JButton implements ActionListener {
	private boolean isOpen;
	/** 当前背景色，动画使用 */
	private Color currentColor;
	private Color closeColor = ColorUtil.DANGER;
	private Color openColor = ColorUtil.SUCCESS;
	private Animator animator;
	/** 白色按钮位移动画 */
	private TimingTargetAdapter target;
	/** 白色球体x坐标，动画使用 */
	private int xPosition;

	public SwitchButton() {
		init();
	}

	public SwitchButton(boolean isOpen) {
		this.isOpen = isOpen;
		init();
	}

	public SwitchButton(boolean isOpen, Color closeColor, Color openColor) {
		this.isOpen = isOpen;
		this.closeColor = closeColor;
		this.openColor = openColor;
		init();
	}

	private void init() {
		currentColor = isOpen ? openColor : closeColor;

		animator = new Animator.Builder()
				.setDuration(200, TimeUnit.MILLISECONDS)
				.addTarget(PropertySetter.getTarget(this, "currentColor", closeColor, openColor))
				.build();
		addActionListener(this);

		// 这里面有设置小球位移路径的动画
		setPreferredSize(new Dimension(50, 25));
		setBorderPainted(false);
		setFocusable(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension size = getPreferredSize();

		boolean enabled = isEnabled();
		// 绘制背景
		if (enabled) g2.setColor(currentColor);
		else g2.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 120));
		g2.fillArc(0, 0, size.height, size.height, 90, 180);
		g2.fillRect(size.height / 2 - 1, 0, Math.max(size.width - size.height + 2, 0), size.height);
		g2.fillArc(size.width - size.height, 0, size.height, size.height, -90, 180);

		// 绘制圆点
		g2.setColor(Color.WHITE);
		int d = (int) (size.height * 0.8); //直径
		g2.fillOval(xPosition, (size.height - d) / 2, d, d);
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean open) {
		isOpen = open;
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (animator.isRunning()) return;
		isOpen = !isOpen;
		if (isOpen) animator.start();
		else animator.restartReverse();
		repaint();
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
		repaint();
	}

	public Color getCloseColor() {
		return closeColor;
	}

	public void setCloseColor(Color closeColor) {
		this.closeColor = closeColor;
	}

	public Color getOpenColor() {
		return openColor;
	}

	public void setOpenColor(Color openColor) {
		this.openColor = openColor;
	}

	public int getXPosition() {
		return xPosition;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
		repaint();
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		// 修改白色小球移动路径
		animator.removeTarget(target);
		Dimension size = getPreferredSize();
		int d = (int) (size.height * 0.8); //直径
		xPosition = isOpen ? (size.width - (size.height + d) / 2) : ((size.height - d) / 2);
		target = PropertySetter.getTarget(this, "xPosition",
				(size.height - d) / 2, size.width - (size.height + d) / 2);
		animator.addTarget(target);
	}
}

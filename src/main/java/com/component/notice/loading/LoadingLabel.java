package com.component.notice.loading;

import com.component.radiance.common.api.icon.RadianceIcon;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.concurrent.TimeUnit;

/**
 * 加载标签，图标绕中心旋转动画
 */
public class LoadingLabel extends JLabel implements TimingTarget, HierarchyListener {
	private RadianceIcon icon;
	private Animator animator;
	private double ratio = 0;

	/**
	 * 创建实例并启动动画
	 *
	 * @param icon     图标
	 * @param duration 转一圈所需时间，单位为毫秒
	 */
	public LoadingLabel(RadianceIcon icon, int duration) {
		super(icon);
		this.icon = icon;
		animator = new Animator.Builder()
				.setDuration(duration, TimeUnit.MILLISECONDS)
				.setRepeatCount(Animator.INFINITE)
				.setRepeatBehavior(Animator.RepeatBehavior.LOOP)
				.addTarget(this)
				.build();

		addHierarchyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// 旋转角度和旋转点
		g2.rotate(ratio, getWidth() / 2, getHeight() / 2);
		super.paintComponent(g2);
	}

	@Override
	public void begin(Animator animator) {
	}

	@Override
	public void end(Animator animator) {

	}

	@Override
	public void repeat(Animator animator) {

	}

	@Override
	public void reverse(Animator animator) {

	}

	@Override
	public void timingEvent(Animator animator, double v) {
		ratio = Math.PI * v;
		repaint();
	}

	@Override
	public RadianceIcon getIcon() {
		return icon;
	}

	public Animator getAnimator() {
		return animator;
	}

	public double getRatio() {
		return ratio;
	}

	public void setIcon(RadianceIcon icon) {
		this.icon = icon;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		long flags = e.getChangeFlags();
		if (flags == HierarchyEvent.SHOWING_CHANGED && !animator.isRunning())
			animator.restart();
		else if (flags == HierarchyEvent.DISPLAYABILITY_CHANGED && animator.isRunning())
			animator.cancel();
	}
}

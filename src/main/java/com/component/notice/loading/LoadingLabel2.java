package com.component.notice.loading;

import com.component.util.UIUtil;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.concurrent.TimeUnit;

/**
 * 加载标签，弧形线条旋转的同时长度也在变化。
 * <p>
 * 这个动画的好坏跟参数有很大关系，我都费了很大劲调这个参数
 */
public class LoadingLabel2 extends JComponent implements HierarchyListener {
	/** 最小角度 */
	public static int minVal = 0;
	/** 最大角度 */
	public static int maxVal = 270;
	/** 当前值 */
	private int to = minVal;
	// 旋转使用的步长
	private double step = 0.01;
	private double sum = 0;
	/** 旋转角度 */
	private double ratio = 0;
	private Animator rotateAnimator;
	private Animator angerAnimator;
	/** 加载条颜色 */
	private Color fg;
	/** 加载条线宽 */
	private int strokeWidth;

	/**
	 * @param fg          加载条颜色
	 * @param strokeWidth 加载条线宽
	 */
	public LoadingLabel2(Color fg, int strokeWidth) {
		this.fg = fg;
		this.strokeWidth = strokeWidth;
		// 匀速旋转
		rotateAnimator = new Animator.Builder()
				.setDuration(1600, TimeUnit.MILLISECONDS)
				.setRepeatCount(Animator.INFINITE)
				.setInterpolator(new AccelerationInterpolator(0.05, 0.04))
				.setRepeatBehavior(Animator.RepeatBehavior.LOOP)
				.addTarget(new TimingTargetAdapter() {
					@Override
					public void timingEvent(Animator source, double fraction) {
						ratio = 2 * Math.PI * fraction;
						sum += step;
						if (sum > 100) sum -= 100; //100没有特殊含义。只是防止数据溢出
						ratio += sum;
						if (ratio > 2 * Math.PI) ratio = ratio - 2 * Math.PI;
						repaint();
					}
				})
				.build();

		// 角度动画
		angerAnimator = new Animator.Builder()
				.setDuration(1600, TimeUnit.MILLISECONDS)
				.setRepeatCount(Animator.INFINITE)
				.setInterpolator(new AccelerationInterpolator(0.05, 0.04))
				.addTarget(new TimingTargetAdapter() {
					@Override
					public void timingEvent(Animator source, double fraction) {
						to = (int) ((maxVal - minVal) * fraction + minVal);
						repaint();
					}
				})
				.build();


		setPreferredSize(new Dimension(48, 48));
		addHierarchyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		UIUtil.setRenderingHints(g2);

		g2.rotate(ratio, getWidth() / 2.0, getHeight() / 2.0);

		g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2.setColor(fg);

		int minSize = Math.min(getWidth(), getHeight());
		int size = minSize - strokeWidth * 2; //球体大小
		g2.drawArc((getWidth() - size) / 2, (getHeight() - size) / 2, size, size, 0, to);
	}

	public static int getMinVal() {
		return minVal;
	}

	public static int getMaxVal() {
		return maxVal;
	}

	public int getTo() {
		return to;
	}

	public Animator getRotateAnimator() {
		return rotateAnimator;
	}

	public Animator getAngerAnimator() {
		return angerAnimator;
	}

	public Color getFg() {
		return fg;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		long flags = e.getChangeFlags();
		if (flags == HierarchyEvent.SHOWING_CHANGED && (!angerAnimator.isRunning() || !rotateAnimator.isRunning())) {
			angerAnimator.restart();
			rotateAnimator.restart();
		} else if (flags == HierarchyEvent.DISPLAYABILITY_CHANGED && (angerAnimator.isRunning() || rotateAnimator.isRunning())) {
			angerAnimator.cancel();
			rotateAnimator.cancel();
		}
	}
}

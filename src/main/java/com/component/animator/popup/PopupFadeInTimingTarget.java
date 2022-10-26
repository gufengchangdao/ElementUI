package com.component.animator.popup;

import com.component.common.component.BaseComponent;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.lang.Math.abs;

/**
 * 弹窗淡入动画
 *
 * @param <E> 弹窗组件
 */
public class PopupFadeInTimingTarget<E extends BaseComponent> implements TimingTarget {
	private final PopupAnimatorTask<E> task;

	public PopupFadeInTimingTarget(PopupAnimatorTask<E> task) {
		this.task = task;
	}

	@Override
	public void begin(Animator animator) {

	}

	@Override
	public void end(Animator animator) {
		// 淡出。这里动画放在事件前面了
		if (task.getDurationTime() != 0) {
			Animator fadeOut = new Animator.Builder()
					.setDuration(task.getFadeOutTime(), TimeUnit.MILLISECONDS)
					.setStartDelay(task.getDurationTime(), TimeUnit.MILLISECONDS)
					.addTarget(new PopupFadeOutTimingTarget<>(task))
					.build();
			task.setFadeOutAnimator(fadeOut);
			fadeOut.start();
		} else {
			// 从列表中移除task
			task.getTaskList().remove(task);
		}

		// 事件执行淡出动画过程中执行
		Consumer<E> processComponent = task.getProcessComponent();
		if (processComponent != null)
			processComponent.accept(task.getC());

	}

	@Override
	public void repeat(Animator animator) {

	}

	@Override
	public void reverse(Animator animator) {

	}

	@Override
	public void timingEvent(Animator animator, double v) {
		E c = task.getC();
		// 方位
		Point from = task.getFrom();
		Point to = task.getTo();
		if (from != null && to != null)
			c.setLocation((int) ((to.x - from.x) * v + from.x), (int) ((to.y - from.y) * v + from.y));

		// 不透明度
		float beginOpacity = task.getBeginOpacity();
		float endOpacity = task.getToOpacity();
		if (!(abs(beginOpacity - 1) < Float.MIN_VALUE && abs(endOpacity - 1) < Float.MIN_VALUE))
			c.setOpacity((float) ((endOpacity - beginOpacity) * v + beginOpacity));
		c.validate();
	}

	public PopupAnimatorTask<E> getTask() {
		return task;
	}
}

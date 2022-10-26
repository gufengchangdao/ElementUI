package com.component.animator.popup;

import com.component.common.component.BaseComponent;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import java.awt.*;
import java.util.function.Consumer;

import static java.lang.Math.abs;

/**
 * 用于消息提示的淡出
 */
public class PopupFadeOutTimingTarget<E extends BaseComponent> implements TimingTarget {
	private final PopupAnimatorTask<E> task;

	public PopupFadeOutTimingTarget(PopupAnimatorTask<E> task) {
		this.task = task;
	}

	@Override
	public void begin(Animator animator) {

	}

	@Override
	public void end(Animator animator) {
		E c = task.getC();
		Consumer<E> dispose = task.getDispose();
		if (dispose != null) dispose.accept(c);

		// 从面板中移除组件
		task.dispose();

		// 从列表中移除task
		task.getTaskList().remove(task);
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
		Point from = task.getTo();
		Point to = task.getEnd();
		if (from != null && to != null)
			c.setLocation((int) ((to.x - from.x) * v + from.x), (int) ((to.y - from.y) * v + from.y));

		// 不透明度
		float beginOpacity = task.getToOpacity();
		float endOpacity = task.getEndOpacity();
		if (!(abs(beginOpacity - 1) < Float.MIN_VALUE && abs(endOpacity - 1) < Float.MIN_VALUE))
			c.setOpacity((float) ((endOpacity - beginOpacity) * v + beginOpacity));
		c.validate();
	}

	public PopupAnimatorTask<E> getTask() {
		return task;
	}
}

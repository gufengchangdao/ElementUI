package com.component.animator.popup;

import com.component.common.component.BaseComponent;
import org.jdesktop.core.animation.timing.Animator;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 通用弹窗动画组相关信息
 * <p>
 * 默认效果是淡入淡出，位置信息需要自己设置
 */
public class PopupAnimatorTask<E extends BaseComponent> {
	private Animator fadeInAnimator;
	private Animator fadeOutAnimator;
	/** 每个PopupAnimatorGroup维护的一组 task，除非必要，开发者不应该直接操作该列表 */
	private final LinkedList<PopupAnimatorTask<E>> taskList;
	/** 应用动画的组件 */
	private final E c;
	/** 初始位置 */
	private Point from;
	/** 淡入结束位置 */
	private Point to;
	/** 淡出结束位置 */
	private Point end;
	/** 初始不透明度，默认值为0 */
	private float beginOpacity = 0;
	/** 结束不透明度，默认值为1 */
	private float toOpacity = 1;
	/** 淡出结束不透明度，默认为0 */
	private float endOpacity = 0;

	/** 淡入动画时长。默认为400 */
	private int fadeInTime = 400;
	/** 持续时长。默认为3000。为0时表示不会自动关闭 */
	private int durationTime = 3000;
	/** 淡出动画时长。默认为400 */
	private int fadeOutTime = 400;
	/** 组件淡入后执行操作，更准确的说是事件执行淡出动画过程中执行 */
	private Consumer<E> processComponent;
	/** 组件淡出后执行操作。不用在里面移除组件，淡出结束后会为你自动移除 */
	private Consumer<E> dispose;

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList) {
		this.c = c;
		this.taskList = taskList;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, Point from, Point to) {
		this.c = c;
		this.taskList = taskList;
		this.from = from;
		this.to = to;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, Point from, Point to, Point end) {
		this.c = c;
		this.taskList = taskList;
		this.from = from;
		this.to = to;
		this.end = end;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, float beginOpacity, float toOpacity) {
		this.c = c;
		this.taskList = taskList;
		this.beginOpacity = beginOpacity;
		this.toOpacity = toOpacity;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, float beginOpacity, float toOpacity, float endOpacity) {
		this.c = c;
		this.taskList = taskList;
		this.beginOpacity = beginOpacity;
		this.toOpacity = toOpacity;
		this.endOpacity = endOpacity;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, Point from, Point to, float beginOpacity, float toOpacity) {
		this.c = c;
		this.taskList = taskList;
		this.from = from;
		this.to = to;
		this.beginOpacity = beginOpacity;
		this.toOpacity = toOpacity;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, Point from, Point to, Point end,
	                         float beginOpacity, float toOpacity, float endOpacity) {
		this.c = c;
		this.taskList = taskList;
		this.from = from;
		this.to = to;
		this.end = end;
		this.beginOpacity = beginOpacity;
		this.toOpacity = toOpacity;
		this.endOpacity = endOpacity;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, Point from, Point to, float beginOpacity,
	                         float toOpacity, int fadeInTime, int durationTime, int fadeOutTime) {
		this.c = c;
		this.taskList = taskList;
		this.from = from;
		this.to = to;
		this.beginOpacity = beginOpacity;
		this.toOpacity = toOpacity;
		this.fadeInTime = fadeInTime;
		this.durationTime = durationTime;
		this.fadeOutTime = fadeOutTime;
	}

	public PopupAnimatorTask(E c, LinkedList<PopupAnimatorTask<E>> taskList, Point from, Point to,
	                         float beginOpacity, float toOpacity, int fadeInTime, int durationTime, int fadeOutTime,
	                         Consumer<E> processComponent, Consumer<E> dispose) {
		this.c = c;
		this.taskList = taskList;
		this.from = from;
		this.to = to;
		this.beginOpacity = beginOpacity;
		this.toOpacity = toOpacity;
		this.fadeInTime = fadeInTime;
		this.durationTime = durationTime;
		this.fadeOutTime = fadeOutTime;
		this.processComponent = processComponent;
		this.dispose = dispose;
	}

	public void dispose() {
		Dimension size = c.getPreferredSize();
		Container parent = c.getParent();
		if (parent == null) return;
		parent.remove(c);
		parent.repaint(c.getX(), c.getY(), size.width, size.height);
	}

	public E getC() {
		return c;
	}

	public Point getFrom() {
		return from;
	}

	public Point getTo() {
		return to;
	}

	public int getFadeInTime() {
		return fadeInTime;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public int getFadeOutTime() {
		return fadeOutTime;
	}

	public Animator getFadeInAnimator() {
		return fadeInAnimator;
	}

	public PopupAnimatorTask<E> setFadeInAnimator(Animator fadeInAnimator) {
		this.fadeInAnimator = fadeInAnimator;
		return this;
	}

	public Animator getFadeOutAnimator() {
		return fadeOutAnimator;
	}

	public PopupAnimatorTask<E> setFadeOutAnimator(Animator fadeOutAnimator) {
		this.fadeOutAnimator = fadeOutAnimator;
		return this;
	}

	public float getBeginOpacity() {
		return beginOpacity;
	}

	public float getToOpacity() {
		return toOpacity;
	}

	public Consumer<E> getProcessComponent() {
		return processComponent;
	}

	public PopupAnimatorTask<E> setProcessComponent(Consumer<E> processComponent) {
		this.processComponent = processComponent;
		return this;
	}

	public Consumer<E> getDispose() {
		return dispose;
	}

	public PopupAnimatorTask<E> setDispose(Consumer<E> dispose) {
		this.dispose = dispose;
		return this;
	}

	public PopupAnimatorTask<E> setFrom(Point from) {
		this.from = from;
		return this;
	}

	public PopupAnimatorTask<E> setTo(Point to) {
		this.to = to;
		return this;
	}

	public PopupAnimatorTask<E> setBeginOpacity(float beginOpacity) {
		this.beginOpacity = beginOpacity;
		return this;
	}

	public PopupAnimatorTask<E> setToOpacity(float toOpacity) {
		this.toOpacity = toOpacity;
		return this;
	}

	public PopupAnimatorTask<E> setFadeInTime(int fadeInTime) {
		this.fadeInTime = fadeInTime;
		return this;
	}

	public PopupAnimatorTask<E> setDurationTime(int durationTime) {
		this.durationTime = durationTime;
		return this;
	}

	public PopupAnimatorTask<E> setFadeOutTime(int fadeOutTime) {
		this.fadeOutTime = fadeOutTime;
		return this;
	}

	public Point getEnd() {
		return end;
	}

	public PopupAnimatorTask<E> setEnd(Point end) {
		this.end = end;
		return this;
	}

	public float getEndOpacity() {
		return endOpacity;
	}

	public PopupAnimatorTask<E> setEndOpacity(float endOpacity) {
		this.endOpacity = endOpacity;
		return this;
	}

	/** 执行淡出动画，如果动画对象为 null 或正在运行则什么都不做 */
	public void startFadeInAnimator() {
		if (fadeInAnimator != null && !fadeInAnimator.isRunning())
			fadeInAnimator.start();
	}

	/** 执行淡出动画(如果可以的话)，没有开启自动关闭的话直接关闭该弹窗 */
	public void startFadeOutAnimator() {
		if (fadeOutAnimator != null) fadeOutAnimator.cancel();
		if (durationTime != 0) {
			if (fadeInAnimator != null && fadeInAnimator.isRunning()) {
				fadeInAnimator.cancel();
			}
			to = c.getLocation(); //得从当前位置开始

			fadeOutAnimator = new Animator.Builder()
					.setDuration(fadeOutTime, TimeUnit.MILLISECONDS)
					.addTarget(new PopupFadeOutTimingTarget<>(this))
					.build();
			fadeOutAnimator.start();
		} else {
			dispose();
		}
	}

	public LinkedList<PopupAnimatorTask<E>> getTaskList() {
		return taskList;
	}
}

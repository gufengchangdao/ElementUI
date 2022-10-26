package com.component.animator.popup;

import com.component.common.component.BaseComponent;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 通用弹窗动画组
 * <p>
 * 通过传入一个容器，可在该容器上添加任意个弹窗动画
 */
public class PopupAnimatorGroup<E extends BaseComponent> {
	/** 任务队列，存在尚未结束的task，不包含不会自动关闭的弹窗任务。非事件队列线程不要也没必要进行增删操作 */
	// private final CopyOnWriteArrayList<PopupAnimatorTask<E>> taskList = new CopyOnWriteArrayList<>();
	private final LinkedList<PopupAnimatorTask<E>> taskList = new LinkedList<>();

	/** 层级，如果使用的是层次面板，则这个会起作用，使新添加的弹窗不会被已有弹窗覆盖 */
	private transient int layer = 0;
	/** 组件容器，一般是玻璃窗格 */
	private Container container;

	/**
	 * 创建一个task对象，简便task创建
	 *
	 * @see PopupAnimatorTask#PopupAnimatorTask(BaseComponent, LinkedList, Point, Point)
	 */
	public PopupAnimatorTask<E> createTask(E c, Point from, Point to) {
		return new PopupAnimatorTask<>(c, taskList, from, to);
	}

	/**
	 * @param container 弹窗组件的容器，一般是玻璃窗格。注意，这个容器应该是 null 布局或层次面板
	 */
	public PopupAnimatorGroup(Container container) {
		this.container = container;
	}

	/**
	 * 将组件添加到容器中并开始一个弹窗动画
	 *
	 * @param task 弹窗动画需要的参数信息
	 */
	public void startAnimator(PopupAnimatorTask<E> task) {
		if (task == null)
			throw new NullPointerException("必须先为 task 赋初始值，才能知道应该完成什么动画");

		E c = task.getC();
		container.add(c, (Integer) ((++layer) % Integer.MAX_VALUE));
		Dimension size = c.getPreferredSize();
		c.setSize(size.width, size.height);

		// 提前构建好
		Animator fadeIn = new Animator.Builder()
				.setDuration(task.getFadeInTime(), TimeUnit.MILLISECONDS)
				.setInterpolator(new AccelerationInterpolator(0.1, 0.6))
				.addTarget(new PopupFadeInTimingTarget<>(task))
				.build();
		task.setFadeInAnimator(fadeIn);

		fadeIn.start();
		taskList.add(task);
	}

	public List<PopupAnimatorTask<E>> getTaskList() {
		return taskList;
	}

	public Container getContainer() {
		return container;
	}

	public int getLayer() {
		return layer;
	}

	/**
	 * 设置新的容器对象。原有任务队列会清空，也许我不该提供这个方法
	 *
	 * @param container 新的容器对象
	 */
	public void setContainer(Container container) {
		this.container = container;
		taskList.clear();
		layer = 0;
	}
}

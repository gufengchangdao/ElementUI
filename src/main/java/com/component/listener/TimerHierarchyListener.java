package com.component.listener;

import javax.swing.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

/**
 * 为定时器添加监听器，组件当前显示在框架时启动动画，不可显示时(包含被移除、被释放)停止动画。内部使用isShowing()进行判断
 */
public class TimerHierarchyListener implements HierarchyListener {
	Timer timer;

	public TimerHierarchyListener(Timer timer) {
		this.timer = timer;
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
			if (e.getComponent().isShowing()) {
				System.out.println("开启");
			} else {
				System.out.println("关闭");
			}
		}
	}
}

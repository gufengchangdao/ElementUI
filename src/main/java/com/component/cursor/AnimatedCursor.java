package com.component.cursor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 为鼠标悬停事件添加鼠标动画
 */
public class AnimatedCursor implements ActionListener, MouseListener {
	/** 当前第几帧 */
	private int counter;
	/** 具有动画的组件 */
	private final Component comp;
	/** 每帧对应的鼠标图片 */
	private final List<Cursor> frames;
	private Timer animator;

	/**
	 * 100毫秒切换一次
	 *
	 * @param comp   要添加鼠标动画的组件
	 * @param images 鼠标动画用到的图片列表
	 */
	public AnimatedCursor(Component comp, List<Image> images) {
		this(comp, images, 100);
	}

	/**
	 * @param comp   要添加鼠标动画的组件
	 * @param images 鼠标动画用到的图片列表
	 * @param delay  多少毫秒切换下一张
	 */
	public AnimatedCursor(Component comp, List<Image> images, int delay) {
		this.comp = comp;

		Point pt = new Point();
		Toolkit tk = comp.getToolkit();
		this.frames = images.stream()
				.map(s -> tk.createCustomCursor(s, pt, s.toString()))
				.collect(Collectors.toList());

		animator = new Timer(delay, this);
		// 移除组件时停止动画
		// comp.addHierarchyListener(e -> {
		// 	boolean b = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
		// 	if (b && !e.getComponent().isDisplayable()) {
		// 		animator.stop();
		// 	}
		// });
		// 动画的播放和停止由鼠标事件决定
		comp.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comp.setCursor(frames.get(counter));
		counter = (counter + 1) % frames.size();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		animator.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		animator.stop();
	}

	public int getCounter() {
		return counter;
	}

	public Component getComp() {
		return comp;
	}

	public List<Cursor> getFrames() {
		return frames;
	}

	public Timer getAnimator() {
		return animator;
	}
}

package com.component.others.tooltip;

import com.component.common.SwingPosition;
import com.component.common.component.AngleComponent;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

/**
 * 文字提示
 * <p>
 * 常用于展示鼠标 hover 时的提示信息。
 */
public class TooltipLabel extends JLabel implements MouseListener, TimingTarget {
	/** 弹窗工厂，为null时自动创建，不过可以通过传入实现多个对象共享一个factory */
	private PopupFactory factory;
	private Popup popup;
	/** 动画播放过程中判断淡入淡出的变量 */
	private boolean isShouldHidePopup = false;
	/** 弹窗的位置，用于设置文本时新建弹窗定位使用 */
	private Point p;

	/** 要添加文字提示的组件 */
	private JComponent c;
	/** 切角的位置 */
	private SwingPosition position;
	/** 切角组件 */
	private AngleComponent tooltipC;
	/** 淡入淡出动画 */
	private Animator animator;

	/**
	 * @see #TooltipLabel(JComponent, String, PopupFactory, SwingPosition, Color)
	 */
	public TooltipLabel(JComponent c, String text, PopupFactory factory) {
		this(c, text, factory, SwingPosition.TOP_LEFT, null);
	}

	/**
	 * @see #TooltipLabel(JComponent, String, PopupFactory, SwingPosition, Color)
	 */
	public TooltipLabel(JComponent c, String text) {
		this(c, text, new PopupFactory(), SwingPosition.TOP_LEFT, null);
	}

	/**
	 * @see #TooltipLabel(JComponent, String, PopupFactory, SwingPosition, Color)
	 */
	public TooltipLabel(JComponent c, String text, SwingPosition position, Color color) {
		this(c, text, new PopupFactory(), position, color);
	}

	/**
	 * @param c        要添加文字提示的组件
	 * @param text     提示文本
	 * @param factory  弹窗工厂，为null时自动创建，不过可以通过传入实现多个对象共享一个factory
	 * @param position 切角的位置
	 * @param color    切角和背景色
	 */
	public TooltipLabel(JComponent c, String text, PopupFactory factory, SwingPosition position, Color color) {
		super(text);
		this.factory = factory;
		this.c = c;
		this.position = position;

		c.addMouseListener(this);
		setOpaque(true);
		if (color == null)
			color = UIManager.getColor("ToolTip.background");
		setBackground(color);

		tooltipC = new AngleComponent(this, position, getBackground(), 5, 2);

		animator = new Animator.Builder()
				.setDuration(600, TimeUnit.MILLISECONDS)
				.addTarget(this)
				.build();
	}

	public void removeTooltip() {
		removeMouseListener(this);
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
	public void setText(String text) {
		super.setText(text);
		// 这个方法弹窗不会立刻刷新，就只要采用更换一个新的弹窗的方式了
		// if (tooltipC != null){ //子组件大小改变，父组件大小也应该改变
		// 	tooltipC.setPreferredSize(getPreferredSize());
		// }

		if (popup != null) {
			popup.hide();
			tooltipC = new AngleComponent(this, position, getBackground(), 5, 2);
			popup = factory.getPopup(c, tooltipC, p.x, p.y + 13);
			popup.show();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (popup != null) return;
		if (animator.isRunning()) animator.stop();

		p = e.getLocationOnScreen();
		Point l = e.getLocationOnScreen();
		// TODO 给该组件添加过渡动画
		popup = factory.getPopup(c, tooltipC, l.x, l.y + 13);
		popup.show();
		animator.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (popup == null) return;

		if (animator.isRunning()) animator.reverseNow();
		else {
			isShouldHidePopup = true;
			animator.restart();
		}
	}

	@Override
	public void begin(Animator animator) {

	}

	@Override
	public void end(Animator animator) {
		if (isShouldHidePopup) {
			popup.hide();
			popup = null;
			isShouldHidePopup = false;
		}
	}

	@Override
	public void repeat(Animator animator) {

	}

	@Override
	public void reverse(Animator animator) {

	}

	@Override
	public void timingEvent(Animator animator, double v) {
		tooltipC.setOpacity(isShouldHidePopup ? (float) (1 - v) : (float) v);
	}
}

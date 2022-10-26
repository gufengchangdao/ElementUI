package com.component.others.popconfirm;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.common.SwingPosition;
import com.component.common.component.AngleComponent;
import com.component.common.template.Y2CNCCComponent;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

/**
 * 气泡确认框
 * <p>
 * 点击元素，弹出气泡确认框。
 */
public class PopConfirm extends Y2CNCCComponent<JLabel, JButton> implements MouseListener, TimingTarget {
	private PopupFactory factory = new PopupFactory();
	private Popup popup;
	private AngleComponent pop;
	private Animator animator;
	/** 动画播放过程中判断淡入淡出的变量 */
	private boolean isShouldHidePopup = false;

	private JComponent target;

	public PopConfirm(JComponent target, JLabel label) {
		this.target = target;
		Insets insets = new Insets(3, 7, 3, 7);
		JButton cancel = ButtonFactory.createPlainButton("取消", ColorUtil.INFO);
		cancel.setMargin(insets);
		JButton confirm = ButtonFactory.createDefaultButton("确定", ColorUtil.PRIMARY);
		confirm.setMargin(insets);

		init(label, cancel, confirm);
	}

	public PopConfirm(JComponent target, JLabel label, JButton cancel, JButton confirm) {
		this.target = target;
		init(label, cancel, confirm);
	}

	public void init(JLabel label, JButton cancel, JButton confirm) {
		setProperty(label, cancel, confirm, 10);
		init();

		// 背景
		setBackground(Color.WHITE);
		// 鼠标监听
		target.addMouseListener(this);
		// 内边距
		setInsets(new Insets(12, 12, 12, 12));
		// 对话框
		pop = new AngleComponent(this, SwingPosition.TOP, getBackground());

		// 动画
		animator = new Animator.Builder()
				.setDuration(300, TimeUnit.MILLISECONDS)
				.addTarget(this)
				.build();

		setOpaque(true);
		setBackground(Color.WHITE);
		setRadius(6);
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
		if (isShouldHidePopup) pop.setOpacity((float) (1 - v));
		else pop.setOpacity((float) v);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (popup != null && !isShouldHidePopup) {
			//正在淡入或已经结束
			if (animator.isRunning()) animator.stop();
			isShouldHidePopup = true;
			animator.restart(); //即便是stop后调用start也有抛异常的可能
		} else {
			isShouldHidePopup = false;
			Point l = target.getLocationOnScreen();
			Dimension size = target.getPreferredSize();
			Dimension s = getPreferredSize();
			popup = factory.getPopup(target, pop, l.x + (size.width - s.width) / 2,
					l.y + size.height + 5);
			popup.show();
			animator.restart();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public PopupFactory getFactory() {
		return factory;
	}

	public Animator getAnimator() {
		return animator;
	}

	public boolean isShouldHidePopup() {
		return isShouldHidePopup;
	}

	public JComponent getTarget() {
		return target;
	}

	public JButton getConfirmButton() {
		return getBottomRightC();
	}

	public JButton getCancelButton() {
		return getBottomLeftC();
	}

	public JLabel getLabel() {
		return getTopC();
	}
}

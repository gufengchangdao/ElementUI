package com.component.others.carousel;

import com.component.basic.color.ColorUtil;
import org.jdesktop.animation.transitions.EffectsManager;
import org.jdesktop.animation.transitions.ScreenTransition;
import org.jdesktop.animation.transitions.TransitionTarget;
import org.jdesktop.animation.transitions.effects.CompositeEffect;
import org.jdesktop.animation.transitions.effects.FadeIn;
import org.jdesktop.animation.transitions.effects.MoveIn;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.lang.Math.max;

/**
 * 水平走马灯
 * <p>
 * 在有限空间内，循环播放同一类型的图片、文字等内容
 */
public class HorizontalCarouselPanel<E extends JComponent> extends JLayeredPane
		implements TimingTarget, TransitionTarget, MouseListener {
	public static final int MOUSE_CLICKED = MouseEvent.MOUSE_CLICKED;
	public static final int MOUSE_ENTERED = MouseEvent.MOUSE_ENTERED;
	public int triggerMode;

	private List<CarouselPanelGroup> coms = new ArrayList<>();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private int newIndex;
	private int currentDisplayIndex;
	private long duration;
	private Consumer<E> eventProcess;

	// 动画
	private Animator slideAnimator;
	private ScreenTransition transition;
	// 此类管理应用程序的效果缓存
	private EffectsManager effectsManager;
	private CompositeEffect in;

	@Override
	public void setupNextScreen() {
		display(newIndex);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (eventProcess != null)
			eventProcess.accept(coms.get(currentDisplayIndex).c);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		slideAnimator.pause();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		slideAnimator.resume();
	}

	/**
	 * 走马灯最小单位
	 */
	private class CarouselPanelGroup implements ActionListener, MouseListener {
		public static final int BUTTON_WIDTH = 50;
		public static final int BUTTON_HEIGHT = 5;
		/** 轮播的组件 */
		E c;
		/** 该组件对应的按钮 */
		JToggleButton b;

		public CarouselPanelGroup(E c) {
			this.c = c;
			b = new JToggleButton();
			b.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
			b.setOpaque(false);
			b.setFocusPainted(false);
			b.setBorderPainted(false);
			b.setBackground(ColorUtil.changeAlpha(ColorUtil.INFO, .4f));
		}

		public E getC() {
			return c;
		}

		public void setC(E c) {
			this.c = c;
		}

		public JToggleButton getB() {
			return b;
		}

		public void setB(JToggleButton b) {
			this.b = b;
		}

		@Override
		public String toString() {
			return "CarouselPanelGroup{" +
					"c=" + c +
					", b=" + b +
					'}';
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			newIndex = coms.indexOf(this);
			// 设置要更换组件的过渡
			effectsManager.clearAllEffects();
			effectsManager.setEffect(c, in, EffectsManager.TransitionType.APPEARING);

			transition.start();
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
			newIndex = coms.indexOf(this);
			// 设置要更换组件的过渡
			effectsManager.clearAllEffects();
			effectsManager.setEffect(c, in, EffectsManager.TransitionType.APPEARING);

			transition.start();
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	public HorizontalCarouselPanel(List<E> coms, Consumer<E> eventProcess) {
		this(coms, MOUSE_ENTERED, 2000, eventProcess);
	}

	public HorizontalCarouselPanel(List<E> coms, int triggerMode,
	                               Consumer<E> eventProcess) {
		this(coms, triggerMode, 2000, eventProcess);
	}

	/**
	 * @param coms         组件列表
	 * @param triggerMode  指示器触发方式，可选 MOUSE_ENTERED 或 MOUSE_CLICKED
	 * @param duration     每个组件展示的时间，单位为毫秒
	 * @param eventProcess 组件点击的事件处理
	 */
	public HorizontalCarouselPanel(List<E> coms, int triggerMode, int duration,
	                               Consumer<E> eventProcess) {
		this.triggerMode = triggerMode;
		this.duration = duration;
		this.eventProcess = eventProcess;

		Dimension maxSize = new Dimension(0, 0);
		for (E c : coms) {
			Dimension s = c.getPreferredSize();
			maxSize.width = max(maxSize.width, s.width);
			maxSize.height = max(maxSize.height, s.height);
			CarouselPanelGroup group = new CarouselPanelGroup(c);
			this.coms.add(group);
			buttonGroup.add(group.b);
		}
		setPreferredSize(maxSize);

		// 传入面板
		Dimension size = coms.get(0).getPreferredSize();
		coms.get(0).setBounds((maxSize.width - size.width) / 2, (maxSize.height - size.height) / 2,
				size.width, size.height);
		add(coms.get(0), (Integer) 0);

		// 默认第一个组件
		display(0);

		// 根据宽度决定是否显示按钮
		if (coms.size() != 0 && maxSize.width > coms.size() * (CarouselPanelGroup.BUTTON_WIDTH + 5) + 10) {
			JPanel buttonPanel = new JPanel();
			for (CarouselPanelGroup g : this.coms) {
				buttonPanel.add(g.b);
				// 设置监听事件
				if (triggerMode == MOUSE_CLICKED) g.b.addActionListener(g);
				else g.b.addMouseListener(g);
			}
			Dimension s = buttonPanel.getPreferredSize();
			buttonPanel.setBounds((maxSize.width - s.width) / 2, maxSize.height - s.height - 5,
					s.width, s.height);
			buttonPanel.setOpaque(false);
			add(buttonPanel, (Integer) 1);

			// 默认选中第一个按钮
			this.coms.get(0).b.setSelected(true);
		}

		// 动画
		setAnimation();

		// 监听事件
		setListener();
	}

	private void setAnimation() {
		// 切换动画，过渡效果
		Animator switchAnimator = new Animator.Builder()
				.setDuration(500, TimeUnit.MILLISECONDS)
				.build();

		effectsManager = new EffectsManager();

		int width = getPreferredSize().width;
		MoveIn moveIn = new MoveIn(width, 0);
		FadeIn fadeIn = new FadeIn();
		// 此效果结合了一个或多个子效果，以创建更复杂和有趣的效果。
		in = new CompositeEffect();
		in.addEffect(moveIn);
		in.addEffect(fadeIn);
		// 淡出效果有些异常
		// MoveOut moveOut = new MoveOut(-width, 0);
		// out = new CompositeEffect(moveOut);

		transition = new ScreenTransition.Builder(this, this)
				.setAnimator(switchAnimator)
				.setEffectsManager(effectsManager)
				.build();

		// 轮播动画
		slideAnimator = new Animator.Builder()
				// .setDuration(coms.size() * 1500L, TimeUnit.MILLISECONDS) //一周期多少个label就多少秒
				.setDuration(coms.size() * duration, TimeUnit.MILLISECONDS) //一周期多少个label就多少秒
				.setRepeatBehavior(Animator.RepeatBehavior.LOOP)
				.setRepeatCount(-1)
				.addTarget(this)
				.build();
		slideAnimator.start();
	}

	private void setListener() {
		// 悬停暂停轮播
		addMouseListener(this);
	}

	/**
	 * 展示索引为 index 的组件
	 *
	 * @param index 组件列表的索引值
	 */
	public void display(int index) {
		coms.get(currentDisplayIndex).b.setSelected(false);
		remove(coms.get(currentDisplayIndex).c);

		E c = coms.get(index).c;
		Dimension s = getPreferredSize();
		Dimension s2 = c.getPreferredSize();
		c.setBounds((s.width - s2.width) / 2, (s.height - s2.height) / 2, s2.width, s2.height);
		add(c, (Integer) 0);
		currentDisplayIndex = index;
		coms.get(index).b.setSelected(true);
	}

	/**
	 * 展示组件 c
	 */
	public void display(E c) {
		coms.get(currentDisplayIndex).b.setSelected(false);
		remove(coms.get(currentDisplayIndex).c);

		Dimension s = getPreferredSize();
		Dimension s2 = c.getPreferredSize();
		c.setBounds((s.width - s2.width) / 2, (s.height - s2.height) / 2, s2.width, s2.height);
		add(c, (Integer) 0);
		int index = 0;
		for (int i = 0; i < coms.size(); i++)
			if (coms.get(i).c == c) {
				index = i;
				break;
			}
		currentDisplayIndex = index;
		coms.get(index).b.setSelected(true);
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
		double frame = 1.0 / coms.size();
		// 避免大量无意义的过渡
		if (currentDisplayIndex == (int) (v / frame)) return;
		// 跳转过的，动画暂停轮播一会儿
		if ((int) (v / frame) != (currentDisplayIndex + 1) % coms.size()) return;
		newIndex = (int) (v / frame);

		// 设置要更换组件的过渡
		effectsManager.clearAllEffects();
		effectsManager.setEffect(coms.get(newIndex).c, in, EffectsManager.TransitionType.APPEARING);

		transition.start();
	}

	public int getTriggerMode() {
		return triggerMode;
	}

	public List<CarouselPanelGroup> getComs() {
		return coms;
	}

	public int getCurrentDisplayIndex() {
		return currentDisplayIndex;
	}

	public long getDuration() {
		return duration;
	}

	public Consumer<E> getEventProcess() {
		return eventProcess;
	}

	public void setEventProcess(Consumer<E> eventProcess) {
		this.eventProcess = eventProcess;
	}
}

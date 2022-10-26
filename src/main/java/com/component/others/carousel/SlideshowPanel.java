package com.component.others.carousel;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import org.jdesktop.animation.transitions.EffectsManager;
import org.jdesktop.animation.transitions.ScreenTransition;
import org.jdesktop.animation.transitions.TransitionTarget;
import org.jdesktop.animation.transitions.effects.MoveIn;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 走马灯面板
 * <p>
 * 存在一些小问题：
 * - 点击按钮后跳转到指定label，但是后续动画不是从该label开始，这是Animator不能设置帧数导致的
 */
public class SlideshowPanel extends JPanel implements TimingTarget, TransitionTarget, MouseListener {
	private int width;
	private int height;
	// 默认按钮大小
	private int buttonWidth = 50;
	private int buttonHeight = 5;
	/**
	 * 每张label停留的时间，单位为毫秒
	 */
	private long duration = 1500;
	/**
	 * 传入的label数据
	 */
	private List<? extends JComponent> coms;
	private JButton[] buttons;
	private JPanel componentsPanel;
	private int currentLabelIndex;

	private Animator slideAnimator;
	private ScreenTransition transition;
	// 此类管理应用程序的效果缓存
	private EffectsManager effectsManager;
	/**
	 * label点击事件，参数为所点击的label索引
	 */
	private Consumer<Integer> eventProcess;

	public SlideshowPanel(int width, int height, long duration, List<? extends JComponent> coms, Consumer<Integer> eventProcess) {
		this(width, height, 50, 5, duration, coms, eventProcess);
	}

	/**
	 * @param width        宽
	 * @param height       高
	 * @param buttonWidth  按钮宽度
	 * @param buttonHeight 按钮高度
	 * @param duration     每张展示时间，单位为毫秒
	 * @param coms         组件列表
	 * @param eventProcess 组件点击事件
	 */
	public SlideshowPanel(int width, int height, int buttonWidth, int buttonHeight,
	                      long duration, List<? extends JComponent> coms, Consumer<Integer> eventProcess) {
		this.width = width;
		this.height = height;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
		this.duration = duration;
		this.coms = coms;
		this.eventProcess = eventProcess;

		init();
	}

	private void init() {
		if (coms.size() == 0)
			throw new RuntimeException("轮播的组件至少有一个");

		if (width > 0 && height > 0) {
			setPreferredSize(new Dimension(width, height));
		}
		setLayout(new BorderLayout());

		// 轮播图
		currentLabelIndex = 0;

		componentsPanel = new JPanel(new BorderLayout());
		componentsPanel.add(coms.get(currentLabelIndex));
		// 添加透明组件并覆盖在要监听的组件上，透明组件的作用是监听鼠标事件
		add(componentsPanel);

		// 底部按钮
		JPanel buttonsPanel = new JPanel();
		buttons = new JButton[coms.size()];
		for (int i = 0; i < coms.size(); i++) {
			int finalI = i;
			buttons[i] = ButtonFactory.createNoTextButton(buttonWidth, buttonHeight, ColorUtil.SECONDARY_TEXT);
			buttons[i].addActionListener(e -> {
				// 因为Animator不支持设置帧数，这里采用暂停动画1秒的方式解决
				// 播放第 finalI + 1 张label后暂停1秒再开始轮播
				timingEvent(slideAnimator, 1.0 / coms.size() * finalI);
				slideAnimator.pause();
				new Timer(1000, e1 -> slideAnimator.resume());
			});
			buttonsPanel.add(buttons[i]);
		}
		// 初始化第一个按钮为高亮
		buttons[currentLabelIndex].setBackground(ColorUtil.PRIMARY_TEXT);
		add(buttonsPanel, BorderLayout.SOUTH);

		// 轮播动画
		setAnimation();

		// 添加鼠标事件
		setKeyListener();
	}

	private void setKeyListener() {
		// 鼠标悬停
		addMouseListener(this);

		// 鼠标点击，触发点击事件
		componentsPanel.addMouseListener(this);
	}

	private void setAnimation() {
		// 切换动画，过渡效果
		Animator switchAnimator = new Animator.Builder()
				.setDuration(500, TimeUnit.MILLISECONDS)
				.build();

		effectsManager = new EffectsManager();
		MoveIn moveIn = new MoveIn(-width, 0);
		coms.forEach(jLabel -> effectsManager.setEffect(jLabel, moveIn, EffectsManager.TransitionType.APPEARING));

		transition = new ScreenTransition.Builder(componentsPanel, this)
				.setAnimator(switchAnimator)
				.setEffectsManager(effectsManager)
				.build();

		// 轮播动画
		slideAnimator = new Animator.Builder()
				.setDuration(coms.size() * duration, TimeUnit.MILLISECONDS) //一周期多少个label就多少秒
				.setRepeatBehavior(Animator.RepeatBehavior.LOOP)
				.setRepeatCount(-1)
				.addTarget(this)
				.build();
		slideAnimator.start();
	}

	public void stopAnimation() {
		slideAnimator.stop();
	}

	@Override
	public void setupNextScreen() {
		componentsPanel.removeAll();
		componentsPanel.add(coms.get(currentLabelIndex));
		componentsPanel.repaint();
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
		if (currentLabelIndex == (int) (v / frame))
			return;
		buttons[currentLabelIndex].setBackground(ColorUtil.SECONDARY_TEXT); //默认灰色
		currentLabelIndex = (int) (v / frame);
		transition.start();

		buttons[currentLabelIndex].setBackground(ColorUtil.PRIMARY_TEXT); //高亮
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 这里设置只有点击按钮上方部分才能触发点击事件
		if (e.getSource() != componentsPanel) return;
		if (eventProcess != null)
			eventProcess.accept(currentLabelIndex);
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

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public int getButtonWidth() {
		return buttonWidth;
	}

	public int getButtonHeight() {
		return buttonHeight;
	}

	public long getDuration() {
		return duration;
	}

	public List<? extends JComponent> getComs() {
		return coms;
	}

	public int getCurrentLabelIndex() {
		return currentLabelIndex;
	}

	public Consumer<Integer> getEventProcess() {
		return eventProcess;
	}

	public void setEventProcess(Consumer<Integer> eventProcess) {
		this.eventProcess = eventProcess;
	}
}

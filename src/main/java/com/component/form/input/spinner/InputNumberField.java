package com.component.form.input.spinner;

import com.component.common.component.BaseComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 整数输入框，左右排列，只支持整数
 * <p>
 * 可以规定最大值和最小值，超过最大值时会禁止数继续增加，小于最小值并且输入框失去焦点时会变为最小值
 */
public class InputNumberField extends BaseComponent implements KeyListener, FocusListener {
	private int currentNum;
	private int maxNum;
	private int minNum;
	private JTextField textField;
	private JButton subButton;
	private JButton addButton;
	/** 默认第一种样式 */
	private InputStyle style = InputStyle.STYLE1;
	/** 步长 */
	private int stepSize = 1;

	public enum InputStyle {
		/** 输入框在坐标，加减图标在右边且上下分布 */
		STYLE1,
		/** 从左到右依次是减、输入框、加 */
		STYLE2;
	}

	/**
	 * 初始值为0，不规定最大值和最小值的数字输入框
	 */
	public InputNumberField() {
		this(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * 初始值为最小值的数字输入框
	 *
	 * @param minNum 最小值
	 * @param maxNum 最大值
	 */
	public InputNumberField(int minNum, int maxNum) {
		this(minNum, minNum, maxNum);
	}

	/**
	 * @param originNum 初始值
	 * @param minNum    最小值
	 * @param maxNum    最大值
	 */
	public InputNumberField(int originNum, int minNum, int maxNum) {
		if (originNum < minNum) throw new IllegalArgumentException("初始值不不能低于最小值");
		currentNum = originNum;
		this.maxNum = maxNum;
		this.minNum = minNum;

		init();
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension size = textField.getPreferredSize();
		return new Dimension(size.width + size.height * 2, size.height);
	}

	private void init() {
		textField = new JTextField(currentNum + "");
		setLayout(null);

		addButton = new JButton("");
		subButton = new JButton("");

		// 根据所选样式布局
		switchStyle(style);
		Dimension size = textField.getPreferredSize();
		setPreferredSize(new Dimension(size.width + size.height * 2, size.height));

		add(textField);
		add(addButton);
		add(subButton);

		addListener();
	}

	private void style1() {
		// 图标是白色的，在白色为主色调的界面下看不见，这里不用
		// addButton = new JButton(new ImageIcon(ResourcesUtil.getImage(ResourcesUtil.Image.ADD_DIG)));
		// subButton = new JButton(new ImageIcon(ResourcesUtil.getImage(ResourcesUtil.Image.SUB_DIG)));
		addButton.setText("∧");
		subButton.setText("∨");

		Dimension size = textField.getPreferredSize();
		textField.setBounds(0, 0, size.width + size.height, size.height);
		addButton.setBounds(size.width + size.height, 0, size.height, size.height / 2);
		subButton.setBounds(size.width + size.height, size.height / 2, size.height, size.height / 2);
	}

	private void style2() {
		addButton.setText("+");
		subButton.setText("-");

		Dimension size = textField.getPreferredSize();
		subButton.setBounds(0, 0, size.height, size.height);
		textField.setBounds(size.height, 0, size.width, size.height);
		addButton.setBounds(size.width + size.height, 0, size.height, size.height);
	}

	public void switchStyle(InputStyle style) {
		this.style = style;
		if (style == InputStyle.STYLE2) {
			style2();
		} else {
			style1();
		}
		repaint();
	}

	private void addListener() {
		addButton.addActionListener(e -> addOne());
		subButton.addActionListener(e -> subOne());

		textField.addKeyListener(this);
		textField.addFocusListener(this);
	}

	public void addOne() {
		if (currentNum + stepSize <= maxNum) {
			currentNum += stepSize;
			textField.setText(currentNum + "");
		}
	}

	public void subOne() {
		if (currentNum - stepSize >= minNum) {
			currentNum -= stepSize;
			textField.setText(currentNum + "");
		}
	}

	public int getNum() {
		return currentNum;
	}

	public void setNum(int newNum) {
		currentNum = newNum;
		textField.setText(newNum + "");
	}

	// 值应与getNum获取到的结果是一样的
	public String getText() {
		return textField.getText();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (!(key >= '0' && key <= '9') && key != '-') e.consume(); // 非数字输入不接受
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int newNum = 0;
		String text = textField.getText();
		if ("".equals(text) || "-".equals(text)) newNum = 0;
		else {
			try {
				newNum = Integer.parseInt(text);
			} catch (NumberFormatException ex) {
				textField.setText("0"); //一次输入超过int
			}
		}

		if (newNum > maxNum) textField.setText(currentNum + "");
		else currentNum = newNum;
	}

	@Override
	public void focusGained(FocusEvent e) {
		textField.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (currentNum < minNum) setNum(minNum);
	}

	public int getMaxNum() {
		return maxNum;
	}

	public int getMinNum() {
		return minNum;
	}

	public InputStyle getStyle() {
		return style;
	}

	public int getStepSize() {
		return stepSize;
	}

	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}
}

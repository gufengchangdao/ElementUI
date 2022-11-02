package com.component.form.input.filter;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * 文本组件整数验证器，将要失去焦点时验证
 */
public class IntegerInputVerifier extends InputVerifier {
	private long min;
	private long max;

	/**
	 * @param min 允许的最小值
	 * @param max 允许的最大值
	 */
	public IntegerInputVerifier(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean verify(JComponent c) {
		boolean verified = false;
		if (c instanceof JTextComponent) {
			String txt = ((JTextComponent) c).getText();
			if (txt.isEmpty()) {
				return true;
			}
			try {
				int iv = Integer.parseInt(txt);
				verified = (iv >= min && iv <= max);
			} catch (NumberFormatException ex) {
				// 提示音
				UIManager.getLookAndFeel().provideErrorFeedback(c);
			}
		}
		return verified;
	}

	public long getMax() {
		return max;
	}

	public long getMin() {
		return min;
	}
}

package com.component.basic.button;

import com.component.util.SwingTestUtil;

import java.awt.*;
import java.util.Arrays;

public class RadioButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			RadioButton radioButton = new RadioButton(Arrays.asList("上海", "北京", "广州", "深圳"));
			radioButton.setRadio(false);
			SwingTestUtil.test(radioButton);
		});
	}
}

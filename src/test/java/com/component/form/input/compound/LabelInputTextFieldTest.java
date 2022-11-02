package com.component.form.input.compound;

import com.component.util.SwingTestUtil;

import java.awt.*;

public class LabelInputTextFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			LabelInputTextField field = new LabelInputTextField("你好");
			field.setPreferredSize(new Dimension(200, 200));
			SwingTestUtil.test(field);
		});
	}
}

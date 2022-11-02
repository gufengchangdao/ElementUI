package com.component.form.input.compound;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class LabelIntegerInputFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			LabelIntegerInputField inputField = new LabelIntegerInputField("原价原价");
			JButton button = new JButton("获取");
			button.addActionListener(e -> System.out.println(inputField.getNum()));
			SwingTestUtil.test(inputField, button);
		});
	}
}

package com.component.form.input.spinner;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class InputNumberFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			InputNumberField inputNumberField = new InputNumberField(10, 999);

			JButton button1 = new JButton("获取");
			button1.addActionListener(e -> System.out.println(inputNumberField.getNum()));
			JButton button2 = new JButton("切换");
			button2.addActionListener(e -> inputNumberField.switchStyle(InputNumberField.InputStyle.STYLE2));

			SwingTestUtil.test(inputNumberField, button1, button2);
		});
	}
}

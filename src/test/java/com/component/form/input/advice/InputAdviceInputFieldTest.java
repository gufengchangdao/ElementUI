package com.component.form.input.advice;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class InputAdviceInputFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			// ArrayList<String> list = new ArrayList<>();
			Vector<String> vector = new Vector<>();
			vector.add("香蕉");
			vector.add("苹果");
			vector.add("艾希");
			InputAdviceInputField<String> field = new InputAdviceInputField<>(10, vector);
			// field.setPreferredSize(new Dimension(100,40));
			// vector.remove(0);
			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				System.out.println(field.getText());
				System.out.println(field.getSelectedItem());
			});
			SwingTestUtil.test(field, button);
		});
	}
}

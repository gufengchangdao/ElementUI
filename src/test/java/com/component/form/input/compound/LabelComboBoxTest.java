package com.component.form.input.compound;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class LabelComboBoxTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			LabelComboBox<String> box = new LabelComboBox<>("￥原价", new String[]{"测试一", "测试二", "测试三"});
			JButton button = new JButton("获取");
			button.addActionListener(e -> System.out.println(box.getSelected()));
			SwingTestUtil.test(box, button);
		});
	}
}

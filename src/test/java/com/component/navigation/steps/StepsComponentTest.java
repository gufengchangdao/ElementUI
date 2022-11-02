package com.component.navigation.steps;

import com.component.basic.color.ColorUtil;
import com.component.svg.icon.regular.XCircleSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class StepsComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			// StepsComponent c = new StepsComponent(List.of("步骤1", "步骤2", "步骤3", "步骤4", "步骤5"),
			// 		// XCircleSvg.class,
			// 		XCircleSvg.class, 2, ColorUtil.SUCCESS, 70);
			StepsComponent c = new StepsComponent(Arrays.asList("步骤1", "步骤2", "步骤3", "步骤4", "步骤5"),
					XCircleSvg.class,
					Arrays.asList("描述一", "描述二", "描述三", "描述四", "描述五"),
					2, ColorUtil.SUCCESS, 70, true);

			JButton b1 = new JButton("加一");
			JButton b2 = new JButton("减一");
			JButton b3 = new JButton("修改为3");
			JButton b4 = new JButton("更换列表");
			b1.addActionListener(e -> c.addStep());
			b2.addActionListener(e -> c.reduceStep());
			b3.addActionListener(e -> c.setStep(2));
			b4.addActionListener(e -> {
				c.setItems(c.getItems().subList(0, 2));
				SwingTestUtil.getFrame().getContentPane().validate();
			});
			SwingTestUtil.test(c, b1, b2, b3, b4);
		});
	}

}

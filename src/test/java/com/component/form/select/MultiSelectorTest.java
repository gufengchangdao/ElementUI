package com.component.form.select;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MultiSelectorTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			Vector<String> vector = new Vector<>();
			vector.add("香蕉");
			vector.add("苹果");
			vector.add("艾希");
			MultiSelector field = new MultiSelector(vector);
			// field.setmSize(new Dimension(100, 40));
			// vector.remove(0);
			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				System.out.println(field.getSelectItem());
			});
			SwingTestUtil.test(field, button);
		});
	}
}

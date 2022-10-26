package com.component.basic.layout;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class ColumnsLayoutTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			ColumnsLayout layout = new ColumnsLayout(240, 5, 5);
			JPanel panel = new JPanel(layout);
			panel.add(new JButton(" "), (Integer) 10);
			panel.add(new JButton(" "), (Integer) 13);
			panel.add(new JButton(" "), (Integer) 2);
			panel.add(new JButton(" "), (Integer) 24);
			panel.add(new JButton(" "), (Integer) 10);
			panel.add(new JButton(" "), (Integer) 12);
			panel.add(new JButton(" "), (Integer) 12);
			panel.add(new JButton(" "), (Integer) 12);
			System.out.println(layout.preferredLayoutSize(panel));
			panel.setBorder(BorderFactory.createLineBorder(ColorUtil.INFO));
			SwingTestUtil.test(panel);
		});
	}
}

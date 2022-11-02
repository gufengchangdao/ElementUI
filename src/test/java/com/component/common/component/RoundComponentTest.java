package com.component.common.component;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class RoundComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			JTextField field = new JTextField("你好");
			field.setPreferredSize(new Dimension(200, 100));

			// JLabel field = new JLabel("标签11");

			RoundComponent c = new RoundComponent(field);
			c.setPaintedBorder(false);
			SwingTestUtil.test(c);
		});
	}
}

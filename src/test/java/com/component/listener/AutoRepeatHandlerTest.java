package com.component.listener;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class AutoRepeatHandlerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new FlowLayout());
			JTextField field = new JTextField("0");
			AutoRepeatHandler autoRepeatHandler = new AutoRepeatHandler(1, field);
			JButton b1 = new JButton(String.format("%+d", 1));
			b1.addMouseListener(autoRepeatHandler);
			b1.addActionListener(autoRepeatHandler);

			autoRepeatHandler = new AutoRepeatHandler(-1, field);
			JButton b2 = new JButton(String.format("%+d", -1));
			b2.addMouseListener(autoRepeatHandler);
			b2.addActionListener(autoRepeatHandler);
			p.add(field);
			p.add(b1);
			p.add(b2);
			SwingTestUtil.test();
		});
	}
}

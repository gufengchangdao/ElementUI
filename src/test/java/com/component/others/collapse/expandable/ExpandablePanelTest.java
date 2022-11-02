package com.component.others.collapse.expandable;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ExpandablePanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			JButton b1 = new JButton("面板一");
			JButton b2 = new JButton("面板二");
			JButton b3 = new JButton("面板三");

			JPanel p1 = new JPanel();
			p1.add(new JButton("艾希"));
			JPanel p2 = new JPanel();
			p2.add(new JTree());
			JPanel p3 = new JPanel();
			p3.add(new JTextField("输入框"));
			p.add(new ExpandablePanel(
					Arrays.asList(b1, b2, b3),
					Arrays.asList(p1, p2, p3)
			));

			SwingTestUtil.test();
		});
	}
}

package com.component.others.line;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ArrowTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new FlowLayout());

			List<Arrow> arrows = Arrays.asList(
					new Arrow(new Point(50, 50), new Point(100, 150)),
					new Arrow(new Point(250, 50), new Point(150, 50)));
			JPanel panel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setStroke(new BasicStroke(4));
					g2.setColor(Color.BLACK);
					for (Arrow a : arrows) {
						a.draw(g2);
					}
					g2.dispose();
				}
			};
			panel.setPreferredSize(new Dimension(270, 200));

			p.add(panel);
			SwingTestUtil.test();
		});
	}
}

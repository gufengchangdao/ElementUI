package com.component.basic.border;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 使用边框的形式来给JTextArea添加背景
 */
public class CentredBackgroundBorderTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JPanel p = SwingTestUtil.init(new MigLayout("fill"));

				JTextArea area = new JTextArea();
				area.setForeground(Color.WHITE);
				area.setBackground(new Color(0x0, true)); // Nimbus
				area.setLineWrap(true);
				area.setOpaque(false);
				area.setText(String.join("\n",
						"private static void createAndShowGui() {",
						"  final JFrame frame = new JFrame();",
						"  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);",
						"  frame.getContentPane().add(new MainPanel());",
						"  frame.pack();",
						"  frame.setLocationRelativeTo(null);",
						"  frame.setVisible(true);",
						"}"
				));

				JScrollPane scroll = new JScrollPane(area);
				scroll.getViewport().setOpaque(false);
				scroll.setViewportBorder(new CentredBackgroundBorder(
						ImageIO.read(CentredBackgroundBorderTest.class.getResourceAsStream("/img/beauty.jpg"))
				));
				scroll.getVerticalScrollBar().setUnitIncrement(25);
				p.add(scroll, "growx, growy");

				SwingTestUtil.test();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}

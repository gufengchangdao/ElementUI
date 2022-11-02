package com.component.common.template;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class C2PanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JPanel p = new JPanel(new WrapLayout());
			JButton button = new JButton("添加");
			p.add(button);
			p.add(new JButton("设置"));
			p.add(new JButton("退出"));
			p.add(new JButton("的挖矿"));
			p.add(new JButton("黄金矿工"));

			JPanel p2 = new JPanel(null);
			JLabel label = new JLabel("标签");
			label.setOpaque(true);
			label.setBackground(Color.RED);
			Point l = button.getLocation();
			Dimension size = label.getPreferredSize();
			label.setBounds(l.x, l.y, size.width, size.height);
			p2.add(label);

			C2Panel<JPanel, JPanel> c2Panel = new C2Panel(p2, p);
			Dimension s = new Dimension(400, 400);
			c2Panel.setPreferredSize(s);
			c2Panel.init();

			SwingTestUtil.test(c2Panel);
		});
	}
}
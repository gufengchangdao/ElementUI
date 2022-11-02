package com.component.others.carousel;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HorizontalCarouselPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.setDefaultTimingSource();
			ArrayList<JPanel> list = new ArrayList<>();
			Color[] colors = {ColorUtil.WARNING, ColorUtil.INFO, ColorUtil.SUCCESS, ColorUtil.PRIMARY};
			for (int i = 1; i <= 4; i++) {
				JPanel p = new JPanel();
				p.add(new JButton("面板 " + i));
				p.setPreferredSize(new Dimension(500, 200));
				p.setOpaque(true);
				p.setBackground(colors[i - 1]);
				list.add(p);
			}

			HorizontalCarouselPanel<JPanel> c = new HorizontalCarouselPanel<>(list, System.out::println);
			c.setBorder(BorderFactory.createLineBorder(Color.RED));

			SwingTestUtil.test(c);
		});
	}
}
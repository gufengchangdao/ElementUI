package com.component.others.carousel;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CarouselCardLayoutPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 2", "grow"));

			CarouselCardLayoutPanel cards = new CarouselCardLayoutPanel();
			cards.add(new JScrollPane(new JTree()));
			cards.add(new JSplitPane());
			cards.add(new JScrollPane(new JTable(9, 3)));
			cards.add(new JButton("JButton"));
			p.add(cards, "span 2, center");

			JButton b1 = new JButton("上一个");
			JButton b2 = new JButton("下一个");
			b1.addActionListener(e -> cards.last());
			b2.addActionListener(e -> cards.next());
			p.add(b1);
			p.add(b2, "right");

			SwingTestUtil.test();
		});
	}
}

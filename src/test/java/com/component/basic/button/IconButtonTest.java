package com.component.basic.button;

import com.component.basic.color.ColorUtil;
import com.component.svg.icon.regular.XCircleSvg;
import com.component.util.SwingTestUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			IconButton b = new IconButton(XCircleSvg.of(16, 16),
					com.component.svg.icon.fill.XCircleSvg.of(16, 16),
					color -> ColorUtil.blend(Color.BLACK, Color.WHITE, 0.5f));
			b.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					b.setPreferredSize(new Dimension(100, 100));
					b.setSize(100, 100);
				}
			});

			SwingTestUtil.test(b);
		});
	}
}

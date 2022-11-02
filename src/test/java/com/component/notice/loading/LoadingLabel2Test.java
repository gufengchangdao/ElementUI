package com.component.notice.loading;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingLabel2Test {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.setDefaultTimingSource();
			SwingTestUtil.loadSkin();
			LoadingLabel2 c = new LoadingLabel2(ColorUtil.PRIMARY, 4);
			// c.setPreferredSize(new Dimension(400, 400));
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					SwingTestUtil.getFrame().getContentPane().remove(c);
					SwingTestUtil.getFrame().getContentPane().repaint();
				}
			});
			SwingTestUtil.test(c);
		});
	}
}

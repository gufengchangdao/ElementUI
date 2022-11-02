package com.component.others.menu;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ReturnJMenuTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			JMenuBar mb = new JMenuBar();
			mb.add(createMenu("文件1"));
			mb.add(Box.createHorizontalGlue());
			mb.add(createMenu("文件2"));
			p.getRootPane().setJMenuBar(mb);

			SwingTestUtil.test();
		});
	}

	private static JMenu createMenu(String key) {
		JMenu menu = new JMenu(key) {
			@Override
			public void setPopupMenuVisible(boolean b) {
				// 当菜单右边界超过窗体时，让菜单在鼠标左方显示
				if (isTopLevelMenu()) {
					Point p = getLocation();
					Rectangle r = getRootPane().getBounds();
					Dimension d1 = getPopupMenu().getPreferredSize();
					if (p.x + d1.width + 14 > r.width) {
						Dimension d2 = getPreferredSize();
						setMenuLocation(d2.width - d1.width, d2.height);
					}
				}
				super.setPopupMenuVisible(b);
			}
		};
		JMenu sub = new ReturnJMenu("Bookmarks");
		sub.add("Item 1");
		sub.add("Item 2");

		menu.add(sub);

		JMenu sub2 = new ReturnJMenu("submenuPopupOffsetX");

		sub2.add("Item 3");
		sub2.add("Item 4");
		menu.add(sub2);

		menu.add("Item 5");
		menu.add("Item 6");

		JMenu sub3 = new ReturnJMenu("Help");
		sub3.add("Help 1");
		sub3.add("Help 2");
		sub3.add("Help 3");
		sub3.add("Help 4");
		menu.add(sub3);

		return menu;
	}
}
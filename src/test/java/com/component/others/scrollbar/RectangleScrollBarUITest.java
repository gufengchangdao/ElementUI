package com.component.others.scrollbar;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.Collections;

public class RectangleScrollBarUITest {
	public static void main(String[] args) {
		// MigLayout不会让滚动窗格溢出的，省去了设置滚动窗格大小
		JPanel p = SwingTestUtil.init(new MigLayout());
		String txt = String.join("\n", Collections.nCopies(100, "aaaaaaaaaaaaaaaaaaa"));
		// 外观的滚动条
		p.add(new JScrollPane(new JTextArea(txt)));

		// 自定义滚动条
		p.add(new JScrollPane(new JTextArea(txt)) {
			@Override
			public void updateUI() {
				super.updateUI();
				getVerticalScrollBar().setUI(new RectangleScrollBarUI());
				getHorizontalScrollBar().setUI(new RectangleScrollBarUI());
			}
		});
		SwingTestUtil.test();
	}
}

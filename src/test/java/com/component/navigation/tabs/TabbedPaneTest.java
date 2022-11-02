package com.component.navigation.tabs;

import com.component.navigation.tabs.ui.CustomTabbedPaneUI;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

/**
 * CustomTabbedPaneUI测试
 */
public class TabbedPaneTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JTabbedPane tb = new JTabbedPane();
			tb.setUI(new CustomTabbedPaneUI());
			tb.add("Tab1", new JTextArea("Tab1"));
			tb.add("Tab2", new JTextArea(""));
			tb.add("Tab3", new JTextArea(""));
			tb.add("Tab4", new JTextArea(""));
			tb.add("Tab5", new JTextArea(""));
			tb.setEnabledAt(1, false);
			tb.setEnabledAt(3, false);
			tb.setPreferredSize(new Dimension(500, 300));

			SwingTestUtil.test(tb);
		});
	}
}
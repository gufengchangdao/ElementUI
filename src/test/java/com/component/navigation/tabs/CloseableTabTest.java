package com.component.navigation.tabs;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class CloseableTabTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JTabbedPane tb = new JTabbedPane();
			tb.add("Tab1", new JTextArea("Tab1"));
			tb.add("Tab2", new JTextArea("Tab2"));
			tb.add("Tab3", new JTextArea("Tab3"));
			tb.add("Tab4", new JTextArea("Tab4"));
			tb.add("Tab5", new JTextArea("Tab5"));
			tb.setTabComponentAt(0, new CloseableTab(tb));

			tb.setEnabledAt(1, false);
			tb.setPreferredSize(new Dimension(400, 300));

			SwingTestUtil.test(tb);
		});
	}
}

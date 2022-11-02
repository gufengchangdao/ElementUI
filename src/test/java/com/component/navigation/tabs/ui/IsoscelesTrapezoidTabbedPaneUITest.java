package com.component.navigation.tabs.ui;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class IsoscelesTrapezoidTabbedPaneUITest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			JTabbedPane tabs = new JTabbedPane() {
				@Override
				public void updateUI() {
					super.updateUI();
					// UIManager.put("TabbedPane.highlight", Color.GRAY);
					setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
					setUI(new IsoscelesTrapezoidTabbedPaneUI());
				}
			};
			tabs.addTab("JTextArea", new JScrollPane(new JTextArea()));
			tabs.addTab("JTree", new JScrollPane(new JTree()));
			tabs.addTab("JButton", new JButton("button"));
			tabs.addTab("JSplitPane", new JSplitPane());
			p.add(tabs, "growx");

			SwingTestUtil.test();
		});
	}
}
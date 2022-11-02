package com.component.navigation.tabs.listener;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class TabTitleEditListenerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));

			String help = String.join("\n",
					" Start editing: Double-Click, Enter-Key",
					" Commit rename: field-focusLost, Enter-Key",
					"Cancel editing: Esc-Key, title.isEmpty"
			);
			JTabbedPane tabs = new JTabbedPane() {
				private transient TabTitleEditListener listener;

				@Override
				public void updateUI() {
					removeChangeListener(listener);
					removeMouseListener(listener);
					super.updateUI();
					listener = new TabTitleEditListener(this);
					addChangeListener(listener);
					addMouseListener(listener);
				}
			};
			tabs.addTab("Shortcuts", new JTextArea(help));
			tabs.addTab("JLabel", new JLabel("JLabel"));
			tabs.addTab("JTree", new JScrollPane(new JTree()));
			tabs.addTab("JSplitPane", new JSplitPane());
			p.add(tabs, "growx, growy");

			SwingTestUtil.test();
		});
	}
}
package com.component.navigation.tabs.hoverclosetab;

import javax.swing.*;
import java.awt.*;

/**
 * 标签的面板，含有文本和图标
 */
class TabPanel extends JPanel {
	private final JButton button;

	public TabPanel(JTabbedPane pane, String title, Component content) {
		this(pane, title, content, new CloseTabIcon());
	}

	public TabPanel(JTabbedPane pane, String title, Component content, Icon icon) {
		super(new BorderLayout());
		setOpaque(false);

		button = new JButton(icon);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setVisible(false);

		JLabel label = new JLabel() {
			@Override
			public Dimension getPreferredSize() {
				Dimension dim = super.getPreferredSize();
				int bw = button.isVisible() ? button.getPreferredSize().width : 0;
				return new Dimension(dim.width - bw, dim.height);
			}
		};
		label.setText(title);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

		button.addActionListener(e -> {
			int idx = pane.indexOfComponent(content);
			pane.removeTabAt(idx);
			int count = pane.getTabCount();
			if (count > idx) {
				Component c = pane.getTabComponentAt(idx);
				if (c instanceof TabPanel) {
					((TabPanel) c).setButtonVisible(true);
				}
			}
		});
		add(label);
		add(button, BorderLayout.EAST);
	}

	public void setButtonVisible(boolean flag) {
		button.setVisible(flag);
	}
}

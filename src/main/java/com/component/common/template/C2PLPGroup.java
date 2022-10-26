package com.component.common.template;

import javax.swing.*;
import java.awt.*;

/**
 * 组合形式，两面板，一传入面板，一层次面板
 */
public class C2PLPGroup {
	private JPanel panel;
	private JLayeredPane layeredPane = new JLayeredPane();

	public C2PLPGroup(JPanel panel) {
		this.panel = panel;
	}

	public void setProperty(JPanel panel) {
		this.panel = panel;
	}

	public void init() {
		Dimension size = panel.getPreferredSize();
		layeredPane.setPreferredSize(size);
		panel.setBounds(0, 0, size.width, size.height);
		layeredPane.add(panel, (Integer) 0);
	}

	/** 改变层次面板预设大小，并同时改变 panel 大小 */
	public void setPreferredSize(Dimension preferredSize) {
		layeredPane.setPreferredSize(preferredSize);
		panel.setBounds(0, 0, preferredSize.width, preferredSize.height);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}
}

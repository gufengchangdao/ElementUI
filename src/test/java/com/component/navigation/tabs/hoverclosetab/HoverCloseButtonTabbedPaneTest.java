package com.component.navigation.tabs.hoverclosetab;

import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.SwordSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class HoverCloseButtonTabbedPaneTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("", "grow"));
			HoverCloseButtonTabbedPane tabbedPane = new HoverCloseButtonTabbedPane();
			tabbedPane.addTab("JTree", new JScrollPane(new JTree()));
			tabbedPane.addTab("JLabel", new JScrollPane(new JLabel("JLabel")));
			tabbedPane.addTab("JSplitPane", new JSplitPane());

			// 指定图标
			RadianceIcon icon = SwordSvg.of(12, 12);
			icon.setColorFilter(color -> ColorUtil.PRIMARY);
			tabbedPane.addTab("宝剑在手", new JSplitPane(), icon);
			p.add(tabbedPane, "growx");

			SwingTestUtil.test();
		});
	}
}
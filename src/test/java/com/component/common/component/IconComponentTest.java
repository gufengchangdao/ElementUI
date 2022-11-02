package com.component.common.component;

import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.StarSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class IconComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			RadianceIcon of = StarSvg.of(16, 16);

			IconComponent<JButton> button = new IconComponent<>(new JButton("点击"));
			button.setLeftIcon(of);
			button.getLeftButton().addActionListener(e -> System.out.println("点击了左键按钮"));
			button.getComponent().addActionListener(e -> {
				button.setRightIcon(of);
				// button.setPreferredSize(new Dimension(200, 60));
			});

			SwingTestUtil.test(button);
		});
	}
}

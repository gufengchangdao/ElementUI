package com.component.common.component;

import com.component.svg.icon.regular.XCircleSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class BackgroundComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			BackgroundComponent c1 = new BackgroundComponent(XCircleSvg.of(100, 100));
			c1.setPreferredSize(new Dimension(100, 100));
			c1.setBorder(BorderFactory.createLineBorder(Color.RED));
			c1.setPreferredSize(new Dimension(200, 200));
			SwingTestUtil.test(c1);
		});
	}
}

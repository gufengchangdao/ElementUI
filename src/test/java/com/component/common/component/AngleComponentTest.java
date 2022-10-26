package com.component.common.component;

import com.component.basic.color.ColorUtil;
import com.component.common.SwingPosition;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class AngleComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JLabel label = new JLabel("这是文本");
			label.setPreferredSize(new Dimension(200, 200));
			label.setOpaque(true);
			label.setBackground(ColorUtil.SUCCESS.brighter());

			SwingTestUtil.test(new AngleComponent(label, SwingPosition.LEFT,
					label.getBackground(), 20, 3, new Point(0, 0)));
		});
	}
}

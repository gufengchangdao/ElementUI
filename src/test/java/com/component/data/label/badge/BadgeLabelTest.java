package com.component.data.label.badge;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class BadgeLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.test(new BadgeLabel("测试标签", ColorUtil.DANGER, Color.WHITE));
		});
	}
}

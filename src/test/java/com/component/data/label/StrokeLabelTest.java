package com.component.data.label;

import com.component.basic.color.ColorUtil;
import com.component.font.FontUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class StrokeLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			// com.tech.view.component.label.StrokeLabel label = new com.tech.view.component.label.StrokeLabel("测试标签描边");
			// label.setFont(new Font("Courier New", Font.BOLD, 140));
			// SwingTestUtils.test(label);
			StrokeLabel label = new StrokeLabel("测试标签描边2", FontUtil.DEFAULT_FONT.deriveFont(36f),
					ColorUtil.COMMON_TEXT, 2, ColorUtil.WARNING);
			SwingTestUtil.test(label);
		});
	}
}
